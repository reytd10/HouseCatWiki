package awa.animalw.DetailInformation;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import awa.animalw.Database.DataBaseOpener;
import awa.animalw.MainActivity;
import awa.animalw.R;

public class detailInfoCat extends AppCompatActivity {
    private int id;
    private String cName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);

        Bundle bundle= getIntent().getExtras();
        //Get data inside the bundle that has been passed from
        //previous activity
        try {
            if (bundle != null) {
                id = bundle.getInt("identifier");
                cName= bundle.getString("name");
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        CustomPagerAdapterImage mCustomPagerAdapterImage = new CustomPagerAdapterImage(this,id ,cName);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapterImage);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager, true);

        //Setting text info
        TextView catInfo = (TextView) findViewById(R.id.catInfo);
        catInfo.setText(getCatInfo(cName));
        catInfo.setMovementMethod(new ScrollingMovementMethod());

        //Info Button
        Button info1 = (Button) findViewById(R.id.info1);


        //Favorite button
        final ToggleButton favorite = (ToggleButton) findViewById(R.id.favbuton);
        //setting animations
        final Animation buttonAnim= AnimationUtils.loadAnimation(this,R.anim.bounce);
        BounceInterpolator bounce = new BounceInterpolator(0.2,20);
        buttonAnim.setInterpolator(bounce);
        //setting animation when clicking on favorite button
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorite.startAnimation(buttonAnim);
            }
        });
        //checking if cat is already in favorite or not
        //if it is, turn the fav button on.
        boolean check = this.hasFav(cName);
        favorite.setChecked(check);
       //toggle function
        favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //change star color to bright
                    //add to favorite
                   if(hasFav(cName) == false){
                     addFav(cName);
                       Log.d("check","add");
                   }
                   else{

                   }
                }
                else{
                  //change star color to dark
                  //remove favorite
                    if(hasFav(cName) == true){
                      removeFav(cName);
                        Log.d("check","remove");
                    }
                    else{

                    }
                }
            }
        });
    }

    //method to get cat name and info from database
    private String getCatInfo(String name){
        String hold ="";
        try{
            DataBaseOpener dbc = new DataBaseOpener(getBaseContext());
            hold = dbc.getCatInfo(name);
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return hold;
    }

    //method to check if cat is in favorite list
    private boolean hasFav(String name){
       boolean hold;
        try{
            DataBaseOpener dbc = new DataBaseOpener(getBaseContext());
            hold = dbc.checkFav(name);
            dbc.close();
            return hold;
        }catch(Exception e){
            e.printStackTrace();
        }
     return false;
    }
    //method to add to favorite list
    private void addFav(String name){
        try{
            DataBaseOpener dbc = new DataBaseOpener(getBaseContext());
            dbc.addFav(name);
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //method to remove favorite
    private void removeFav(String name){
        try{
            DataBaseOpener dbc = new DataBaseOpener(getBaseContext());
            dbc.removeFav(name);
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
