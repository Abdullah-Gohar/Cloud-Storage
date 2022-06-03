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
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=CloudStorage;userName=admin;password=123;trustServerCertificate=true");
            PreparedStatement statement = connection.prepareStatement("select * from Client");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.print(result.getString("UserName") + " ");
                System.out.print(result.getString("Pass") + " ");
                System.out.println(result.getString("Purpose") + " ");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
