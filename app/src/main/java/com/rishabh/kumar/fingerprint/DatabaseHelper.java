package com.rishabh.kumar.fingerprint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kumar on 8/3/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_name";
    public static final String COL_1 = "ID";
    public static  String COL_2= "name";
    public static final String COL_3= "url";
    public static final String COL_4= "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, URL TEXT, PASSWORD TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String url, String password)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,url);
        contentValues.put(COL_4,password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        if (res.getCount() == 0)
        {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }
        return  res;
    }
    public boolean updateData(String id,String name, String url, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,url);
        contentValues.put(COL_4,password);
        db.update(TABLE_NAME,contentValues, "ID=?",new String[]{id});
        return true;
    }
    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }

}
