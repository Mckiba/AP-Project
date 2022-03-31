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
            System.out.println("GET USER INFO" + user.getPassword() + user.getEmail());
            return loginCustomer(user.getEmail(), user.getPassword());
        }
        return false;
    }


    public static boolean loginCustomer(String email, String password) {

        String userEmail, pass = "";
        //TODO: CHANGE ACCEPTING USER EMAIL TO ACCEPTING USER ID

        String loginSql = "SELECT customer_id, full_name, email, contact, service_address, complaint_code, account_number, password, password FROM MicroStar.`customers` WHERE email = ? AND password =?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()) {
            PreparedStatement statement = dbConn.prepareStatement(loginSql);
            statement.setString(1, email);
            statement.setString(2, password);

            System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            System.out.println("User Results Received");
            System.out.println("USER RESULTS");

            System.out.println("Login User");

            while (result.next()) {
                userEmail = result.getString(3);
                pass = result.getString(8);
                System.out.println("USER EMAIL: " + userEmail);
                System.out.println("USER PASS: " + pass);


                if (userEmail.equals(email) && pass.equals(password))
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
