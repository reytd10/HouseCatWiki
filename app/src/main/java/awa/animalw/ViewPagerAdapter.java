package awa.animalw;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import awa.animalw.Tabs.MainTab;
import awa.animalw.Tabs.SearchTab;
import awa.animalw.Tabs.FavoriteTab;

/**
 * Created by ROG on 10/04/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MainTab();
        } else if (position == 1) {
            return new SearchTab();
        } else
            return new FavoriteTab();
    }
    //Refresh all fragments when going back from an activity.
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

