import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private String userName = "root";
    private String password = "12345";
    private String dbUrl = ("jdbc:mysql://localhost/atm_bank_customer_data?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, userName, password); //bizim bu connectorlere erişmemizi sağlayan onları yöneten class
    }

    public void showErrorMessage(SQLException exception){
        System.out.println("Error: "+exception.getMessage());
        System.out.println("Error code: "+exception.getErrorCode());
    }
}
