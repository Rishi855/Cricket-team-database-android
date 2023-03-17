package com.example.dbe1.owners;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class OwnerDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CRICKET";
    public static final int DATABASE_ID = 1;

    public static final String TABLE_OWNER = "owner";
    public static final String KEY_OWNER_ID = "owner_id";
    public static final String KEY_TEAM_ID = "team_id";
    public static final String KEY_OWNER_NAME = "owner_name";



    public OwnerDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE "+ TABLE_OWNER+"(" +
//                KEY_OWNER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
//                KEY_TEAM_ID +" INTEGER, "+
//                KEY_OWNER_NAME +" TEXT "+
//                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addOwner(int teamId,String ownerName)
    {
        Log.d("checkerror","ok 2");
        SQLiteDatabase db =this.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put(KEY_TEAM_ID,teamId);
        val.put(KEY_OWNER_NAME,ownerName);
        db.insert(TABLE_OWNER,null,val);
    }

    public ArrayList<OwnerAttributeClass> fetchAllOwner()
    {
        ArrayList<OwnerAttributeClass> getOwnerData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_OWNER,null);
        while(cursor.moveToNext())
        {
            OwnerAttributeClass model = new OwnerAttributeClass();
            model.owner_id = cursor.getInt(0);
            model.team_id = cursor.getInt(1);
            model.owner_name = cursor.getString(2);

            getOwnerData.add(model);
        }
        return getOwnerData;
    }

}
