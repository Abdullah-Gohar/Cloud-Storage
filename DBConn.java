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
                 //   "jdbc:sqlserver://DESKTOP-5CB18B9:1433;databaseName=Resort_DB;userName=mishal123;password=123;trustServerCertificate=true");
            PreparedStatement statement = connection.prepareStatement("select * from Client");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.print(result.getString("ClientFirstName") + " ");
                System.out.print(result.getString("ClientLastName") + " ");
                System.out.println(result.getString("Gender") + " ");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
