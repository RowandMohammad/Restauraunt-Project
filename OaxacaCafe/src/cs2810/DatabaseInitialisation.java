package cs2810;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitialisation {
	private final static String user = "vnxcaquwfcdcmx";
	private final static String password = "57a2d6d4bc061d9a386aaa5352bac1ac7cfc1744b1b7e46318519a73c7dfa547";

	public static void main(String[] args) throws URISyntaxException, SQLException {
		String mainMenuFile = "mainmenu.txt";
		String sidesMenuFile = "sidesmenu.txt";
		String drinksMenuFile = "drinksmenu.txt";
		String staffLoginFile = "stafflogin.txt";
		System.out.println("************** Checking JDBC Connection With PostgreSQL **************");
		Connection dbConnection = null;
		dbConnection = getConnection();
		if (dbConnection != null) {
			System.out.println("************** Connection Successful! **************");

		} else {
			System.out.println("************** Connection Failedl! **************\"");
			return;
		}
		{
			dropTable(dbConnection, "mainmenu");
			createTable(dbConnection,
					"mainmenu (name varchar(50) PRIMARY KEY," + "calories int, " + "ingredients varchar(200), "
							+ "type varchar(50), " + "price DECIMAL(4 , 2 ) NOT NULL, " + "ETA int)");
			dropTable(dbConnection, "sidesmenu");
			createTable(dbConnection,
					"sidesmenu (name varchar(50) PRIMARY KEY," + "calories int, " + "ingredients varchar(200), "
							+ "type varchar(50), " + "price DECIMAL(4 , 2 ) NOT NULL, " + "ETA int)");
			dropTable(dbConnection, "drinksmenu");
			createTable(dbConnection,
					"drinksmenu (name varchar(50) PRIMARY KEY," + "calories int, " + "ingredients varchar(200), "
							+ "type varchar(50), " + "price DECIMAL(4 , 2 ) NOT NULL, " + "ETA int)");
			dropTable(dbConnection, "stafflogin");
			createTable(dbConnection,
					"stafflogin (username int PRIMARY KEY," + "password int, " + " staffrole varchar(50))");
			dropTable(dbConnection, "orders");
			createTable(dbConnection,
					"orders (ordernumber int PRIMARY KEY," + "foodordered varchar(500), "
							+ "totalprice DECIMAL(4 , 2 ) NOT NULL, " + "ordertime int, " + "waiter varchar(50), "
							+ "ETA int, " + "tablenumber int)");

		}
		insertDataIntoTable(dbConnection, "mainmenu (name, calories, ingredients, type, price, ETA)", mainMenuFile);
		insertDataIntoTable(dbConnection, "sidesmenu (name, calories, ingredients, type, price, ETA)", sidesMenuFile);
		insertDataIntoTable(dbConnection, "drinksmenu (name, calories, ingredients, type, price, ETA)", drinksMenuFile);
		insertDataIntoTable(dbConnection, "stafflogin (username, password, staffrole)", staffLoginFile);

	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(
				"postgres://vnxcaquwfcdcmx:57a2d6d4bc061d9a386aaa5352bac1ac7cfc1744b1b7e46318519a73c7dfa547@ec2-52-211-161-21.eu-west-1.compute.amazonaws.com:5432/dakl9haghtbqac");

		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
				+ "?sslmode=require";

		return DriverManager.getConnection(dbUrl, user, password);
	}

	// Drops any tables with connection to database
	public static void dropTable(Connection dbConnection, String table) {
		Statement st = null;
		try {
			st = dbConnection.createStatement();
			st.execute("DROP TABLE IF EXISTS " + table + " CASCADE");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Creates tables in the database
	public static void createTable(Connection dbConnection, String tableDescription) {
		Statement st = null;
		try {
			st = dbConnection.createStatement();
			st.execute("CREATE TABLE " + tableDescription);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertDataIntoTable(Connection dbConnection, String table, String file) {
		int batchSize = 3;

		try {

			int count = 0;
			String lineText = null;
			BufferedReader lineReader;
			lineReader = new BufferedReader(new FileReader(file));
			System.out.println("Reading " + file + " file and inserting values in database");
			while ((lineText = lineReader.readLine()) != null) {
				String sqlLine = "INSERT INTO " + table + " VALUES(";
				String[] menuBL = lineText.split(",");

				for (int j = 0; j <= menuBL.length; j++) {
					if (j < menuBL.length - 1) {
						sqlLine += "?,";
					} else if (j == menuBL.length - 1) {
						sqlLine += "?)";
					}

				}

				PreparedStatement statement = dbConnection.prepareStatement(sqlLine);
				for (int i = 0; i < menuBL.length; i++) {
					if (stringToNumeralChecker(menuBL[i])) {
						statement.setInt(i + 1, Integer.parseInt(menuBL[i]));
					}
					if (stringToFloatChecker(menuBL[i])) {
						statement.setFloat(i + 1, Float.parseFloat(menuBL[i]));
					}

					else {
						statement.setString(i + 1, menuBL[i]);
					}
				}
				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			lineReader.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	  //Connects to database to execute queries
	  public static ResultSet executeSelect(Connection dbConnection, String sqlQuery) {
	    Statement st = null;
	    try {
	      st = dbConnection.createStatement();
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
	    ResultSet rs = null;
	    try {
	      rs = st.executeQuery(sqlQuery);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
	    
	    
	    return rs;
	    
	  }

	// Checks whether passed string can be converted to a numeric value
	public static boolean stringToNumeralChecker(String numberInString) {
		if (numberInString == null) {
			return false;
		}
		try {
			int i = Integer.parseInt(numberInString);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	// Checks whether passed string can be converted to a numeric value
	public static boolean stringToFloatChecker(String numberInString) {
		if (numberInString == null) {
			return false;
		}
		try {
			float i = Float.parseFloat(numberInString);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
