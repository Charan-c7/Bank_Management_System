package com.bank.main;

import java.util.Scanner;
import com.bank.service.AdminService;
import com.bank.service.CustomerService;

public class BankMainClass 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		CustomerService cs=new CustomerService();
		AdminService as=new AdminService();
		System.out.println("Enter \n 1.For Customer Registration \n 2.For Customer Login "
				+ "\n 3.For Admin Login");
		switch (sc.nextInt())
		{
		case 1:
			System.out.println("Customer Registration");
			cs.customerRegistration();
			break;
		case 2:
			System.out.println("Customer LogIn");
			cs.customerLogin();
			break;
		case 3:
			System.out.println("Admin Login");
			as.adminLogIn();
			break;
		default:System.out.println("Invalid Option");
			break;
		}
		sc.close();
	}
}