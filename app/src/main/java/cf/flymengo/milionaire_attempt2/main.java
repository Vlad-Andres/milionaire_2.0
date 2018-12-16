package cf.flymengo.milionaire_attempt2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView username_txt = findViewById(R.id.username_txt);
        username_txt.setText(User.name);
        ImageView img = (ImageView) findViewById(R.id.avatar_img);
        img.setImageBitmap(User.avatar_img);
    }
}
