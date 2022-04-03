package model;

import java.io.Serializable;
import java.util.Date;

public class Complaints implements Serializable {

    private static final long serialVersionUID = 4090151075057566008L;

    protected String id;
    protected String customerID;
    protected String category;
    protected String response;
    protected String responseProvider;
    protected Date responseDate;
    protected String issueDetails;

    public Complaints(String id, String customerID, String category, String response, String responseProvider, Date responseDate, String issueDetails) {
        this.id = id;
        this.customerID = customerID;
        this.category = category;
        this.response = response;
        this.responseProvider = responseProvider;
        this.responseDate = responseDate;
        this.issueDetails = issueDetails;
    }
    public Complaints(Complaints c) {
        this.id = c.id;
        this.customerID = c.customerID;
        this.category = c.category;
        this.response = c.response;
        this.responseProvider = c.responseProvider;
        this.responseDate = c.responseDate;
        this.issueDetails = c.issueDetails;
    }

    public Complaints() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseProvider() {
        return responseProvider;
    }

    public void setResponseProvider(String responseProvider) {
        this.responseProvider = responseProvider;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(String issueDetails) {
        this.issueDetails = issueDetails;
    }
}
