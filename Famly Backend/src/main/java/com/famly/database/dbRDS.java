package com.famly.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

@Deprecated
public class dbRDS {

    public static final String dbInstanceID = "myfamlytreedb";
    private static final String dbName = "famlytree";
    private static final String rdsEndpoint = "myfamlytreedb.c9uzkdtsq1am.us-east-1.rds.amazonaws.com";
    private static final String port = "3306";
    private static final String userName = "harish";
    private static final String password = "myawsvm1";
    private static Logger logger = LogManager.getLogger(dbRDS.class.getName());

    private static Connection getRemoteConnection() {
        System.out.println("Remote connection starting.");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
//                String dbName = System.getProperty("RDS_DB_NAME");
//                String userName = System.getProperty("RDS_USERNAME");
//                String password = System.getProperty("RDS_PASSWORD");
//                String hostname = System.getProperty("RDS_HOSTNAME");
//                String port = System.getProperty("RDS_PORT");
                String jdbcUrl = "jdbc:mysql://" + rdsEndpoint + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                logger.trace("Getting remote connection with connection string from environment variables.");
                Connection con = DriverManager.getConnection(jdbcUrl);
                logger.info("Remote connection successful.");
                System.out.println("Remote connection successful.");
                return con;
            }
            catch (ClassNotFoundException e) { logger.warn(e.toString());}
            catch (SQLException e) { logger.warn(e.toString());}

        return null;
    }

//    public static void main(String[] args){
//        Connection conn = null;
//        Statement setupStatement = null;
//        Statement readStatement = null;
//        ResultSet resultSet = null;
//        String results = "";
//        int numresults = 0;
//        String statement = null;
//        Connection db =null;
//
//        try {
//            // Create connection to RDS DB instance
//            conn = getRemoteConnection();
//
//            // Create a table and write two rows
//            setupStatement = conn.createStatement();
//            String createTable = "CREATE TABLE Beanstalk (Resource char(50));";
//            String insertRow1 = "INSERT INTO Beanstalk (Resource) VALUES ('EC2 Instance');";
//            String insertRow2 = "INSERT INTO Beanstalk (Resource) VALUES ('RDS Instance');";
//
//            setupStatement.addBatch(createTable);
//            setupStatement.addBatch(insertRow1);
//            setupStatement.addBatch(insertRow2);
//            setupStatement.executeBatch();
//            setupStatement.close();
//
//        } catch (SQLException ex) {
//            // Handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        } finally {
//            System.out.println("Closing the connection.");
//            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
//        }
//    }
}
