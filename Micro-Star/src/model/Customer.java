package model;

import java.io.Serializable;

public class Customer implements Serializable {

    protected int customerID;
    protected String fullName;
    protected String email;
    protected String contact;
    protected String serviceAddress;
    protected int accountNumber;
    protected String password;

    public Customer(int customerID, String fullName, String email, String contact, String serviceAddress, int accountNumber, String password) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.contact = contact;
        this.serviceAddress = serviceAddress;
        this.accountNumber = accountNumber;
        this.password = password;
    }

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Customer(Customer c) {
        this.customerID = c.customerID;
        this.fullName = c.fullName;
        this.email = c.email;
        this.contact = c.contact;
        this.serviceAddress = c.serviceAddress;
        this.accountNumber = c.accountNumber;
        this.password = c.password;
    }

    public Customer() {
        this.customerID = 1000;
        this.fullName = "fullName";
        this.email = "email";
        this.contact = "contact";
        this.serviceAddress = "serviceAddress";
        this.accountNumber = 100032;
        this.password = "password";
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer:" +
                "CustomerID: " + customerID +
                "FullName: '" + fullName + '\'' +
                "Email: " + email + '\'' +
                "Contact: " + contact + '\'' +
                "ServiceAddress: " + serviceAddress + '\'' +
                "AccountNumber: " + accountNumber +
                "Password: " + password + '\''
                ;
    }
}
