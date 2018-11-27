package cf.flymengo.milionaire_attempt2;

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

public class loginPage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        //Bundle bundle = getArguments();
        //int pageNumber=bundle.getInt("PageNumber");
        view = inflater.inflate(R.layout.login,container,false);
        //TextView textView = (TextView)view.findViewById(R.id.pageNumber);
        //textView.setText(Integer.toString(pageNumber));

        return view;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    private void login_btn_click(View view){
        Button login_btn = view.findViewById(R.id.button);
        EditText mail = view.findViewById(R.id.email_txt);
        EditText pass = view.findViewById(R.id.password_txt);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.length()>0 && pass.length()>0){

                }
            }
        });
    }
    public class UserLogIn extends AsyncTask<String, String ,String >{
        String URL = "jbdc:mysql://localhost:3306/milionaire";
        String UNAME = "root";
        String PASS = "toor";

        @Override
        protected void onPreExecute() {

        }
    }
}
