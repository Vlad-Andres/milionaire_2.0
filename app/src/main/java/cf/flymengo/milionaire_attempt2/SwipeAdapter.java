package cf.flymengo.milionaire_attempt2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {
    public SwipeAdapter(FragmentManager fm){super (fm); }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new loginPage();
            case 1:
                return new registrationPage();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
