package cf.flymengo.milionaire_attempt2;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnect extends AsyncTask<String , String , String> {
    String URL = "jbdc:mysql://localhost:3306/milionaire";
    String UNAME = "root";
    String PASS = "toor";

    @Override
    protected String doInBackground(String... strings) {
        try {
            try{
            Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        Connection con = DriverManager.getConnection(URL, UNAME, PASS);
            if (con == null) {
                // conectiune nereusita
            } else {
                String query = "SELECT `Id`,`wallet`,`rank`,`name`,`Nickname`,`email` FROM users WHERE Nickname=' + username_txt.Text + '";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs != null) {
                    while (rs.next()) {
                        User u = new User(rs.getInt("Id"), rs.getString("name"), rs.getString("Nickname"), rs.getString("rank"),
                                rs.getInt("wallet"), rs.getString("email"));
                    }
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
