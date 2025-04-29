import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/StokTakipSistemi";
        String username = "root";
        String password = "34141603";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("MySQL bağlantısı başarılı!");
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası: " + e.getMessage());
        }
    }
}

