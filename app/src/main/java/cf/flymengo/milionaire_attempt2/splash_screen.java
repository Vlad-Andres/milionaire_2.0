package cf.flymengo.milionaire_attempt2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        // -- animation
        final TextView lbl = findViewById(R.id.splash_txt);
        connectionClass connect_test = new connectionClass();
        lbl.setText("123");
        lbl.setText(connect_test.test_db());
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                lbl.setText("Finished");
                // -- fragments
                ViewPager viewPager = findViewById(R.id.view_pager);
                viewPager.setOffscreenPageLimit(1);
                SwipeAdapter swipe_adapter= new SwipeAdapter(getSupportFragmentManager());
                viewPager.setAdapter(swipe_adapter);
                viewPager.setCurrentItem(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        if (!anim.hasStarted() || anim.hasEnded()){ lbl.startAnimation(anim); }

    }
}
