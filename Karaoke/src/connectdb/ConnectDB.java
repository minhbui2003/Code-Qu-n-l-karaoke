
package connectdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ad
 */
public class ConnectDB {

      private Connection con = null;
    private static ConnectDB instance = new ConnectDB();

    private ConnectDB() {
        // private constructor để ngăn việc khởi tạo từ bên ngoài
    }

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws SQLException {
        // Kiểm tra xem kết nối đã được thiết lập chưa
        if (con == null || con.isClosed()) {
            String url = "jdbc:sqlserver://localhost:1433;databasename=QlKara;encrypt=false";
            String user = "sa";
            String pass = "123456789";
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Ket noi thanh cong");
        }
    }
    
    public Connection getConnection() {
        return con;
    }

    public static void main(String[] args) throws SQLException {
    ConnectDB db = ConnectDB.getInstance();
    try {
        db.connect();
        // Perform database operations here

        // Example: Execute a query
        Statement statement = db.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM YourTable");

        // Process the result set or perform other database operations

        // Close the resources
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        System.out.println("Ket noi bi dong");
        e.printStackTrace();
    } finally {
        // Always close the connection in a finally block to ensure it gets closed even if an exception occurs
    }
}
}
