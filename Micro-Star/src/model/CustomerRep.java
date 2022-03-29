package model;

public class CustomerRep {
    int repId;
    String fullName;
    String email;
    int complaintCode;
    String password;

    public CustomerRep() {
        this.repId = 1001;
        this.fullName = "Jane Doe";
        this.email = "doeJane2@gmail.com";
        this.complaintCode = 1080;
        this.password = "JanePassWord123";

    }

    public CustomerRep(int repID, String fullName, String email, int complaintCode, String password) {
        this.repId = repId;
        this.fullName = fullName;
        this.email = email;
        this.complaintCode = complaintCode;
        this.password = password;
    }

    public CustomerRep(CustomerRep c) {
        this.repId = c.repId;
        this.fullName = c.fullName;
        this.email = c.email;
        this.complaintCode = c.complaintCode;
        this.password = c.password;
    }

    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
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

    public int getComplaintCode() {
        return complaintCode;
    }

    public void setComplaintCode(int complaintCode) {
        this.complaintCode = complaintCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomerRep Info "
                + "[repId:" + repId + ", fullName:" + fullName + ", email:" + email + ", complaintCode:"
                + complaintCode + ", password:" + password + "]";
    }


}
