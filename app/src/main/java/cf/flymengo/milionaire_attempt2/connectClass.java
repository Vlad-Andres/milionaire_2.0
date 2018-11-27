package cf.flymengo.milionaire_attempt2;

import android.os.StrictMode;
import android.util.Log;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectClass {


    String URL = "jdbc:mysql://127.0.0.1/milionaire";
    String UNAME = "root";
    String PASS = "toor";




    public Connection CONN() {
        User.test_var="1.1";

        Connection conn = null;
        String ConnURL = null;
        try {
            User.test_var="1.2";
            Class.forName("com.mysql.jdbc.Driver");
            User.test_var="1.3";
            conn = DriverManager.getConnection(URL, UNAME, PASS);
            User.test_var="1.4";

             conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
