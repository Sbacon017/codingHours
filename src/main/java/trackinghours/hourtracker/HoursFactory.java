package trackinghours.hourtracker;

import java.sql.*;
import java.util.*;

public class HoursFactory {
	
	Connection conn;

	
	public Hours[] getHourObjects() {
		
		// Initialize the Hours Array
		Hours[] hourObjects = new Hours[2];
		
		try {
			//Get the connection object
			this.conn = getConnection();
			
			//Create the query string.
			String query = "";
	
			//Get ResultSetObjects
			Statement stmt = this.conn.createStatement();
			
			System.out.println("Checking table status.");
			
			//Check that the table exists, and create it if not
			DatabaseMetaData meta = this.conn.getMetaData();
			ResultSet metaRS = meta.getTables(null, null, null, new String[] {"TABLE"});
			if (!metaRS.next()) {
				
				//Create the table
				System.out.println("Creating Table.");
				stmt.execute("CREATE TABLE HOURSTABLE (ID INT PRIMARY KEY, HOURS INT)");
				System.out.println("Table Created.");
				
				//Initialize the base (0) values
				System.out.println("Initializing values.");
				stmt.executeUpdate("INSERT INTO HOURSTABLE VALUES (1, 0)");
				stmt.executeUpdate("INSERT INTO HOURSTABLE VALUES (2, 0)");
				System.out.println("Values initialized.\n");
			} else {
				System.out.println("Table exists");
			}

			
			//Getting result set
			query = "SELECT * FROM HOURSTABLE";
			ResultSet rs = stmt.executeQuery(query);

			//Generate the hours
			System.out.println("Creating hour objects.");
			makeHours(rs, hourObjects);
			
			
		} catch(SQLException e) {
			System.out.println("SQLException Caught.");
			e.printStackTrace();
		}
		
		//Return the thing
		return hourObjects;
		
	}
	
	/*
	 * Connects to the Derby database. 
	 */
	public Connection getConnection() throws SQLException {
		System.out.println("Connecting to Database...");
		Connection conn = null;;
		conn = DriverManager.getConnection("jdbc:derby:hoursdb;create=true");
		System.out.println("Connected to: " + conn + "\n");	
		return conn;
	}
	
	// Creates the hours from the data in the results set. 
	public void makeHours(ResultSet rs, Hours[] hourObjects) throws SQLException {

		//Get values from ResultSet
		rs.next();
		int codeTime = rs.getInt(2);
		rs.next();
		int gameTime = rs.getInt(2);
		
		
		//Create HourObjects
		Hours codeHours = new Hours(codeTime);
		Hours gameHours = new Hours(gameTime);
		
		//Pop em in that array
		hourObjects[0] = codeHours;
		hourObjects[1] = gameHours;
		
	}
	
	public void saveHours(Hours[] hourArray) {
		try {
			Statement stmt = this.conn.createStatement();
			stmt.executeUpdate("UPDATE hourstable SET HOURS = " 
					+ hourArray[0].getHours() + " WHERE ID = 1");
			stmt.executeUpdate("UPDATE hourstable SET HOURS = " 
					+ hourArray[1].getHours() + " WHERE ID = 2");
			
			stmt.close();
			conn.close();
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	

}
