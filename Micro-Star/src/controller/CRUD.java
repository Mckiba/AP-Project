package controller;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import javax.swing.JOptionPane;

public class CRUD {
	
	private static Connection connect = null;
	public Connection getDatabaseConnection()
	{
		if(connect == null) {
			try
			{
				String url = "jdbc:mysql://localhost:3306/employee";
				connect = DriverManager.getConnection(url, "root","");
				
				JOptionPane.showMessageDialog(null,"DB Connection Established", "CONNECTON STATUS", JOptionPane.INFORMATION_MESSAGE);
				
			}
			catch(SQLException ex)
			{
				JOptionPane.showMessageDialog(null,"Could not connect to database\n" + ex, "Connection Failure", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		return connect;
	}
}
