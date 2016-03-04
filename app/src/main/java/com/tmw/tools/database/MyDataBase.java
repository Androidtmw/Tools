package com.tmw.tools.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tmw on 2016/2/26.
 */
public class MyDataBase extends SQLiteOpenHelper{


    private final String CREATETABLE = "create table userinfo(_id integer primary key AUTOINCREMENT,username text,password text)";


    public MyDataBase(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATETABLE);//创建数据库

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
