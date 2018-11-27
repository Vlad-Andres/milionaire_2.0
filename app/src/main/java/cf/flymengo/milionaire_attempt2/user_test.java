package cf.flymengo.milionaire_attempt2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class user_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test);
        TextView txt = (TextView)findViewById(R.id.name_lbl);
        txt.setText("nuul");
        txt.setText(User.test_var);
    }
}
