import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kabeer
 */
public class DBConn {
    public static void main(String[] args) {
        try {
            // Connection connection = DriverManager.getConnection(
            //         "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=Resort_DB;userName=admin;password=123;trustServerCertificate=true");
            Connection connection = DriverManager.getConnection(
<<<<<<< HEAD
                 //   "jdbc:sqlserver://DESKTOP-5CB18B9:1433;databaseName=Resort_DB;userName=mishal123;password=123;trustServerCertificate=true");
=======
                    "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=CloudStorage;userName=admin;password=123;trustServerCertificate=true");
                    // "jdbc:sqlserver://Hareem:1433;databaseName=CloudStorage;userName=hareem123;password=12345;trustServerCertificate=true");
            
>>>>>>> f8c91d87099bcaf5e06af46f2d477b1764a9b990
            PreparedStatement statement = connection.prepareStatement("select * from Client");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.print(result.getString("UserName") + " ");
                System.out.print(result.getString("Pass") + " ");
                // System.out.println(result.getString("Gender") + " ");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
