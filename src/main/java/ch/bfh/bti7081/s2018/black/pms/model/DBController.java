package ch.bfh.bti7081.s2018.black.pms.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {
	
    private static Connection connection; 
    private static String DB_PATH = System.getProperty("user.home") + "/" + "testdb";
	
    DBController(){
    
    } 
	
    public void initDBConnection() {
        try {
            if (connection != null)
                return;
            
            // ToDo: Logger
            System.out.println("Creating Connection to Database...");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if (!connection.isClosed())
            	// ToDo: Logger
                System.out.println("...Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
    
    public void getLocations() {
    	getQueries("Location");
    }
    
    private void getQueries(String tableName) {
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName + ";");
            
            while (rs.next()) {
            	System.out.println("ID = " + rs.getInt("ID"));
                System.out.println("Autor = " + rs.getString("Name"));
                System.out.println("Titel = " + rs.getString("Street"));
            }
            
            rs.close();
            connection.close();
            
        } catch (SQLException e) {
        	// ToDo Logger
            System.err.println("Couldn't handle DB-Query");
            e.printStackTrace();
        }
    }

	/*public static void main(String[] args) {
		DBController dbController = new DBController();
		dbController.initDBConnection();
		dbController.getLocations();
	}
	*/
}
