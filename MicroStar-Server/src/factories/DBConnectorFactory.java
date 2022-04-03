package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorFactory {



    public static Connection getDatabaseConnection() throws SQLException {

         java.sql.Connection con = null;


        String url = "jdbc:mysql://localhost:3306/MicroStar";

        if (con == null) {
            try {
                con = DriverManager.getConnection(url, "root", "password");
                return con;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }


}
