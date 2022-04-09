package services;

import factories.DBConnectorFactory;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginAuth {

    private static PreparedStatement loginConnection(String userID, String password, String loginSql, Connection dbConn) throws SQLException {
        PreparedStatement statement = dbConn.prepareStatement(loginSql);
        statement.setString(1, userID);
        statement.setString(2, password);

        System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
        ResultSet result = statement.executeQuery();

        System.out.println("Login User");
        return statement;
    }

    public static boolean authLoginUser(User user) {
        String userType = user.getAccountType().toUpperCase();

        if (userType.equals("CUSTOMER")) {
            System.out.println("GET USER INFO" + user.getPassword() + user.getCustomerID());
            return loginCustomer(user.getCustomerID(), user.getPassword());
        }
        if (userType.equals("TECHNICIAN")) {
            System.out.println("GET TECH INFO" + user.getPassword() + user.getCustomerID());
            return loginTechnician(user.getCustomerID(), user.getPassword());
        }
        if (userType.equals("REP")) {
            System.out.println("GET REP INFO" + user.getPassword() + user.getCustomerID());
            return loginCustomerRep(user.getCustomerID(), user.getPassword());
        }
        return false;
    }

    public static boolean loginTechnician(String userID, String password) {
        String ID, pass = "";
        String loginSql = "SELECT staff_id, full_name, email, complaint_code, password FROM MicroStar.`Technician` WHERE staff_id = ? AND password =?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()) {
            PreparedStatement statement = loginConnection(userID, password, loginSql, dbConn);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ID = result.getString(1);
                pass = result.getString(5);
                System.out.println("Technician ID: " + ID);
                System.out.println("Tech PASS: " + pass);
                if (ID.equals(userID) && pass.equals(password)) {
                    System.out.println("USER FOUND");
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }
        return false;
    }


    public static boolean loginCustomerRep(String userID, String password) {

        String ID, pass = "";
        String loginSql = "SELECT rep_id, full_name, email, complaint_code, password FROM MicroStar.`CustomerRep` WHERE rep_id = ? AND password =?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()) {
            PreparedStatement statement = loginConnection(userID, password, loginSql, dbConn);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ID = result.getString(1);
                pass = result.getString(5);
                System.out.println("REP ID: " + ID);
                System.out.println("REP PASS: " + pass);
                if (ID.equals(userID) && pass.equals(password)){
                    System.out.println("USER FOUND");
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }
        return false;
    }


    public static boolean loginCustomer(String userID, String password) {

        String ID, pass = "";
        String loginSql = "SELECT customer_id, full_name, email, contact, service_address, complaint_code, account_number, password, password FROM MicroStar.`customers` WHERE customer_id = ? AND password =?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()) {
            PreparedStatement statement = loginConnection(userID, password, loginSql, dbConn);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ID = result.getString(1);
                pass = result.getString(8);
                System.out.println("USER ID: " + ID);
                System.out.println("USER PASS: " + pass);
                if (ID.equals(userID) && pass.equals(password)) {
                    System.out.println("USER FOUND");
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }
        return false;
    }
}
