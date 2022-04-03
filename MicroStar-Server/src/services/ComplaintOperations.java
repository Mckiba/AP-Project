package services;

import factories.SessionFactoryBuilder;
import model.Complaints;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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


}
