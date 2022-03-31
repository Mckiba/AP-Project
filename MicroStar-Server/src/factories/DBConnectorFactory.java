package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorFactory {

    private static java.sql.Connection con = null;


    public static Connection getDatabaseConnection() throws SQLException {
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
