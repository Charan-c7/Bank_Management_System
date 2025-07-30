package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.util.DataBaseConnection;

public class AdminDAO
{
	private static final String admin_login="select * from admin_details where "
			+ "Admin_EmailID=? and  Admin_Password=?";
	public boolean selectAdminDetailsByUsingEmailIdAndPassword(String emailId,String password)
	{
		try {
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(admin_login);
			ps.setString(1, emailId);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst())
			{
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
}
