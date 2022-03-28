package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.Query;

import com.model.User;

import factories.DBConnectorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserOperation {
    private static final Logger logger = LogManager.getLogger(UserOperation.class);

    public static User getUserInfo(String username, String userType) {
        User currentUser = new User();

        userType = userType.toUpperCase();

        String searchSql = null;
        switch(userType) {
            case "STUDENT":
                searchSql = "SELECT customer_id, full_name, email, contact, service_address, complaint_code, account_number, password FROM MicroStar.`customers` WHERE customer_id = ?";
                break;
            case "AGENT":
                searchSql = "SELECT staff_id, full_name, email, complaint_code, password FROM MicroStar.`Technician` WHERE staff_id = ?";
                break;
            case "REP":
                searchSql = "SELECT rep_id, full_name, email, complaint_code, password FROM microstar.`Customerrep` WHERE rep_id = ?";
                break;
        }

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()){

            PreparedStatement statement = dbConn.prepareStatement(searchSql);
            statement.setString(1, username);

            logger.warn("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                currentUser.setCustomerID(result.getString(1));
                currentUser.setFullName(result.getString(2));
                currentUser.setEmail(result.getString(5));

                if(currentUser.getCustomerID().equals(username))
                    return currentUser;
            }

        } catch (SQLException e) {
            logger.error("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }

        return null;
    }


}