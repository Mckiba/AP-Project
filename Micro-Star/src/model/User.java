package model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -7457037016119921266L;

   protected String customerID;
    protected String password ;
    protected  String email;
    protected String accountType;

    public User(String password, String customerID, String accountType) {

        this.password = password;
        this.customerID = customerID;
        this.accountType = accountType;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
