package application.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Models.Boat_Account;
import application.Models.F_Fish_Buyers_Account;
import application.Models.F_Fish_Buyers_Account_Uncleard;
import application.Models.User;

public class Boat_AccountServices {
	
	
	Connection connection;
	
	
	//search and get particular Boat account
	public Boat_Account getAllentries(int ID) throws SQLException {
    	
    	connection=DBConnection.Connector();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		Boat_Account boat_Account= new Boat_Account();
		
		String getAllentriesQuery= "select * from Boat_Account where ID =?";
	
		
		try {
			
			preparedStatement= connection.prepareStatement(getAllentriesQuery);
			//preparedStatement.setString(Integer.parseInt());
			
			resultSet=preparedStatement.executeQuery();
			
			
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			connection.close();
		}
		return boat_Account;

		
		
    }
	
	// add entries
	public boolean addEntries(F_Fish_Buyers_Account entry)throws SQLException {
		

    	connection=DBConnection.Connector();
		PreparedStatement preparedStatement=null;
		int resultSet;
		
		
		String addEntryQuery="INSERT INTO F_Fish_Buyers_Account(Date,Reason,To_Pay,Paid)"+" VALUES(?,?,?,?)";
		
		
		try {
			  
			preparedStatement=connection.prepareStatement(addEntryQuery);
			preparedStatement.setString(1, entry.getDate());
			preparedStatement.setString(2,entry.getReason());
			preparedStatement.setDouble(3, entry.getTo_Pay());
			preparedStatement.setDouble(4, entry.getPaid());
			resultSet= preparedStatement.executeUpdate();
			
			if (resultSet != 0) {

				return true;
			} else {
				return false;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}

		
	}
	
	//add uncleard entries
	
	public boolean addUnclearEntries(F_Fish_Buyers_Account_Uncleard Unentry)throws SQLException {
		

    	connection=DBConnection.Connector();
		PreparedStatement preparedStatement=null;
		int resultSet;
		
		
		String addEntryQuery="INSERT INTO F_Fish_Buyers_Account(Date,Reason,To_Pay,Paid)"+" VALUES(?,?,?,?)";
		
		
		try {
			  
			preparedStatement=connection.prepareStatement(addEntryQuery);
			preparedStatement.setString(1, Unentry.getDate());
			preparedStatement.setString(2,Unentry.getReason());
			preparedStatement.setDouble(3, Unentry.getTo_Pay());
			preparedStatement.setDouble(4, Unentry.getPaid());
			resultSet= preparedStatement.executeUpdate();
			
			if (resultSet != 0) {

				return true;
			} else {
				return false;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}

		
	}
	

}
