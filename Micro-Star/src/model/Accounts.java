package model;

//import java.util.Date;

import java.io.Serializable;
import java.util.Date;

public class Accounts implements Serializable {

    private static final long serialVersionUID = -6631284529858975607L;

    protected String customerID;
    String paymentStatus;
    double amountDue;
    Date paymentDate;
    Date createdAt;

    public Accounts(String customerID, String paymentStatus, double amountDue, Date paymentDate, Date createdAt) {
        this.customerID = customerID;
        this.paymentStatus = paymentStatus;
        this.amountDue = amountDue;
        this.paymentDate = paymentDate;
        this.createdAt = createdAt;
    }


    public Accounts(Accounts a) {
        this.customerID = a.customerID;
        this.paymentStatus = a.paymentStatus;
        this.amountDue = a.amountDue;
        this.paymentDate = a.paymentDate;
        this.createdAt = a.createdAt;
    }

    public Accounts() {

    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}