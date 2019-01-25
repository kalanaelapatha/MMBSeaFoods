package application.Services;

import java.sql.*;

public class DBConnection {

	public static Connection Connector() {
		try {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection("jdbc:sqlite:applicationDB.db");
				return conn;
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Connection LoginConnector() {
		try {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection("jdbc:sqlite:applicationDB.db");
				
				if(conn != null) {
					//creating the database 
					 Statement stmt = conn.createStatement();
					 
			         String UserTB = "CREATE TABLE IF NOT EXISTS Users " +
			                        "( ID             INTEGER PRIMARY KEY AUTOINCREMENT," +
			                        "  USERNAME       TEXT    NOT NULL, " + 
			                        "  PASSWORD       TEXT    NOT NULL)";
			         
			         String Vehicles = "CREATE TABLE IF NOT EXISTS Vehicles " +
		                        "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		                        "  Vehicle_No     TEXT    	NOT NULL UNIQUE, " + 
		                        "  Total_Lease    DOUBLE    NOT NULL, " + 
		                        "  Paid_Amount    DOUBLE    NOT NULL," +
		                        "  To_Pay    	  DOUBLE    NOT NULL, " +
		                        "  Premium_Amount DOUBLE    NOT NULL, " +
		                        "  Last_Payment   DATE    )";
			         
			         String Vehicles_Leased_Payments = "CREATE TABLE IF NOT EXISTS Vehicles_Leased_Payments " +
		                        "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		                        "  Vehicle_ID     INTEGER   REFERENCES  Vehicles (ID)," + 		                         
		                        "  Paid_Amount    DOUBLE    NOT NULL," +
		                        "  Payment_Date   DATE    )";
			         
			         String Boats = "CREATE TABLE IF NOT EXISTS Boats" +
		                        "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		                        "  BoatNo     	  TEXT      NOT NULL," + 		                         
		                        "  Mobile_No      TEXT      NOT NULL)";
			         
			         String Boat_Account = "CREATE TABLE IF NOT EXISTS Boat_Account" +
		                        "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		                        "  Date     	  DATE      NOT NULL," + 		                         		                        
		                        "  Reason         TEXT      NOT NULL," +
		                        "  To_Pay		  DOUBLE            ," +
		                        "  Paid           DOUBLE            ," +
		                        "  Boat_ID        INTEGER   REFERENCES  Boats (ID) )";
			         
			         String Boat_Account_UnCleared = "CREATE TABLE IF NOT EXISTS Boat_Account_UnCleared" +
		                        "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		                        "  Date     	  DATE      NOT NULL," + 		                         		                        
		                        "  Reason         TEXT      NOT NULL," +
		                        "  To_Pay		  DOUBLE    NOT NULL," +
		                        "  Boat_ID        INTEGER   REFERENCES  Boats (ID))";
			         
			         String Foreign_Fish_Buyers = "CREATE TABLE IF NOT EXISTS Foreign_Fish_Buyers" +
							 "( ID            INTEGER 	PRIMARY KEY AUTOINCREMENT," +	                         		                        
					         "  Name          TEXT      NOT NULL," +
					         "  Mobile_No	  TEXT            )" ;
					 
					 String F_Fish_Buyers_Account = "CREATE TABLE IF NOT EXISTS F_Fish_Buyers_Account" +
							 "( ID            INTEGER 	PRIMARY KEY AUTOINCREMENT," +	                         		                        
		            		 "  Date     	  DATE      NOT NULL," + 		                         		                        
		                     "  Reason        TEXT      NOT NULL," +
		                     "  To_Pay		  DOUBLE            ," +
		                     "  Paid          DOUBLE            )" ;	
					 
					 String F_Fish_Buyers_Account_Uncleard = "CREATE TABLE IF NOT EXISTS F_Fish_Uncleard" +
							 "( ID            INTEGER 	PRIMARY KEY AUTOINCREMENT," +	                         		                        
		            		 "  Date     	  DATE      NOT NULL," + 		                         		                        
		                     "  Reason        TEXT      NOT NULL," +
		                     "  To_Pay		  DOUBLE            ," +
		                     "  Paid          DOUBLE            )" ;	
			         
			         String Fish_Lot = "CREATE TABLE IF NOT EXISTS Fish_Lot" +
		                        "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		                        "  Added_Date     DATE      NOT NULL," + 		                         		                        
		                        "  Lorry_Number   TEXT      NOT NULL," +
		                        "  Buying_Weight  INTEGER    ,"+
		                        "  Ice_fee        DOUBLE     ,"+
		                        "  Lorry_fee      DOUBLE     ,"+
		                        "  other_fees     DOUBLE     ,"+
		                        "  buying_price   DOUBLE     ,"+
		                        "  Sold_Weight    INTEGER    ,"+
		                        "  Sold_price     DOUBLE     ,"+
		                        "  Sold_To        INTEGER   REFERENCES  Foreign_Fish_Buyers (ID))";
			         
			         String Fish_stock = "CREATE TABLE IF NOT EXISTS Fish_stock" +
		                        "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		                        "  Added_Date     DATE      NOT NULL," + 		                         		                        
		                        "  Boat_ID        INTEGER   REFERENCES  Boats (ID)," +
		                        "  Harbour        TEXT      NOT NULL,"+
		                        "  sapparu        INTEGER   NOT NULL,"+
		                        "  NoofFishes     INTEGER     ,"+
		                        "  Total_Weight   INTEGER    ,"+
		                        "  buying_price   DOUBLE     ,"+
		                        "  Lot_ID         INTEGER   REFERENCES  Fish_Lot (ID))";
		                        
		             String Foreign_Fish_types = "CREATE TABLE IF NOT EXISTS Foreign_Fish_types" +
		                        "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +           
		                        "  Name           TEXT      NOT NULL,"+
		                        "  buying_Price   DOUBLE    NOT NULL )";
		            
		             //to add each fish separtaly to the stock
		             
		             String stock_fishes = "CREATE TABLE IF NOT EXISTS stock_fishes" +
		            		 "( ID             INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		            		 "  Type           INTEGER  REFERENCES  foreign_Fish_types (ID),"+
		            		 "  Total_Weight   INTEGER  NOT NULL,"+
		            		 "  buying_Price   DOUBLE   NOT NULL,"+
		            		 "  Fish_stock_ID  INTEGER  REFERENCES  Fish_stock (ID) )";
		             
		             String Third_Party_Account = "CREATE TABLE IF NOT EXISTS Third_Party_Account" +
		            		 "( ID            INTEGER 	PRIMARY KEY AUTOINCREMENT," +
		            		 "  Date     	  DATE      NOT NULL," + 		                         		                        
		                     "  Reason        TEXT      NOT NULL," +
		                     "  To_Pay		  DOUBLE            ," +
		                     "  Paid          DOUBLE            )" ;		
		                     
					 String Third_Party_Acc_Uncleared = "CREATE TABLE IF NOT EXISTS Third_Party_Account" +
							 "( ID            INTEGER 	PRIMARY KEY AUTOINCREMENT," +
							 "  Date     	  DATE      NOT NULL," + 		                         		                        
					         "  Reason        TEXT      NOT NULL," +
					         "  To_Pay		  DOUBLE            ," +
					         "  Paid          DOUBLE            )" ;
					 
					 
		             
		             
		            		 
			         stmt.executeUpdate(UserTB);
			         stmt.executeUpdate(Vehicles);
			         stmt.executeUpdate(Vehicles_Leased_Payments);
			         stmt.executeUpdate(Boats);
			         stmt.executeUpdate(Boat_Account);
			         stmt.executeUpdate(Boat_Account_UnCleared);	
			         stmt.executeUpdate(Foreign_Fish_Buyers);
			         stmt.executeUpdate(F_Fish_Buyers_Account);
			         stmt.executeUpdate(F_Fish_Buyers_Account_Uncleard);	
			         stmt.executeUpdate(Fish_Lot);
			         stmt.executeUpdate(Fish_stock);
			         stmt.executeUpdate(Foreign_Fish_types);
			         stmt.executeUpdate(stock_fishes);	
			         stmt.executeUpdate(Third_Party_Account);
			         stmt.executeUpdate(Third_Party_Acc_Uncleared);
			         
			         stmt.close();
			         
			         
			         
				}
				
				//add the initial administrator account
				PreparedStatement checkUser=null;
				ResultSet users=null;
				String query= "select * from Users"; 
					
				checkUser =conn.prepareStatement(query);
				users = checkUser.executeQuery();
				
				if(!users.next()) {
						PreparedStatement insertUser=null;
						String insertQuery= "INSERT INTO Users (USERNAME, PASSWORD)" + 
											"VALUES ('root', 'root')";
						insertUser = conn.prepareStatement(insertQuery);
						insertUser.executeUpdate();
						insertUser.close();
					}
				
				return conn;
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}