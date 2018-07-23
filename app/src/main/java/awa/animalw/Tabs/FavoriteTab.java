package awa.animalw.Tabs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import awa.animalw.Database.DataBaseOpener;
import awa.animalw.DetailInformation.detailInfoCat;
import awa.animalw.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class FavoriteTab extends Fragment {

    private boolean shouldRefreshOnResume = false;
    Context context = null;
    FavoriteAdapter favAdapter;
    ArrayList<String> favList;

    public FavoriteTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Root View
         View rootView = inflater.inflate(R.layout.fragment_fav_tab, container, false);
        //Generate Data List
        favList = this.getFavList();
        //Adapter
        favAdapter= new FavoriteAdapter(this.getContext(),favList);
        //ListView
        ListView list = (ListView)rootView.findViewById(R.id.favoritelist);
        list.setAdapter(favAdapter);
        //List Item on click action
        //set on click for list item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                String name = (String)adapter.getItemAtPosition(position);
                Log.d("test",name);
                /////
                //Go to the detail information page based on the cat name and position on the list.
                Intent intent = new Intent(getContext(), detailInfoCat.class);
                Bundle bun = new Bundle();
                bun.putInt("identifier",position+1);
                bun.putString("name",name);
                intent.putExtras(bun);
                startActivity(intent);
            }
        });
        /*Create a ListView
        //Get the list of favorite from the DB
        //method from DB to get Favorite is getFavList()
        //which will return the list of favorited cat name if there is any
        //This ListView should blank if there is no entries.
        */


        return rootView;

    }

    //Extra methods for DB functions (getting info )
    private ArrayList<String> getFavList(){
        ArrayList<String> hold = new ArrayList<>();
        try{
            DataBaseOpener dbc = new DataBaseOpener(getContext());
            hold = dbc.getFavList();
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return hold;
    }

   //refresh list
   @Override
   public void setUserVisibleHint(boolean isVisibleToUser) {
       super.setUserVisibleHint(isVisibleToUser);
       if (isVisibleToUser) {
           getFragmentManager().beginTransaction().detach(this).attach(this).commit();

       }
   }

   //When user go back from DetailCatInfo page, they should be able to see the immediate change(Add/remove) to the Favorite list
   //Right now add/remove favorite and going back to Favorite Tab doesnt apply the change to the list.
   //Solution suggestiong: create a way to notify when Back button is pressed. Getting token as a key etc.

   //OnResume method
   @Override
   public void onResume(){
       super.onResume();
       if(shouldRefreshOnResume){
           // refresh fragment
           //this.setUserVisibleHint(shouldRefreshOnResume);
           Log.d("if resume","AAA");

       }
   }

    @Override
    public void onPause() {
        super.onPause();
        shouldRefreshOnResume = true;
        Log.d("if stop","ABBBBA");
    }



}

