import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=StokTakipSistemi;integratedSecurity=true;encrypt=true;trustServerCertificate=true";


        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Windows Authentication ile bağlantı başarılı!");
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası: " + e.getMessage());
        }
    }
}
