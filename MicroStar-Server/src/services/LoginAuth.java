package services;

import factories.DBConnectorFactory;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginAuth {


    public static boolean authLoginUser(User user) {
        String userType = user.getAccountType().toUpperCase();

        if (userType.equals("CUSTOMER")) {
            System.out.println("GET USER INFO" + user.getPassword() + user.getCustomerID());
            return loginCustomer(user.getCustomerID(), user.getPassword());
        }
        return false;
    }


    public static boolean loginCustomer(String userID, String password) {

        String ID, pass = "";

        String loginSql = "SELECT customer_id, full_name, email, contact, service_address, complaint_code, account_number, password, password FROM MicroStar.`customers` WHERE customer_id = ? AND password =?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()) {
            PreparedStatement statement = dbConn.prepareStatement(loginSql);
            statement.setString(1, userID);
            statement.setString(2, password);

            System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            System.out.println("User Results Received");
            System.out.println("USER RESULTS");

            System.out.println("Login User");

            while (result.next()) {
                ID = result.getString(1);
                pass = result.getString(8);
                System.out.println("USER ID: " + ID);
                System.out.println("USER PASS: " + pass);


                if (ID.equals(userID) && pass.equals(password))
                    return true;
                System.out.println("USER FOUND");
            }

        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }

        return false;
    }
}
