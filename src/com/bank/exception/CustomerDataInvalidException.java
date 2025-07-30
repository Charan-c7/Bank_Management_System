package com.bank.exception;

@SuppressWarnings("serial")
public class CustomerDataInvalidException extends RuntimeException
{
	private String exceptionMsg;
	public CustomerDataInvalidException() {}
	public CustomerDataInvalidException(String exceptionMsg) 
	{
		this.exceptionMsg = exceptionMsg;
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	@Override
	public String toString() {
		return getClass()+" : "+exceptionMsg;
	}
	
	
}
