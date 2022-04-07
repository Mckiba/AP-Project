package services;

import factories.DBConnectorFactory;
import factories.SessionFactoryBuilder;
import model.Accounts;
import model.Complaints;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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







}
