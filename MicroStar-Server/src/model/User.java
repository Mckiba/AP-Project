package model;

import factories.SessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Table(name = "Customers")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -7457037016119921266L;

    @Id
    protected String customerID;

    @Column(name = "full_name")
    protected String fullName;

    @Column(name = "email")
    protected String email;

    @Column(name = "contact")
    protected String contact;

    @Column(name = "password")
    protected String password;

    @Column(name = "accountType")
    protected String accountType;

    public User(String customerID, String fullName, String email, String contact, String password, String accouuntType) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.accountType = accouuntType;
    }

    public User(String email, String password, String accountType) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public User() {
        this.customerID = "0000";
        this.fullName = "fullName";
        this.email = "email";
        this.contact = "contact";
        this.password = "password";
    }

    public User(User u) {
        this.customerID = u.customerID;
        this.fullName = u.fullName;
        this.email = u.email;
        this.contact = u.contact;
        this.password = u.password;
        this.accountType = u.accountType;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void create() {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(this);
        transaction.commit();
        session.close();
    }

    public void update() {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, this.customerID);
        user.setFullName(this.fullName);
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete() {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, this.customerID);
        session.delete(user);
        transaction.commit();
        session.close();
    }
}

