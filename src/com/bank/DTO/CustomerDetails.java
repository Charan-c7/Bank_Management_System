package com.bank.DTO;

public class CustomerDetails 
{
	private String name;
	private String emailid;
	private long mobileNumber;
	private long aadharNumber;
	private String address;
	private String gender;
	private long accoutNumber;
	private double amount;
	private int pin;
	private String status;
	public CustomerDetails() {}

	public CustomerDetails(String name, String emailid, long mobileNumber, long aadharNumber, String address,
			String gender, long accoutNumber, double amount, int pin,String status) 
	{	
		this.name = name;
		this.emailid = emailid;
		this.mobileNumber = mobileNumber;
		this.aadharNumber = aadharNumber;
		this.address = address;
		this.gender = gender;
		this.accoutNumber = accoutNumber;
		this.amount = amount;
		this.pin = pin;
		this.status=status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getAccoutNumber() {
		return accoutNumber;
	}

	public void setAccoutNumber(long accoutNumber) {
		this.accoutNumber = accoutNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerDetails [name=" + name + ", emailid=" + emailid + ", mobileNumber=" + mobileNumber
				+ ", aadharNumber=" + aadharNumber + ", address=" + address + ", gender=" + gender + ", accoutNumber="
				+ accoutNumber + ", amount=" + amount + ", pin=" + pin + ", status=" + status + "]";
	}
	
}
