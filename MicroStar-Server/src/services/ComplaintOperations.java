package services;

import factories.DBConnectorFactory;
import factories.SessionFactoryBuilder;
import model.Complaints;
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
