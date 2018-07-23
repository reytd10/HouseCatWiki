package awa.animalw.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Created by ROG on 8/05/2018.
 */

public class DataBaseOpener extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "catDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private Context context1;
    public DataBaseOpener(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        context1=context;
        setForcedUpgrade(1);
        //Log.d("version",valueOf(DATABASE_VERSION));
    }

    ////Methods
    //this method is used to add info to the cat table
    public void addCat(String catName, String catDetails ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("catName",catName);
        cv.put("catDetails",catDetails);
        //updating the value into table.
        // db.update("catInfo",cv, "id"+"=?", new String[] {String.valueOf(id)});
        db.insert("catInfo",null,cv);
    }
    ///
    //This methods is used to get name from cat table
    public String getCatName(int id){
        String name = "";
        String default1 = "No information was found, sorry";
        //setting  select query
        String select = "SELECT * FROM catInfo WHERE id="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(select,null);
        if(c == null){
            return default1;
        }
        else{
            c.moveToFirst();
            name = c.getString(c.getColumnIndex("catName"));
        }
        c.close();
        return name;
    }
    //This methods is used to get info from cat table
    public String getCatInfo(String name){
        String info= "";
        String default1 = "No information was found, sorry";
        //setting  select query
        String select = "SELECT catDetails FROM catInfo WHERE catName="+"'"+name+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(select,null);
        if(c == null){
            return default1;
        }
        else{
            c.moveToFirst();
            info = c.getString(c.getColumnIndex("catDetails"));
        }
        c.close();
        return info;
    }
    //This method is used to get the total number of entries in the catDatabase
    public long getTotal(){
        long hold = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        hold = DatabaseUtils.queryNumEntries(db,"catInfo");
        db.close();
        return  hold;
    }
    //This method is used to get the total number of entries in the favorite
    public long getTotalFav(){
        long hold = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        hold = DatabaseUtils.queryNumEntries(db,"favorite");
        db.close();
        return  hold;
    }


    //This method is used to get favorites from database and save it to somewhere else.
    public void addFav(String catName ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",catName);
        db.insert("favorite",null,cv);
    }
    //This method is used to check if entry already exist
    public boolean checkFav(String catName){

        //setting  select query
        String select = "SELECT name FROM favorite WHERE name="+"'"+catName+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(select,null);
        if(c == null){
            c.close();

            return false;
        }
        else{
            //check if cursor is empty or not
            //if empty/false meaning that there is no favorite of such cat
            //used to either add or remove.
            if(c.moveToFirst()){
                Log.d("checkboolean","true");
                c.close();
                return true;
            }
            else{
                Log.d("checkboolean","false");
                c.close();
                return false;
            }
        }
    }
    //method to remove favorite
    public void removeFav(String catName ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("favorite","name=?",new String[]{catName});
    }
    //method to get the list of favorite
    public ArrayList<String> getFavList(){
        ArrayList<String> holder = new ArrayList<>();
        String select = "SELECT name FROM favorite";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(select,null);
        if(c == null){
            c.close();
        }
        else{
            //check if cursor is empty or not
            //Loop through the cursor and add entries to the List
            //If cursor is empty then list is empty.
            while (c.moveToNext()) {
                holder.add(c.getString(c.getColumnIndex("name")));
               // Log.d("testing list", holder.get(2));
            }
            c.close();
        }
        return holder;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //setting onUpgrade method so that some of the old data can be retained
        //Save old data into something else(.txt,etc).
       /* if (oldVersion != newVersion) {
           db.execSQL("");
            new DataBaseOpener(context1);
        }else
            super.onUpgrade(db, oldVersion, newVersion);*/
    }


}
