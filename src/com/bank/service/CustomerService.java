package com.bank.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.bank.DAO.CustomerDAO;
import com.bank.DAO.TransactionDetailsDAO;
import com.bank.DTO.CustomerDetails;
import com.bank.DTO.TransactionDetails;
import com.bank.exception.CustomerDataInvalidException;

public class CustomerService {
	CustomerDAO customerDAO = new CustomerDAO();
	Scanner sc = new Scanner(System.in);
	TransactionDetailsDAO transactionDetailsDAO = new TransactionDetailsDAO();

	public void customerRegistration() {
		CustomerDetails cd = new CustomerDetails();
		List<CustomerDetails> allCustomerDetails = customerDAO.getAllCustomerDetails();
		System.out.println("Enter Customer Name:");
		String name = sc.nextLine();
		while (true) {
		    try {
		        if (name.length() >= 3) {
		            boolean isValidName = true;
		            for (int i = 0; i < name.length(); i++) {
		                char ch = name.charAt(i);
		                if (!Character.isLetter(ch) && ch != ' ') {
		                    isValidName = false;
		                    break;
		                }
		            }
		            if (isValidName) {
		                cd.setName(name);
		                break;
		            } else {
		                throw new CustomerDataInvalidException("Name should contain only letters and spaces");
		            }
		        } else {
		            throw new CustomerDataInvalidException("Name must be at least 3 characters long");
		        }
		    } catch (CustomerDataInvalidException e) {
		        System.out.println(e.getExceptionMsg());
		        System.out.println("Re-enter Customer Name:");
		        name = sc.nextLine();
		    }
		}
		System.out.println("Enter Customer Email ID");
		String emailID = sc.next();

		while (true) {
			try {

				if (emailID.endsWith("@gmail.com")) {
					String namePart = emailID.substring(0, emailID.indexOf("@"));
					if (namePart.length() > 4) {
						boolean isValidName = true;
						for (int i = 0; i < namePart.length(); i++) {
							char ch = namePart.charAt(i);
							if (!Character.isLetterOrDigit(ch)) {
								isValidName = false;
								break;
							}
						}
						if (!isValidName) {
							throw new CustomerDataInvalidException("Name part should contain only letters and digits");
						}
						boolean found = false;
						for (CustomerDetails s : allCustomerDetails) {
							if (s.getEmailid().equalsIgnoreCase(emailID)) {
								found = true;
								break;
							}
						}
						if (found) {
							throw new CustomerDataInvalidException("Email already exists");
						}
						cd.setEmailid(emailID);
						break;
					} else {
						throw new CustomerDataInvalidException("Name part must be more than 4 characters");
					}
				} else {
					throw new CustomerDataInvalidException("Email must end with '@gmail.com'");
				}
			} catch (CustomerDataInvalidException e) {
				System.out.println(e.getExceptionMsg());
				System.out.println("Enter another email ID:");
				emailID = sc.next();
			}
		}
		System.out.println("Enter Customer Mobile Number");
		long mobileNumber = sc.nextLong();
		boolean sate = true;
		while (sate) {
			try {
				if (mobileNumber >= 6000000000l && mobileNumber <= 9999999999l) {
					cd.setMobileNumber(mobileNumber);
					sate = false;
				} else {
					throw new CustomerDataInvalidException("Invalid Number");
				}
			} catch (CustomerDataInvalidException e) {
				System.out.println(e.getExceptionMsg());
				System.out.println("Re-enter the mobile number");
				mobileNumber = sc.nextLong();
			}
		}
		System.out.println("Enter Customer Aadhar Number");
		long aadharNumber = sc.nextLong();
		boolean state = true;
		while (state) {
			try {
				if (aadharNumber >= 100000000000l && aadharNumber <= 999999999999l) {
					cd.setAadharNumber(aadharNumber);
					state = false;
				} else {
					throw new CustomerDataInvalidException("Invalid Aadhar Number");
				}
			} catch (CustomerDataInvalidException e) {
				System.out.println(e.getExceptionMsg());
				System.out.println("Re-Enter Aadhar Number");
				aadharNumber = sc.nextLong();
			}
		}
		System.out.println("Enter Customer Address");
		String address = sc.next();
		System.out.println("Enter Customer Gender (Male / Female / Other)");
		String gender = sc.next();

		while (true) {
		    try {
		        String genderLower = gender.toLowerCase();

		        if (genderLower.equals("male") || genderLower.equals("female") || genderLower.equals("other")) {
		            gender = gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
		            cd.setGender(gender);
		            break;
		        } else {
		            throw new CustomerDataInvalidException("Invalid gender. Please enter Male, Female, or Other.");
		        }
		    } catch (CustomerDataInvalidException e) {
		        System.out.println(e.getExceptionMsg());
		        System.out.println("Re-enter Customer Gender:");
		        gender = sc.next();
		    }
		}

		System.out.println("Enter Customer Amount");
		double amount = sc.nextDouble();
		if (amount > 0) {
			cd.setAmount(amount);
		} else {
			throw new CustomerDataInvalidException("Invalid Amount");
		}
		cd.setAddress(address);
		cd.setName(name);
		cd.setGender(gender);
		if (customerDAO.insertCustomerDetails(cd)) {
			System.out.println("Data inserted");
		} else {
			System.out.println("Server Error");
		}
	}

	public int pin;

	public void customerLogin() {
		System.out.println("Enter the EmailID or Account Number");
		String emailId = sc.next();
		System.out.println("Enter the Pin number");
		this.pin = sc.nextInt();
		CustomerDetails customerDetails = customerDAO.loginVerify(emailId, pin);
		if (customerDetails != null) {
			String[] captchadata = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
					"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
					"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3",
					"4", "5", "6", "7", "8", "9" };
			String captcha = "";
			for (int i = 0; i < 4; i++) {
				Random random = new Random();
				int r = random.nextInt(captchadata.length);
				captcha += captchadata[r];
			}
			System.out.println("Captcha : " + captcha);
			System.out.println("Enter the captcha");
			String userCaptcha = sc.next();
			if (captcha.equals(userCaptcha)) {
				if (customerDetails.getGender().equalsIgnoreCase("Male")) {
					System.out.println("Hello Mister : " + customerDetails.getName());
					customerOperations();
				} else {
					System.out.println("Hello Miss : " + customerDetails.getName());
					customerOperations();
				}
			} else {
				System.out.println("Invalid captcha");
			}
		} else {
			System.out.println("Invalid Credentials...........");
		}
	}

	public void customerOperations() {
		Scanner sc = new Scanner(System.in);
		boolean loop=true;
		while(loop)
		{
			System.out.println("Enter " + "\n 1.Credit " + "\n 2.Debit " + "\n 3.Check Statement " + "\n 4.Check Balance "
				+ "\n 5.Update Pin" +"\n 6.Close Account"+"\n 7.Exit");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Credit");
				creditOperation();
				break;
			case 2:
				System.out.println("Debit");
				debitOperation();
				break;
			case 3:
				System.out.println("Check Statement");
				transactionStatements();
				break;
			case 4:
				System.out.println("Check Balance");
				checkBalance();
				break;
			case 5:
				updatingPin();
				break;
			case 6:
				System.out.println("Close Account");
				closeAccountOperation();
				break;
				
			case 7:
				System.out.println("Thank you.....!");
				loop=false;
				break;
			default:
				System.out.println("Inavalid Option..");
				break;
			}
		}
	}

	public void checkBalance() {
		System.out.println("Enter the pin");
		int pin = sc.nextInt();
		CustomerDetails customerDetails = customerDAO.checkBalance(pin);
		if (customerDetails != null) {
			System.out.println("Name :- " + customerDetails.getName());
			System.out.println("Balance :- " + customerDetails.getAmount());
		} else {
			System.out.println("Incorrect Pin number");
		}
	}

	public void debitOperation() {
		System.out.println("Enter account number");
		long accNum = sc.nextLong();
		System.out.println("Enter the pin");
		int pin = sc.nextInt();
		if (this.pin == pin) {
			CustomerDetails cd = customerDAO.selectCustomerDetailsByUsingAccountAndPINNumber(accNum, pin);
			if (cd != null) {
				System.out.println("Enter the amount");
				double amount = sc.nextDouble();
				if (amount >= 0) {
					double balance = cd.getAmount();
					if (amount <= balance) {
						double money = balance - amount;
						if (customerDAO.updateAmount(money,accNum, pin)) {
							TransactionDetails transactionDetails = new TransactionDetails();
							transactionDetails.setTransactionType("DEBIT");
							transactionDetails.setTransactionAmount(amount);
							transactionDetails.setBalanceAmount(money);
							transactionDetails.setTransactionDate(LocalDate.now());
							transactionDetails.setTransactionTime(LocalTime.now());
							transactionDetails.setCustomerAccountNumber(accNum);
							transactionDetails.setTransactionStatus("Transfered");
							transactionDetailsDAO.insertTransactionDeatils(transactionDetails);
							System.out.println("Updated");
						} else {
							System.out.println("Server Error");
						}
					} else {
						System.out.println("In-Sufficient Balance........");
					}

				} else {
					throw new CustomerDataInvalidException("Invalid Amount");
				}

			} else {
				System.out.println("Invalid credentials");
			}
		} else {
			System.out.println("You can access only your bank account");
		}
	}

	public void creditOperation() {
		System.out.println("Enter account number");
		long accNum = sc.nextLong();
		System.out.println("Enter the pin");
		int pin = sc.nextInt();
		if (this.pin == pin) {
			CustomerDetails cd = customerDAO.selectCustomerDetailsByUsingAccountAndPINNumber(accNum, pin);
			if (cd != null) {
				System.out.println("Enter the amount");
				double amount = sc.nextDouble();
				if (amount >= 0) {
					double balance = cd.getAmount();
					double money = balance + amount;
					if (customerDAO.updateAmount(money,accNum, pin)) {
						TransactionDetails transactionDetails = new TransactionDetails();
						transactionDetails.setTransactionType("CREDIT");
						transactionDetails.setTransactionAmount(amount);
						transactionDetails.setBalanceAmount(money);
						transactionDetails.setTransactionDate(LocalDate.now());
						transactionDetails.setTransactionTime(LocalTime.now());
						transactionDetails.setCustomerAccountNumber(accNum);
						transactionDetails.setTransactionStatus("Transfered");
						transactionDetailsDAO.insertTransactionDeatils(transactionDetails);
						System.out.println("Updated");
					} else {
						System.out.println("Server Error");
					}
				} else {
					throw new CustomerDataInvalidException("Invalid Amount");
				}
			} else {
				System.out.println("Invalid credentials");
			}
		} else {
			System.out.println("You can access only your bank account");
		}
	}

	public void closeAccountOperation() {
		System.out.println("Enter account number");
		long accNum = sc.nextLong();
		System.out.println("Enter the pin");
		int pin = sc.nextInt();
		if (this.pin == pin) {
			if (customerDAO.closeAccount(pin)) {
				LocalDate date = LocalDate.now();
				CustomerDetails cd = customerDAO.selectCustomerDetailsByUsingAccountAndPINNumber(accNum, pin);
				System.out.println("Final balance refunded: " + cd.getAmount() + "Rs.");
				System.out.println("Your account " + accNum + " has been successfully closed on " + date + ".");
				System.out.println("Thank you for banking with us!");
			}
		} else {
			System.out.println("You can access only your bank account");
		}
	}

	public void transactionStatements() {
		System.out.println("Enter account number");
		long accNum = sc.nextLong();
		System.out.println("Enter the pin");
		int pin = sc.nextInt();
		if (this.pin == pin) {
			TransactionDetailsDAO tdo = new TransactionDetailsDAO();
			List<TransactionDetails> transaction = tdo.transactionBankStatement(accNum);
			for (TransactionDetails td : transaction) {
				System.out.println(td);
			}

		} else {
			System.out.println("You can access only your bank account");
		}
	}
	public void updatingPin() {
		System.out.println("Enter your Account Number");
		long accountNumber = sc.nextLong();
		System.out.println("Enter your Old Pin");
		int oldPin = sc.nextInt();
		if(this.pin==oldPin )
		{
			boolean status = true;
			while(status)
			{
				System.out.println("Enter your New Pin");
				int firstPin = sc.nextInt();
				System.out.println("Confirm Your Pin");
				int secondPin = sc.nextInt();
				if(firstPin==secondPin)
				{
					if(customerDAO.updatePinUsingAccountNumber(accountNumber, secondPin))
					{
						System.out.println("Pin Updated Successfully");
						this.pin=secondPin;
						status =false;
					}
				}
				else
				{
					System.err.println("Password DoesNot Matched");
				}
			}

		}
	}
}
