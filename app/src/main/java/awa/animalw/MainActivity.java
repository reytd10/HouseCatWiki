package awa.animalw;

import android.app.Fragment;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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
        //Initiation of Image Loader settings
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .threadPoolSize(5)
                .threadPriority(Thread.MAX_PRIORITY)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .diskCacheExtraOptions(480, 320, null)
                .build();

        ImageLoader.getInstance().init(config);


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
        //creating divider for each tab
        LinearLayout linearLayout = (LinearLayout)tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.BLACK);
        drawable.setSize(5, 5);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);
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
