package com.bank.DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.bank.DTO.TransactionDetails;
import com.bank.util.DataBaseConnection;
public class TransactionDetailsDAO 
{
	final private static String transaction_details=
			"insert into transaction_details(Transaction_Type, Transaction_Amount, Transaction_Time, Transaction_Date, "
			+ "Balance_Amount, Transaction_Status, Customer_Account_Number) values(?,?,?,?,?,?,?)";
	public boolean insertTransactionDeatils(TransactionDetails td)
	{
		try
		{
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(transaction_details);
			ps.setString(1, td.getTransactionType());
			ps.setDouble(2, td.getTransactionAmount());
			ps.setTime(3, Time.valueOf(td.getTransactionTime()));
			ps.setDate(4, Date.valueOf(td.getTransactionDate()));
			ps.setDouble(5, td.getBalanceAmount());
			ps.setString(6, td.getTransactionStatus());
			ps.setLong(7, td.getCustomerAccountNumber());
			int result=ps.executeUpdate();
			if(result!=0)
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
	final private static String transaction="select * from transaction_details where Customer_Account_Number=?";
	public List<TransactionDetails> transactionBankStatement(long accNum)
	{
		try {
			Connection c=DataBaseConnection.forMySqlConnection();
			PreparedStatement ps=c.prepareStatement(transaction);
			ps.setLong(1, accNum);
			ResultSet rs=ps.executeQuery();
			List<TransactionDetails> ltd=new ArrayList<TransactionDetails>();
			while(rs.next())
			{
				TransactionDetails td=new TransactionDetails();				
				td.setTransactionId(rs.getInt("Transaction_ID"));
				td.setTransactionType(rs.getString("Transaction_Type"));
				td.setTransactionAmount(rs.getDouble("Transaction_Amount"));
				td.setTransactionTime(rs.getTime("Transaction_Time").toLocalTime());
				td.setTransactionDate(rs.getDate("Transaction_Date").toLocalDate());
				td.setBalanceAmount(rs.getDouble("Balance_Amount"));
				td.setTransactionStatus(rs.getString("Transaction_Status"));
				ltd.add(td);
			}
			return ltd;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
