package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.util.*;

import com.bank.DTO.CustomerDetails;

public class CustomerDAO 
{
	final private static String insert="insert into customer_details(Customer_Name, "
			+ "Customer_EmailID, Customer_Mobile_Number, Customer_Aadhar_Number,"
			+ " Customer_Address, Customer_Gender,Customer_Amount,Customer_Status) values(?,?,?,?,?,?,?,?)";
	final private static String selectDetails="select * from customer_details";
	final private static String statusDetails="select * from customer_details where Customer_Status=?";
	
	final private static String updatePinAndAccount="update customer_details "
			+ "set Customer_Account_Number=?, Customer_Pin=?,Customer_Status=? "
			+ "where Customer_Mobile_Number=?";
	public boolean insertCustomerDetails(CustomerDetails cd)
	{
		try 
		{
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(insert);
			ps.setString(1, cd.getName());
			ps.setString(2, cd.getEmailid());
			ps.setLong(3, cd.getMobileNumber());
			ps.setLong(4, cd.getAadharNumber());
			ps.setString(5, cd.getAddress());
			ps.setString(6, cd.getGender());
			ps.setDouble(7, cd.getAmount());
			ps.setString(8, "Pending");
			int result=ps.executeUpdate();
			if(result!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	public List<CustomerDetails> getAllCustomerDetails()
	{
		try 
		{
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(selectDetails);
			ResultSet rs=ps.executeQuery();
			List<CustomerDetails> lcd=new ArrayList<CustomerDetails>();
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					CustomerDetails cd=new CustomerDetails();
					cd.setName(rs.getString("Customer_Name"));
					cd.setEmailid(rs.getString("Customer_EmailID"));
					cd.setMobileNumber(rs.getLong("Customer_Mobile_Number"));
					cd.setAadharNumber(rs.getLong("Customer_Aadhar_Number"));
					cd.setAddress(rs.getString("Customer_Address"));
					cd.setGender(rs.getString("Customer_Gender"));
					cd.setAmount(rs.getDouble("Customer_Amount"));
					cd.setStatus(rs.getString("Customer_Status"));
					lcd.add(cd);
				}
			}
			return lcd;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public List<CustomerDetails> getAllCustomerDetailsByStatus(String status)
	{
		try
		{
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(statusDetails);
			ps.setString(1, status);
			ResultSet rs=ps.executeQuery();
			List<CustomerDetails> lcd=new ArrayList<CustomerDetails>();
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					CustomerDetails cd=new CustomerDetails();
					cd.setName(rs.getString("Customer_Name"));
					cd.setEmailid(rs.getString("Customer_EmailID"));
					cd.setMobileNumber(rs.getLong("Customer_Mobile_Number"));
					cd.setAadharNumber(rs.getLong("Customer_Aadhar_Number"));
					cd.setAddress(rs.getString("Customer_Address"));
					cd.setGender(rs.getString("Customer_Gender"));
					cd.setAmount(rs.getDouble("Customer_Amount"));
					cd.setStatus(rs.getString("Customer_Status"));
					lcd.add(cd);
				}
			}
			return lcd;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public void updateAccountAndPinByUsingId(List<CustomerDetails> pd)
	{
		
		try {
			Connection c = DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(updatePinAndAccount);
			
			for(CustomerDetails cd:pd)
			{
				ps.setLong(1,cd.getAccoutNumber());
				ps.setInt(2,cd.getPin() );
				ps.setString(3, "Accepted");
				ps.setLong(4, cd.getMobileNumber());
				ps.addBatch();
			}
			System.out.println(ps);
			@SuppressWarnings("unused")
			int[] batch=ps.executeBatch();
			System.out.println("Updated");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	final private static String customer_login="select * from customer_details where (Customer_EmailID=? or Customer_Account_Number=?) and Customer_Pin=?";
	public CustomerDetails loginVerify(String emailId,int pin)
	{
		try {
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(customer_login);
			ps.setString(1, emailId);
			ps.setString(2, emailId);
			ps.setInt(3, pin);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setName(rs.getString("Customer_Name"));
				customerDetails.setGender(rs.getString("Customer_Gender"));
				return customerDetails;
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}
	final private static String balance="select * from customer_details where Customer_Pin=?";
	public CustomerDetails checkBalance(int pin)
	{
		
		Connection c;
		try {
			c = DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(balance);
			ps.setInt(1, pin);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setName(rs.getString("Customer_Name"));
				customerDetails.setAmount(rs.getDouble("Customer_Amount"));
				return customerDetails;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	final private static String customer_details_Using_acc_pin="select * from customer_details where Customer_Account_Number=? and Customer_Pin=?";
	public CustomerDetails selectCustomerDetailsByUsingAccountAndPINNumber(long accountNumber,int pin)
	{
		try {
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(customer_details_Using_acc_pin);
			ps.setLong(1, accountNumber);
			ps.setInt(2, pin);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setName(rs.getString("Customer_Name"));
				customerDetails.setAmount(rs.getDouble("Customer_Amount"));
				return customerDetails;
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return null;
	}
	final private static String update_Amount="update customer_details set Customer_Amount=? where Customer_Pin=?";
	public boolean updateAmount(double amount,int pin)
	{
		try
		{
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps2=c.prepareStatement(update_Amount);
			ps2.setDouble(1, amount);
			ps2.setInt(2, pin);
			int result=ps2.executeUpdate();
			if(result>0)
			{
				return true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	public void creditAmount(double amount,int pin)
	{
		try
		{
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps1=c.prepareStatement(balance);
			ps1.setInt(1, pin);
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
			{
				PreparedStatement ps2=c.prepareStatement(update_Amount);
				double balance=rs.getDouble("Customer_Amount")+amount;
				ps2.setDouble(1, balance);
				ps2.setInt(2, pin);
				int result=ps2.executeUpdate();
				if(result>0)
				{
					System.out.println("Amount Debited Successfully...........");
					System.out.println("Account balnce is : "+balance+"Rs");
				}
				else
				{
					System.out.println("Invalid Pin Number");
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
	}
	final private static String close_account="update customer_details set Customer_Status=? where Customer_Pin=?";
	public boolean closeAccount(int pin)
	{
		try
		{
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps2=c.prepareStatement(close_account);
			ps2.setString(1, "Close");
			ps2.setInt(2, pin);
			int result=ps2.executeUpdate();
			if(result>0)
			{
				return true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
}
