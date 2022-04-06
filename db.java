import java.sql.Connection;
import java.sql.DriverManager;
public class db {

    static Connection c;

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/carRental", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
}
