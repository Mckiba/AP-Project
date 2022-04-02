package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Complaints")
public class Complaints implements Serializable {

    private static final long serialVersionUID = 4090151075057566008L;

        @Id
        @Column(name = "complaintsID")
        protected String id;

        @Column(name = "customerID")
        protected String customerID;

        @Column(name = "category")
        protected String category;

        @Column(name = "response")
        protected String response;

        @Column(name = "response_provider")
        protected String responseProvider;

        @Column(name = "response_date")
        protected Date responseDate;

        @Column(name = "issue_Details")
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
        public Complaints(model.Complaints c) {
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
