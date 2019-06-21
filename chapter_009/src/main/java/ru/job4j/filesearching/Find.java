package ru.job4j.filesearching;

import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.Thread.currentThread;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Find {

    private CommandLine cmdline;
    private boolean endOfList = false;
    private final ConcurrentLinkedQueue<File> fileList = new ConcurrentLinkedQueue<>();
    private final List<File> resultFileList = new ArrayList<>();
    private String directory;
    private char filterType;
    private Logger logger;
    private String logPath;
    private String logFile;
    private String filterMask;

    /**
     * Method gets parameters from the command line
     * @param cmdline
     */
    public void setIniValues(CommandLine cmdline) {
        this.logFile = cmdline.getOptionValue('o');
        this.directory = cmdline.getOptionValue('d');
        this.filterMask = cmdline.getOptionValue('n');
        char ft;
        if (cmdline.hasOption('r')) {
            ft = 'r';
        } else if (cmdline.hasOption('m')) {
            ft = 'm';
        } else {
            ft = 'f';
        }
        this.filterType = ft;
    }

    public void setLogFile(CommandLine cmdline) {
        this.logFile = cmdline.getOptionValue('o');
    }

    public void setLogPath(String dir, String logFile) {
        this.logPath = String.format("%s/%s", dir, logFile);
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Method creates log file in specific directory
     * @return
     */
    private Logger logIni() {
        Logger log = Logger.getLogger("Log");
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tc \"%5$s\"%n");
        FileHandler fh;
        try {
            setLogPath(this.directory, this.logFile);
            fh = new FileHandler(this.logPath);
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        return log;
    }

    /**
     * Method puts record to log
     * @param rec
     */
    private void recToLog(String rec) {
        this.logger.info(rec);
    }

    /**
     * Method puts search result to log file
     */
    public void putSearchingResultToLog() {
        for (File f: this.resultFileList) {
            recToLog(f.getName());
        }
    }

    /**
     * Method initiates and starts searching process
     */
    private void search() {
        Thread fileSearch = getFileList(this.directory);
        Thread filteredFilesList = getFilteredFilesList(this.filterMask);
        recToLog("Start searching.");
        fileSearch.start();
        recToLog("Start filtering. Result:");
        filteredFilesList.start();
        try {
            fileSearch.join();
            filteredFilesList.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        putSearchingResultToLog();
        recToLog("Searching is terminated");
    }

    /**
     * Method searches files in specific directory
     * @param filterMask
     * @return
     */
    private Thread getFilteredFilesList(String filterMask) {
        return new Thread(
                () -> {
                    while (!(this.fileList.isEmpty() && endOfList)) {
                        File f = fileList.poll();
                        if (f != null) {
                            switch (this.filterType) {
                                case 'r':
                                    if (f.getName().matches(filterMask)) {
                                        this.resultFileList.add(f);
                                    }
                                    break;
                                case 'm':
                                    if (FilenameUtils.wildcardMatch(f.getName(), filterMask)) {
                                        this.resultFileList.add(f);
                                    }
                                    break;
                                default:
                                    if (f.getName().equals(filterMask)) {
                                        this.resultFileList.add(f);
                                    }
                            }
                        }
                    }
                    currentThread().interrupt();
                }
        );
    }

    /**
     * Method does files filtering by mask
     * @param directory
     * @return
     */
    public Thread getFileList(String directory) {
        Queue<File> queueFiles = new LinkedList<>();
        return new Thread(
                () -> {
                    File file = new File(directory);
                    queueFiles.offer(file);
                    while (!endOfList) {
                        File tempFile = queueFiles.poll();
                        if (tempFile != null) {
                            if (tempFile.isDirectory()) {
                                for (File f : Objects.requireNonNull(tempFile.listFiles())) {
                                    queueFiles.offer(f);
                                }
                            } else {
                                fileList.offer(tempFile);
                            }
                        } else {
                            endOfList = true;
                            currentThread().interrupt();
                        }
                    }
                });
    }

    /**
     * To simulate args use:
     *  String[] testArgs = { "-d", "/home/igor/project/job4j/chapter_009", "-n", "file1.txt", "-f", "-o", "log.txt"};
     *  String[] testArgs = { "-d", "/home/igor/project/job4j/chapter_009", "-n", "*.txt", "-m", "-o", "log.txt"};
     *  String[] testArgs = { "-d", "/home/igor/project/job4j/chapter_009", "-n", "\\w+\\.txt", "-r", "-o", "log.txt"};
     *  args = testArgs;
     *
     * @param args
     */
    public void commandLineParser(String[] args) throws Exception {
        Options options = new Options();

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        options.addOption("d", true, "Initial search directory")
                .addOption("n", true, "Full file name search, mask or regular expression")
                .addOption("m", false, "Search by mask")
                .addOption("f", false, "Search by full name")
                .addOption("r", false, "Search by regular expression")
                .addOption("h", false, "Help")
                .addOption("o", true, "Log file");
        String header = "\nDescription of command line arguments\n\n";
        String footer = "";
        try {
            this.cmdline = parser.parse(options, args);
            setIniValues(cmdline);
            setLogFile(cmdline);
            setLogger(logIni());
            recToLog("Searching initiation.");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar find.jar", header, options, footer, true);
            throw new Exception();
        }
    }

    public static void main(String[] args) {
        Find find = new Find();
        try {
            find.commandLineParser(args);
            find.search();
        } catch (Exception e) {
            System.out.println("Error occur.");
        }
    }
}
