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
            return  true;
        }catch(HibernateException hex) {
            hex.printStackTrace();
            return false;
        }
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


}