package model;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "Accounts")
public class Accounts implements Serializable {

    private static final long serialVersionUID = -6631284529858975607L;

    @Id
    @Column(name = "account_id")
    protected String customerID;

    @Column(name = "payment_status")
    String paymentStatus;

    @Column(name = "amount_due")
    double amountDue;

    @Column(name = "payment_due_date")
    java.util.Date paymentDate;


    @Column(name = "created_at")
    java.util.Date createdAt;


    public Accounts(String customerID, String paymentStatus, double amountDue, java.util.Date paymentDate, java.util.Date createdAt) {
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

    public java.util.Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(java.util.Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "customerID='" + customerID + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", amountDue=" + amountDue +
                ", paymentDate=" + paymentDate +
                ", createdAt=" + createdAt +
                '}';
    }
}

