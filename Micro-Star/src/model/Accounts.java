
package model;

//import java.util.Date;

public class Accounts 
{
	protected int customerID; 
	String paymentstatus; 
	double amountDue;
	date paymentdate; 
	boolean productStatus;
	date createdAt; 
	
	public Accounts(int customerID, String paymentstatus, double amountDue, date paymentdate, boolean productStatus, date createdAt)
	{
		this.customerID= customerID;
		this.paymentstatus= paymentstatus;
		this.amountDue= amountDue; 
		this.paymentdate= paymentdate;
		this.productStatus= productStatus;
		this.createdAt= createdAt;
		
	}
	
	public Accounts(Accounts a)
	{
		this.customerID = a.customerID;
		this.paymentstatus= a.paymentstatus;
		this.amountDue= a.amountDue;
		this.paymentdate= a.paymentdate; 
		this.productStatus= a.productStatus;
		this.paymentdate= a.paymentdate;
		this.productStatus= a.productStatus;
		this.createdAt= a.createdAt;
	}
	
	public Accounts()
	{
		this.customerID= 1234;
		this.paymentstatus= "Paid";
		this.amountDue= 100.05;
		this.paymentdate= date.now;
		this.productStatus= true;
		this.createdAt= date.now;		
		
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString()
	{
		return "Accounts{"+
				"customerID:"+ customerID + ",Payment Status:"+ paymentstatus +
				",Amount Due:"+ amountDue + ",Payment Date:"+ paymentdate +",Product Status"+ productStatus+
				",Created At"+createdAt;
	}
	
}
