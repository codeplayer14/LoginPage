package com.example.paddy.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by paddy on 15/8/16.
 */

class Database extends SQLiteOpenHelper {

    private static final String DB_NAME="Database";
    private static final int DB_VERSION=1;
    Database(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE USERINFO ( " + " _id INTEGER PRIMARY KEY AUTOINCREMENT USERNAME TEXT PASSWORD TEXT) ;");

        ContentValues contentValues=new ContentValues();
        contentValues.put("USERNAME","Paddy");
        contentValues.put("PASSWORD","abcd1234");
        db.insert("USERINFO",null,contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
