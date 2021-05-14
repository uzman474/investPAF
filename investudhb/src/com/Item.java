package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {
	
	private Connection connect()  
	{   
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con =
		 DriverManager.getConnection(
		 "jdbc:mysql://127.0.0.1:3306/paf?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		 }
		catch (Exception e)   
		{    
			e.printStackTrace();   
		}  
	  return con;  
	}
	
	public String readItems()  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for reading.";   
			}  
			
		
		// Prepare the html table to be displayed    
		output = "<table border='1'><tr><th>Invest Name</th>"
				+ "<th>Researcher ID</th><th>invest Price</th>"      
				+ "<th>invest Details</th>"
				+ "<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from items";    
		Statement stmt = con.createStatement();    
		ResultSet rs = stmt.executeQuery(query);
		
	   // iterate through the rows in the result set    
		while (rs.next())    
		{     
			String investID = Integer.toString(rs.getInt("investID"));     
			String investName = rs.getString("investName");
			String researcherID = rs.getString("researcherID");     
			String investPrice = Double.toString(rs.getDouble("investPrice"));     
			String investDet = rs.getString("investDet")
					;  
		    // Add into the html table     
			output += "<tr><td><input id='hidInvestIDUpdate' "
					+ "name='hidInvestIDUpdate'          "
					+ "type='hidden' value='" + investID       
					+ "'>" + investName + "</td>";     
			output += "<td>" + researcherID + "</td>";     
			output += "<td>" + investPrice + "</td>";     
			output += "<td>" + investDet + "</td>";
			
		    // buttons     
			output += "<td><input name='btnUpdate' "
					+ "type='button' value='Update'"
					+ "class='btnUpdate btn btn-secondary'></td>"       
					+ "<td><input name='btnRemove' "
					+ "type='button' value='Remove'"
					+ "class='btnRemove btn btn-danger' "
					+ "data-investid='"       
					+ investID + "'>" + "</td></tr>";    
		}  
		con.close();
		
		// Complete the html table    
		output += "</table>";   
	}  
	catch (Exception e)   
	{    
		output = "Error while reading the items.";    
		System.err.println(e.getMessage());   
	}
	
	return output;  
	}
	
	public String insertItem(String name, String rid, String price, String det)  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for inserting.";    
			}  
			
			// create a prepared statement   
			String query = " insert into items(`investID`,`investName`,`reseacherID`,`investPrice`,`investDet`)"+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, name);    
			preparedStmt.setString(3, rid);    
			preparedStmt.setDouble(4, Double.parseDouble(price));    
			preparedStmt.setString(5, det);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close();
			
			String newItems = readItems();    
			output = "{\"status\":\"success\", \"data\": \"" +        
						newItems + "\"}";   
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while inserting the item.\"}";    
			System.err.println(e.getMessage());   
		}
		
		return output;  
	}
	
	
	public String updateItem(String ID, String name, String rid, String price, String det)  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for updating.";    }  
			
	   // create a prepared statement    
			String query = "UPDATE items SET investName=?,researcherID=?,investPrice=?,investDet=?WHERE investID=?";
	   
	   PreparedStatement preparedStmt = con.prepareStatement(query);
	   
	   // binding values    
	   preparedStmt.setString(1, name);    
	   preparedStmt.setString(2, rid);    
	   preparedStmt.setDouble(3, Double.parseDouble(price));    
	   preparedStmt.setString(4, det);    
	   preparedStmt.setInt(5, Integer.parseInt(ID));
	   
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close();
	   
	   String newItems = readItems();    
	   output = "{\"status\":\"success\", \"data\": \"" +    
	   newItems + "\"}";   
	   
	}   
		catch (Exception e)   
   {   
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while updating the item.\"}";    
			System.err.println(e.getMessage());   
   	}  
	return output;  
}
	
	
	public String deleteItem(String investID)  
	{   
		String output = "";
		
		try   
		{    
			Connection con = connect();
			
			if (con == null)    
			{     
				return "Error while connecting to the database for deleting.";    
			}
			
			// create a prepared statement    
			String query = "delete from items where investID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(investID));
			
			// execute the statement    
			preparedStmt.execute();    
			con.close();
			
			String newItems = readItems();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					newItems + "\"}";   
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while deleting the item.\"}";    
			System.err.println(e.getMessage());   
		}
		
		return output;  
	} 
					
		

}
