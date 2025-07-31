package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bank.DAO.AdminDAO;
import com.bank.DAO.CustomerDAO;
import com.bank.DTO.CustomerDetails;

public class AdminService 
{
	AdminDAO ad=new AdminDAO();
	Scanner sc = new Scanner(System.in);
	CustomerDAO cd=new CustomerDAO();
	public void adminLogIn()
	{
		System.out.println("Enter the EmailID");
		String emailId=sc.next();
		System.out.println("Enter the password");
		String password=sc.next();
		if(ad.selectAdminDetailsByUsingEmailIdAndPassword(emailId, password))
		{
			System.out.println("Enter \n 1.To Get Customer Details"
					+ "\n 2.To Get All Account Request Details \n"
					+ "3.To Get All Account Closing Details");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("All Customer Details");
				List<CustomerDetails> l=cd.getAllCustomerDetails();
				for(CustomerDetails cd : l)
				{
					System.out.println("Customer Name : "+cd.getName());
					System.out.println("Customer EmailId : "+cd.getEmailid());
					long mobile=cd.getMobileNumber();
					long aadhar=cd.getAadharNumber();
					String mn=""+mobile;
					String an=""+aadhar;
					System.out.println("Mobile Number : "+mn.subSequence(0, 3)+"****"+mn.subSequence(7, 10));
					System.out.println(" Number : "+an.subSequence(0, 4)+"****"+an.subSequence(8, 12));
					System.out.println("Customer Address : "+cd.getAddress());
					System.out.println("Customer Gender : "+cd.getGender());
					System.out.println("Customer Amount : "+cd.getAmount());
					System.out.println("****----****----****----****----****----****----");
				}
				break;
			case 2:
				List<CustomerDetails> pd=cd.getAllCustomerDetailsByStatus("Pending");
				long lq=pd.stream().count();
				
				if(lq>0)
				{
					System.out.println("-----All Account Request Details-----");
					System.out.println("-----All Customer Details-----");
					for(CustomerDetails cd : pd)
					{
						
							System.out.println("Customer Name : "+cd.getName());
							System.out.println("Customer EmailId : "+cd.getEmailid());
							long mobile=cd.getMobileNumber();
							long aadhar=cd.getAadharNumber();
							String mn=""+mobile;
							String an=""+aadhar;
							System.out.println("Mobile Number : "+mn.subSequence(0, 3)+"****"+mn.subSequence(7, 10));
							System.out.println(" Number : "+an.subSequence(0, 4)+"****"+an.subSequence(8, 12));
							System.out.println("Customer Address : "+cd.getAddress());
							System.out.println("Customer Gender : "+cd.getGender());
							System.out.println("Customer Amount : "+cd.getAmount());
							System.out.println("****----****----****----****----****----****----");
						
					}
					System.out.println("Enter \n 1.To generate account number for one person \n "
							+ "2.To Accept all to generate the account number");
					switch (sc.nextInt()) 
					{
					case 1: {
						sc.nextLine(); 
						System.out.print("Enter the Email ID of the customer to approve: ");
						String email = sc.next();
						boolean found = false;
						for (CustomerDetails customer : pd) {
							if (customer.getEmailid().equalsIgnoreCase(email)) {
								Random r = new Random();

								int acn = r.nextInt(10000000);
								if (acn < 1000000)
									acn += 1000000;
								else if (acn > 9999999)
									acn -= 1000000;
								customer.setAccoutNumber(acn);

								int pin = r.nextInt(10000);
								if (pin < 1000)
									pin += 1000;
								else if (pin > 9999)
									pin -= 1000;
								customer.setPin(pin);

								customer.setStatus("Accepted");

								List<CustomerDetails> single = new ArrayList<>();
								single.add(customer);
								cd.updateAccountAndPinByUsingId(single);

								System.out.println("Account Number and PIN generated successfully!");
								System.out.println("Account No: " + acn);
								System.out.println("PIN: " + pin);
								found = true;
								break;
							}
						}

						if (!found) {
							System.out.println("No pending customer found with the given Email ID.");
						}

						break;

					case 2:
					{
						List<CustomerDetails> acceptedDetails=new ArrayList<CustomerDetails>();
						for(int i=0;i<pd.size();i++)
						{
							CustomerDetails customerDetails=pd.get(i);
							Random r=new Random();
							int acn=r.nextInt(10000000);
							if(acn<1000000)
							{
								acn +=1000000;
							}
							else if(acn >9999999)
							{
								acn -=1000000;
							}
							customerDetails.setAccoutNumber(acn);
							int pin=r.nextInt(10000);
							if(pin <1000)
							{
								pin +=1000;
							}
							else if(pin > 9999)
							{
								pin -=1000;
							}
							customerDetails.setPin(pin);
							customerDetails.setStatus("Accepted");
							acceptedDetails.add(customerDetails);
						}
						cd.updateAccountAndPinByUsingId(pd);
						System.out.println(acceptedDetails);
					}
					break;
					
					default:System.out.println("Enter valid choice");
						break;
					}	
				}
				else
				{
					System.out.println("No pending details");
				}
				break;
			case 3:
				System.out.println("All Closed Account Details");
				List<CustomerDetails> closed=cd.getAllCustomerDetailsByStatus("Closed");
				long cq=closed.stream().count();
				
				if(cq>0)
				{
					System.out.println("-----All Account Request Details-----");
					System.out.println("-----All Customer Details-----");
					for(CustomerDetails cd : closed)
					{
						
							System.out.println("Customer Name : "+cd.getName());
							System.out.println("Customer EmailId : "+cd.getEmailid());
							long mobile=cd.getMobileNumber();
							long aadhar=cd.getAadharNumber();
							String mn=""+mobile;
							String an=""+aadhar;
							System.out.println("Mobile Number : "+mn.subSequence(0, 3)+"****"+mn.subSequence(7, 10));
							System.out.println(" Number : "+an.subSequence(0, 4)+"****"+an.subSequence(8, 12));
							System.out.println("Customer Address : "+cd.getAddress());
							System.out.println("Customer Gender : "+cd.getGender());
							System.out.println("Customer Amount : "+cd.getAmount());
							System.out.println("****----****----****----****----****----****----");
						
					}
				}
				else
				{
					System.out.println("No Closed Accounts");
				}
				break;
			default:
				System.out.println("Invalid Option.....!");
				break;
			}
		}
		else
		{
			System.out.println("Invalid EmailID and password");
		}
	}
}
