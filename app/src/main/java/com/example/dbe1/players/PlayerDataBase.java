package com.example.dbe1.players;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class PlayerDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CRICKET";
    private static final int DATABASE_ID = 1;

    private static final String TABLE_PLAYER = "players";
    private static final String KEY_PLAYER_ID = "player_id";
    private static final String KEY_TEAM_ID = "team_id";
    private static final String KEY_JERSEY_NO = "jersey_no";
    private static final String KEY_SPECIFICATION = "player_specification";
    private static final String KEY_PLAYER_NAME = "player_name";
    private static final String KEY_HEIGHT = "player_height";
    private static final String KEY_WEIGHT = "player_weight";
    private static final String KEY_MATCHES = "player_matches";
    private static final String KEY_RUNS = "player_runs";
    private static final String KEY_WICKETS = "player_wickets";
    private static final String KEY_CATCHES = "player_catches";
//    private static final String KEY_DERIVED_AVG = "player_average";


    public PlayerDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("CREATE TABLE " + TABLE_PLAYER+ " ( "+
//                KEY_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                KEY_TEAM_ID + " INTEGER NOT NULL," +
//                KEY_JERSEY_NO + " INTEGER NOT NULL," +
//                KEY_PLAYER_NAME + " TEXT NOT NULL," +
//                KEY_SPECIFICATION + " TEXT NOT NULL," +
//                KEY_WEIGHT + " REAL NOT NULL," +
//                KEY_HEIGHT + " REAL NOT NULL,"+
//                KEY_MATCHES + " INTEGER," +
//                KEY_RUNS + " INTEGER," +
//                KEY_WICKETS + " INTEGER," +
//                KEY_CATCHES + " INTEGER" +")");

        Log.d("checkerror","done 1");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PLAYER);
        onCreate(db);
    }
    public void addPlayer(int teamID,int jersey_no,String player_name,String specification,float weight,float height,int matches,int run,int wickets,int catches)
    {
        Log.d("checkerror","ok 10");
        SQLiteDatabase db =this.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put(KEY_TEAM_ID,teamID);
        val.put(KEY_JERSEY_NO,jersey_no);
        val.put(KEY_PLAYER_NAME,player_name);
        val.put(KEY_SPECIFICATION,specification);
        val.put(KEY_WEIGHT,weight);
        val.put(KEY_HEIGHT,height);
        val.put(KEY_MATCHES,matches);
        val.put(KEY_RUNS,run);
        val.put(KEY_WICKETS,wickets);
        val.put(KEY_CATCHES,catches);

        db.insert(TABLE_PLAYER,null,val);
        Log.d("checkerror","ok 11");
    }

    public ArrayList<PlayerAttributeClass> fetchAllPlayers()
    {
        ArrayList<PlayerAttributeClass> getPlayerData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("checkerrorf","yes 14 ");
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_PLAYER,null);
        Log.d("checkerrorf","yes 15");
        while(cursor.moveToNext())
        {
            Log.d("checkerrorf","yes 12");
            PlayerAttributeClass model = new PlayerAttributeClass();
            model.player_id = cursor.getInt(0);
            model.team_id = cursor.getInt(1);
            model.jersey_no = cursor.getInt(2);
            model.player_name = cursor.getString(3);
            model.player_specification = cursor.getString(4);
            model.player_weight = cursor.getInt(5);
            model.player_height = cursor.getInt(6);
            model.player_matches = cursor.getInt(7);
            model.player_run = cursor.getInt(8);
            model.player_wicket = cursor.getInt(9);
            model.player_catches = cursor.getInt(10);
            Log.d("checkerrorf","yes 13");

            getPlayerData.add(model);
        }
        return getPlayerData;
    }
    public void updateData(int id,int JerseyNo,String PlayerName,String Specification,Float Weight,Float Height,int Matches,int Runs,int Wickets,int Catches)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String str = "UPDATE "+TABLE_PLAYER+ " SET "+
                KEY_JERSEY_NO+" = "+JerseyNo+" , "+
                KEY_PLAYER_NAME+" = '"+PlayerName+"' , "+
                KEY_SPECIFICATION+" = '"+Specification+"' , "+
                KEY_WEIGHT+" = "+Weight+" , "+
                KEY_HEIGHT+" = "+Height+" , "+
                KEY_MATCHES+" = "+Matches+" , "+
                KEY_RUNS+" = "+Runs+" , "+
                KEY_WICKETS+" = "+Wickets+" ,"+
                KEY_CATCHES+" = "+Catches+" "+
                " WHERE "+KEY_PLAYER_ID+" = "+id;
        db.execSQL(str);
    }
    public void deleteDatatable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE "+TABLE_PLAYER);
//        Log.d("ContactInfo ","Deleted");
    }
}
