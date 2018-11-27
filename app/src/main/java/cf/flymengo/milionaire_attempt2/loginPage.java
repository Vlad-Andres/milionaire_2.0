package cf.flymengo.milionaire_attempt2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginPage extends Fragment {
    public User currentUser;
    EditText mail,pass;
    TextView log_in_lbl;
    Button login_btn;
    connectClass conClass;
    boolean isSuccess=false;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        //Bundle bundle = getArguments();
        //int pageNumber=bundle.getInt("PageNumber");
        view = inflater.inflate(R.layout.login,container,false);
        //TextView textView = (TextView)view.findViewById(R.id.pageNumber);
        //textView.setText(Integer.toString(pageNumber));
        login_btn = view.findViewById(R.id.button);
        mail = view.findViewById(R.id.email_txt);
        pass = view.findViewById(R.id.password_txt);
        log_in_lbl = view.findViewById(R.id.textView);
        conClass = new connectClass();
        intent = new Intent(getActivity(),user_test.class);
        login_btn_click(view);
        return view;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    private void login_btn_click(View view){
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.length()>0 && pass.length()>0){
                    UserLogIn login = new UserLogIn();
                    login.execute();
                    currentUser = new User(1,"vlad","sds","sds",123,"sd");
                    startActivity(intent);
                }
            }
        });
    }
    public class UserLogIn extends AsyncTask<String, String ,String >{

        String em,password;
        String emailstr=mail.getText().toString();
        @Override
        protected void onPreExecute() {
            User.test_var="0";
        }

        @Override
        protected String doInBackground(String... strings) {
            if (mail.length()>0 && pass.length()>0){
                User.test_var="1";
                try{
                    Connection con = conClass.CONN();
                    User.test_var="2";
                    if (con==null){
                        User.test_var="2.1 Internet Connection";
                    }else {
                        User.test_var="3";
                        String query = "SELECT `Id`,`wallet`,`rank`,`name`,`Nickname`,`email`,`password` FROM users WHERE email='e@mail.com'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        User.test_var="4";
                        while (rs.next())

                        {
                            em=rs.getString(6);
                            password=rs.getString(7);

                            if(em.equals(emailstr))
                            {
                                isSuccess=true;
                                currentUser = new User(rs.getInt("Id"),rs.getString("name"),rs.getString("Nickname")
                                        ,rs.getString("rank"),rs.getInt("wallet"),rs.getString("email"));
                                log_in_lbl.setText("succes");
                            }
                            else
                            {
                               // currentUser = new User(1,"vlad","sds","sds",123,"sd");
                                isSuccess=false;
                            }
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            return null;
        }
    }
}
