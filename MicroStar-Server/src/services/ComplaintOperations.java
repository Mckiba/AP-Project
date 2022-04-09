package services;

import factories.DBConnectorFactory;
import factories.SessionFactoryBuilder;
import model.Complaints;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ComplaintOperations {


    public static boolean addComplaint(Complaints complaint) {
        try (Session session = SessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            Complaints complaints = new Complaints();
            complaints.setId(complaint.getId());
            complaints.setCategory(complaint.getCategory());
            complaints.setCustomerID(complaint.getCustomerID());
            complaints.setIssueDetails(complaint.getIssueDetails());
            session.save(complaints);
            session.getTransaction().commit();
            SessionFactoryBuilder.closeSessionFactory();
            return true;
        } catch (HibernateException hex) {
            hex.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Complaints> getAllComplaints() {
        ArrayList<Complaints> complaintList = new ArrayList<>();
        String loginSql = "SELECT * FROM complaints";
        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()) {
            PreparedStatement statement = dbConn.prepareStatement(loginSql);
            System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet rst = statement.executeQuery();

            while (rst.next()) {
                Complaints customer = new Complaints(rst.getString("complaintsID"),
                        rst.getString("customerID"), rst.getString("category"),
                        rst.getString("response"), rst.getString("response_provider"),
                        rst.getDate("response_date"), rst.getString("issue_Details"));
                complaintList.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
            e.printStackTrace();
        }
        return complaintList;
    }



    public static Complaints getComplaint(String issueID) {

        Complaints complaint = new Complaints();

        String sql = "SELECT * FROM MicroStar.complaints WHERE complaintsID = ?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()){

            PreparedStatement statement = dbConn.prepareStatement(sql);
            statement.setString(1, issueID);

            System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                complaint.setId((result.getString(1)));
                complaint.setCategory(result.getString(2));
                complaint.setResponse(result.getString(3));
                complaint.setCustomerID(result.getString(4));
                complaint.setResponseProvider(result.getString(5));
                complaint.setResponseDate(result.getDate(6));
                complaint.setIssueDetails(result.getString(7));

                break;
            }

            System.out.println(complaint.toString());

        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }

        return complaint;
    }
    public static List<Complaints> queryComplaints(User userObj){

        List<Complaints> complaintsList = new ArrayList<Complaints>();

        String sql = "SELECT * FROM MicroStar.complaints WHERE customerID = ?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()){

            PreparedStatement statement = dbConn.prepareStatement(sql);
            statement.setString(1, userObj.getCustomerID());

            System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Complaints complaint = new Complaints();

                complaint.setId(result.getString(1));
                complaint.setCustomerID(result.getString(2));
                complaint.setCategory(result.getString(3));
                complaint.setResponse(result.getString(4));
                complaint.setResponseProvider(result.getString(5));
                complaint.setResponseDate(result.getDate(6));
                complaint.setIssueDetails(result.getString(7));
                complaintsList.add(complaint);
            }

            System.out.println(complaintsList.toString());

        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }

        return complaintsList;
    }

    public static boolean addResponse(String issueID,String userId ,String response) {

        Date date = new Date(); // This object contains the current date value

        try (Session session = SessionFactoryBuilder.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();
            Complaints complaints = session.load(Complaints.class, issueID);
            complaints.setResponseProvider(userId);
            complaints.setResponse(response);
            complaints.setResponseDate(date);
            session.update(complaints);
            tx.commit();
            session.close();
            SessionFactoryBuilder.closeSessionFactory();

            return true;

        } catch (HibernateException hex) {
            hex.printStackTrace();
            return false;
        }
    }

    public static boolean assignTechnician(String issueID, String techID) {

        try (Session session = SessionFactoryBuilder.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Complaints complaints = session.load(Complaints.class, issueID);
            complaints.setResponseProvider(techID);
            session.update(complaints);
            tx.commit();
            session.close();
            SessionFactoryBuilder.closeSessionFactory();
            return true;
        } catch (HibernateException hex) {
            hex.printStackTrace();
            return false;
        }
    }


    public static ArrayList<Complaints> getAssignedComplaints(User user) {

        ArrayList<Complaints> complaintList = new ArrayList<>();
        String loginSql = "SELECT * FROM complaints WHERE response_provider = ?";
        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()) {
            PreparedStatement statement = dbConn.prepareStatement(loginSql);
            statement.setString(1, user.getCustomerID());
            System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet rst = statement.executeQuery();

            while (rst.next()) {
                Complaints complaints = new Complaints(rst.getString("complaintsID"),
                        rst.getString("customerID"), rst.getString("category"),
                        rst.getString("response"), rst.getString("response_provider"),
                        rst.getDate("response_date"), rst.getString("issue_Details"));
                complaintList.add(complaints);
            }
        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
            e.printStackTrace();
        }
        return complaintList;
    }
    
    public static ArrayList<Complaints> getComplaintsByCategory(String category) {
    	ArrayList<Complaints> complaints = new ArrayList<>();
    	String filter = "SELECT * FROM complaints WHERE category =`` ";
    	
    	try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()){

                PreparedStatement statement = dbConn.prepareStatement(filter);
                System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
                
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                	Complaints filterCat = new Complaints();// creating an object to filter category 
                	filterCat.setCategory(result.getString(category));
                	filterCat.setCustomerID(result.getString("customerID"));
                	filterCat.setIssueDetails(result.getString("issue_Details"));
                	filterCat.setResponse(result.getString("response"));
                	filterCat.setResponseProvider(result.getString("response_provider"));
                	filterCat.setResponseDate(result.getDate("response_date"));

                	complaints.add(filterCat);
                } 
                //System.out.println(filterCat.toString());

            } catch (SQLException e) {
                System.out.println("Error(" + e.getErrorCode()
                        + ") " + e.getMessage());
            }

    	return complaints;
    }
}
