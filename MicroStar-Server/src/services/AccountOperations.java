package services;

import factories.DBConnectorFactory;
import model.Accounts;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountOperations {




    public static Accounts queryAccount(User userObj){

        Accounts account = new Accounts();

        String sql = "SELECT * FROM MicroStar.accounts WHERE account_id = ?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()){

            PreparedStatement statement = dbConn.prepareStatement(sql);
            statement.setString(1, userObj.getCustomerID());

            System.out.println("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                account.setCustomerID(result.getString(1));
                account.setPaymentStatus(result.getString(2));
                account.setAmountDue(result.getDouble(3));
                account.setPaymentDate(result.getDate(4));
                account.setCreatedAt(result.getDate(5));
                break;
            }

            System.out.println(account.toString());

        } catch (SQLException e) {
            System.out.println("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }

        return account;
    }

}
