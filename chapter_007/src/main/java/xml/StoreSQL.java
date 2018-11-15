package xml;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StoreSQL {

    private Connection connection;

    public StoreSQL(Config config) {
        String sql = "CREATE TABLE if not exists entry (field INTEGER)";
        String url = config.get("sqlite-url");
        try {
            Class.forName(config.get("sqlite-driver-class-name"));
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method generate n records in DB with fields from 1 to n
     * @param n
     */
    public void generate(int n) {
        String sqlDel = "DELETE from entry;";
        String sqlInsert = "Insert into entry values(?)";
        try (PreparedStatement psD = connection.prepareStatement(sqlDel);
              PreparedStatement psI = connection.prepareStatement(sqlInsert)) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= n; i++) {
                psI.setInt(1, i);
                psI.addBatch();
            }
            psD.executeUpdate();
            psI.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method gets data from DB end adds to the List
     * @return
     */
    public List<Integer> getData() {
        List<Integer> result = new LinkedList<>();
        String sql = "Select * from entry;";
        try (Statement stat = connection.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {
                result.add(rs.getInt("field"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method converts DataList to List<Entry> that will be used for XML transformation
     * @return
     */
    public List<StoreXML.Entry> doEntryList() {
        List<StoreXML.Entry> entryList = new ArrayList<>();
        for (Integer i: this.getData()) {
            StoreXML.Entry temp = new StoreXML.Entry();
            temp.setValue(i);
            entryList.add(temp);
        }
        return entryList;
    }
}
