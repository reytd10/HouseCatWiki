package awa.animalw.Tabs;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import awa.animalw.Database.DataBaseOpener;
import awa.animalw.Database.DatabaseHelper;
import awa.animalw.DetailInformation.detailInfoCat;
import awa.animalw.R;


public class MainTab extends Fragment {
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;
    Context context = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView= inflater.inflate(R.layout.fragment_main_tab, container, false);
        context = this.getContext();
        // add data for displaying in expandable list view
        if(deptList.isEmpty()) {
            loadData();
        }


        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) RootView.findViewById(R.id.expList1);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(getContext(), deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);
        //Enable fast scroll
        simpleExpandableListView.setFastScrollEnabled(true);
        //Collapse all the Groups
        expandAll();
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ///Go To Math Lecture
                if(groupPosition == 0) {
                    //get the group header
                    GroupInfo headerInfo = deptList.get(0);
                    //get the child info
                    ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);
                    //display it or do something with it
                    for(int hold = 0; hold < headerInfo.getProductList().size(); hold++){
                       if( childPosition == hold){
                           //Go to the assigned activity correspondence to the position number
                           //childPosition will be used as and ID in this case when move to other activity
                           Intent intent = new Intent(context, detailInfoCat.class);
                           Bundle bun = new Bundle();
                           bun.putInt("identifier",hold+1);
                           bun.putString("name",detailInfo.getName());
                           Log.d("test",detailInfo.getName());
                           intent.putExtras(bun);
                           startActivity(intent);
                       }
                    }

                }
                ///

                return false;
            }
        });

        return RootView;
    }


    //method to collapse all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
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

    //load some initial data into out list
    private void loadData(){
        //getting cat breed information from Arraylist
        ArrayList<String> catInfo = catBreed();
        //Adding information to child position.
       for(int hold = 0; hold<catInfo.size(); hold++){
           addProduct("Cat",catInfo.get(hold));
       }

    }

    //here we maintain our products in various departments
    private int addProduct(String department, String product){

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        //detailInfoCat.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }

    //method to get cat name and info rom database
    private String getCatInfo(String name){
        String hold ="";
        try{
            DataBaseOpener dbc = new DataBaseOpener(context);
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
            DataBaseOpener dbc = new DataBaseOpener(context);
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
            DataBaseOpener dbc = new DataBaseOpener(context);
            hold = dbc.getTotal();
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return hold;
    }

    //method to add cat to database
    private void addCat(String name, String details){
        try{
            DataBaseOpener dbc = new DataBaseOpener(context);
            dbc.addCat(name,details);
            dbc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
