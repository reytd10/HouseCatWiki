package awa.animalw.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ROG on 8/05/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String db_name = "catDatabase.db";
    //final static int db_version = 1;
    public DatabaseHelper(Context context) {
        super(context, db_name, null, 3);
        // TODO Auto-generated constructor stub
    }
    //onCreate Method
    @Override
    public void onCreate(SQLiteDatabase db){
      //Create table
        String createDB = "CREATE TABLE catInfo (id integer primary key autoincrement, catName TEXT , catDetails TEXT);";
        db.execSQL(createDB);
      //Insert Base Value
        ContentValues cv = new ContentValues();
        cv.put("catName", "Agean" );
        cv.put("catDetails", "\n" +
                "Aegean\n" +
                "\n" +
                "A juvenile male Aegean house cat.\n" +
                "Origin\tGreece Greece\n" +
                "Notes\n" +
                "Occurs naturally throughout Greece, originates from the Cycladic islands\n" +
                "Domestic cat (Felis catus)\n" +
                "The Aegean cat (Greek: γάτα του Αιγαίου gáta tou Aigaíou) is a naturally occurring landrace of domestic cat originating from the Cycladic Islands of Greece. It is considered a natural cat, developing without human interference.[1] Development of the Aegean cat as a formal breed began in the early 1990s by breeders in the fledgling Greek cat fancy, but the variety has yet to be recognized by any major fancier and breeder organization. It is considered to be the only native Greek variety of cat.");
        db.insert("catInfo", null, cv);
   }
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
   //This methods is used to get name from cat table
    public String getCatName(int id){
        String name = "";
        String default1 = "No information was found, sorry";
        //setting  select query
        String select = "SELECT * FROM catInfo WHERE id="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(select,null);
        if(c == null){
            return default1;
        }
        else{
           c.moveToFirst();
           name = c.getString(c.getColumnIndex("catName"));
        }
        return name;
    }
    //This methods is used to get info from cat table
    public String getCatInfo(int id){
        String info= "";
        String default1 = "No information was found, sorry";
        //setting  select query
        String select = "SELECT * FROM catInfo WHERE id="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(select,null);
        if(c == null){
            return default1;
        }
        else{
            c.moveToFirst();
            info = c.getString(c.getColumnIndex("catDetails"));
        }
        return info;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

}
