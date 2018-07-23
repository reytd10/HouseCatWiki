package awa.animalw.Tabs;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import awa.animalw.Database.DataBaseOpener;
import awa.animalw.DetailInformation.detailInfoCat;
import awa.animalw.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchTab extends Fragment implements  SearchView.OnQueryTextListener{
    Context context = null;
    SearchListAdapter adapter;
    SearchView searchList;
    public SearchTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //RootView
        View rootView= inflater.inflate(R.layout.fragment_search_tab, container, false);
        //Generate Data
        ArrayList<String> catList = catBreed();
        //Binding to adapter
         adapter = new SearchListAdapter(this.getContext(), catList);
        //Implement SearchView
         searchList =(SearchView)rootView.findViewById(R.id.search);
        CharSequence searchText = searchList.getQuery();
        searchList.setOnQueryTextListener(this);
        searchList.clearFocus();

        //ListView
        ListView list = (ListView)rootView.findViewById(R.id.searchlist);
        list.setAdapter(adapter);
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

        return rootView;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
    //Hide Keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // List of all cat breeds
    private ArrayList<String> catBreed(){
        long totalE = getTotal();
        ArrayList<String> cat = new ArrayList<String>();
        //get total number of entries in database and loop through
        for(int hold = 1; hold<=totalE;hold++){
            cat.add(this.getCatName(hold));
        }
        return cat;
    }

    //method to get cat name and info rom database
    private String getCatInfo(String name){
        String hold ="";
        try{
            DataBaseOpener dbc = new DataBaseOpener(getContext());
            hold = dbc.getCatInfo(name);
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return hold;
    }
    //method to get cat name from database
    private String getCatName(int id){
        String hold ="";
        try{
            DataBaseOpener dbc = new DataBaseOpener(getContext());
            hold = dbc.getCatName(id);
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return hold;
    }
    //method to get total count of entries in the database
    private long getTotal(){
        long hold =0;
        try{
            DataBaseOpener dbc = new DataBaseOpener(getContext());
            hold = dbc.getTotal();
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return hold;
    }


}
