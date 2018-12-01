package ru.job4j.parsing;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ParsingScheduler implements Job {

    private static final Logger LOG = Logger.getLogger(ParsingScheduler.class);
    private Config config;
    private static String property;
    private Connection connection;
    private final String url = "https://www.sql.ru/forum/job-offers/";
    private final String keyWord = "java";
    private final String keyWordExcl1 = "javascript";
    private final String keyWordExcl2 = "javas script";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        runParsing();
    }

    /**
     * Method initiate Sql.ru vacancy Parsing
     */
    private void runParsing() {
        config = new Config(property);
        this.dbConnection();
        try {
            this.searchVacancy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setProperty(String p) {
        property = p;
    }

    /**
     * Method performs DB connection and creates DB tables "vacancy", "runtime"
     */
    public void dbConnection() {
        String sql = "CREATE TABLE if not exists vacancy ( id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "  name VARCHAR NOT NULL UNIQUE,\n"
                + "  text TEXT,\n"
                + "  link VARCHAR)";
        String sql2 = "create table  if not exists runtime(lastruntime real);";
        String url = config.get("sqlite-url");
        try {
            Class.forName(config.get("sqlite-driver-class-name"));

            connection = DriverManager.getConnection(url);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        try (PreparedStatement ps = connection.prepareStatement(sql);
             PreparedStatement ps2 = connection.prepareStatement(sql2)) {
            ps.executeUpdate();
            ps2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method initiate vacancy searching
     *
     * @throws IOException
     */
    private void searchVacancy() throws IOException {
        int page = 1;
        String fullUrl = url + page;
        boolean runScan = true;
        while (runScan) {
            runScan = pageScan(keyWord, keyWordExcl1, keyWordExcl2, fullUrl, lastScan());
            page++;
            fullUrl = url + page;
        }
        saveLastScan();
    }

    /**
     * Method gets last scan date
     *
     * @return
     */
    private long lastScan() {
        String sql = "SELECT datetime(lastruntime) from runtime ORDER BY lastruntime DESC LIMIT 1";
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currentDate = Calendar.getInstance();
        long lastScanTime = currentDate.getTimeInMillis();
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                lastScanTime = f.parse(rs.getString(1)).getTime();
            } else {
                currentDate.set(Calendar.YEAR, currentDate.get(Calendar.YEAR) - 1);
                lastScanTime = currentDate.getTimeInMillis();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastScanTime;
    }

    /**
     * This method saves last scan date in BD in table "runtime"
     */
    private void saveLastScan() {
        String sqlInsert = "Insert into runtime(lastruntime) values (CURRENT_TIMESTAMP)";
        try (PreparedStatement psI = connection.prepareStatement(sqlInsert)) {
            psI.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method performs scan of the sql.ru page
     * @param keyword  - search keyword
     * @param url      - sql.ru vacancy page url
     * @param lastScan - sql.ru vacancy last scan
     * @return - true, if scan successful and not last page
     * @throws IOException
     */
    private boolean pageScan(String keyword, String keyword2, String keyword3, String url, long lastScan) throws IOException {
        boolean result = true;
        String name;
        String link;
        String text;
        String date;
        int elementCount = 0;
        int notExceptCount = 0;
        int badCount = 3;
        Document doc = Jsoup.connect(url).get();
        Elements aElements = doc.select("td.postslisttopic");
        for (Element aElement : aElements) {
            if (aElement.select("a").first().text().toLowerCase().contains(keyword)
                    && !aElement.select("a").first().text().toLowerCase().contains(keyword2)
                    && !aElement.select("a").first().text().toLowerCase().contains(keyword3)
                    ) {
                name = aElement.select("a").first().text();
                link = aElement.select("a").first().attr("href");
                Document doc2 = Jsoup.connect(aElement.select("a").first().attr("href")).get();
                text = doc2.select("td.msgBody").eq(1).text();
                int index = doc2.select("td.msgFooter").first().text().indexOf(",");
                date = doc2.select("td.msgFooter").first().text().substring(0, index);
                Vacancy vacancy = new Vacancy(name, text, link, date);
                if (isValidDate(vacancy, lastScan)) {
                    if (checkRecord(vacancy))
                    {
                        insertVacToDB(vacancy);
                        LOG.info(String.format("%s\n%s\n\n%s\n -----------------------\n", vacancy.getName(), vacancy.getLink(), vacancy.getText()));
                    }
                } else {
                    notExceptCount++;
                }
                if (notExceptCount > badCount) {
                    result = false;
                    break;
                }
            }
            elementCount++;
            if (elementCount < 10) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * This method checks that found vacancy within the valid period
     * @param vacancy - parsed vacancy
     * @param endDate - the date on which the verification takes place
     * @return - true if valid
     */
    private boolean isValidDate(Vacancy vacancy, long endDate) {
        boolean result = false;
        SimpleDateFormat f = new SimpleDateFormat("dd MMM yy");
        Date d = new Date();
        if (!(vacancy.getData().equals("вчера") || vacancy.getData().equals("сегодня"))) {
            try {
                d = f.parse(vacancy.getData());
                if (d.getTime() > endDate) {
                    result = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            result = true;
        }
        d.setTime(endDate);
        return result;
    }

    /**
     * Insert to DB new vacancy record
     * @param vacancy - parsed vacancy
     */
    private Vacancy insertVacToDB(Vacancy vacancy) {
        String sqlInsert = "Insert into vacancy(name, text, link) values (?,?,?)";
        try (PreparedStatement psI = connection.prepareStatement(sqlInsert)) {
            psI.setString(1, vacancy.getName());
            psI.setString(2, vacancy.getText());
            psI.setString(3, vacancy.getLink());
            psI.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vacancy;
    }

    /**
     *  method checks the presence of a record in ВИ for uniqueness
     * @param vacancy
     * @return
     */
    private boolean checkRecord(Vacancy vacancy) {
        boolean result = true;
        try (PreparedStatement pst = connection.prepareStatement("SELECT count(id) FROM vacancy WHERE name = ?")) {
            pst.setString(1, vacancy.getName());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    if (rs.getInt(1) != 0) {
                        result = false;
                        vacancy.setStatus("exist");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}


/**
 * This Main Class that reads prompt input parameter
 * his parameter is property file name
 */
class SqlRuParsing {
    public static void main(String[] args) {
        if (args.length > 0) {
            ParsingScheduler.setProperty(args[0]);
        } else {
            ParsingScheduler.setProperty("app.properties");
        }
        initScheduler();
    }

    /**
     * Method initiates job Schedule every 12 hours
     */
    private static void initScheduler() {
        try {
            JobKey jobKeyA = new JobKey("myJob", "group1");
            JobDetail jobA = JobBuilder.newJob(ParsingScheduler.class)
                    .withIdentity(jobKeyA).build();
            Trigger trigger1 = TriggerBuilder
                    .newTrigger()
                    .withIdentity("Trigger1", "group1")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 0 12 * * ?"))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobA, trigger1);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}