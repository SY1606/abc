package com.example.liuhang1123.sql;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDao extends SQLiteOpenHelper {

    public MyDao (Context context){
        super(context,"dachui",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE abc(personid INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR(100),date VARCHAR(100),category VARCHAR(100),author_name VARCHAR(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
