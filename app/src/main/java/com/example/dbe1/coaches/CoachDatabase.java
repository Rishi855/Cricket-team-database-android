package com.example.dbe1.coaches;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class CoachDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CRICKET";
    private static final int DATABASE_ID = 1;

    private static final String TABLE_COACH = "coaches";
    private static final String KEY_TEAM_ID = "team_id";
    private static final String KEY_COACH_NAME = "coach_name";
    private static final String KEY_EXPERIENCE = "coach_experience";
    private static final String KEY_SPECIFICATION = "coach_specification";
    private static final String KEY_COACH_ID = "coach_id";

    public CoachDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " +TABLE_COACH+" (" +
//                KEY_TEAM_ID+" INTEGER" +
//                KEY_COACH_NAME+"TEXT" +
//                KEY_EXPERIENCE+"INTEGER" +
//                KEY_SPECIFICATION+"TEXT"+
//                KEY_COACH_ID+ "INTEGER PRIMARY KEY AUTOINCREMENT"
//                +" )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COACH);
        onCreate(db);
    }

    public void addCoach(int teamId,String coachName,int experience,String specification)
    {
        Log.d("checkerror","ok 2");
        SQLiteDatabase db =this.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put(KEY_TEAM_ID,teamId);
        val.put(KEY_COACH_NAME,coachName);
        val.put(KEY_EXPERIENCE,experience);
        val.put(KEY_SPECIFICATION,specification);
        db.insert(TABLE_COACH,null,val);
    }

    public ArrayList<CoachAttributeCLass> fetchAllCoach()
    {
        ArrayList<CoachAttributeCLass> getCoachData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_COACH,null);
        while(cursor.moveToNext())
        {
            CoachAttributeCLass model = new CoachAttributeCLass();
            model.team_id = cursor.getInt(0);
            model.coach_name = cursor.getString(1);
            model.experience = cursor.getInt(2);
            model.specification = cursor.getString(3);
            model.coach_id = cursor.getInt(4);
            getCoachData.add(model);
        }
        return getCoachData;
    }
    public void updateData(int id,String name,String experience,String specification)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String str = "UPDATE "+TABLE_COACH+ " SET "+
                KEY_COACH_NAME+" = '"+name+"' , "+
                KEY_EXPERIENCE+" = '"+experience+"' ,"+
                KEY_SPECIFICATION+" = '"+specification+"' "+
                " WHERE "+KEY_COACH_ID+" = "+id;
        db.execSQL(str);
    }
    public void deleteDatatable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE "+TABLE_COACH);
    }
}
