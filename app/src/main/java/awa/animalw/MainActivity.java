package awa.animalw;

import android.app.Fragment;
import android.database.SQLException;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.io.IOException;

import awa.animalw.Database.DataBaseOpener;
import awa.animalw.Tabs.FavoriteTab;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    private String[] pageTitle = {"Cat Breeds", "Search", "Your Favorites"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.view_pager1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout1);

        //ToolBar custom display
        setSupportActionBar(toolbar);
        title.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }
        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //set viewpager adapter

        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //change ViewPager page when tab selected
        tabLayout.addOnTabSelectedListener (new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                 viewPager.clearFocus();

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    //OnResume method
    @Override
    public void onResume() {
        super.onResume();
        //refresh
       pagerAdapter.notifyDataSetChanged();
    }
}
