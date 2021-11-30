package com.login_v1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cesdeshop";
    public static final int DATABASE_VERSION = 14;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    /*
        db.execSQL("create table users(" +
                "id integer primary key autoincrement," +
                "name nvarchar(50)," +
                "email nvarchar(50)," +
                "password nvarchar(20)," +
                "rol nvarchar(20)," +
                "shop nvarchar(50) default 'oeee')");
*/
        String[] tablas = {
                "create table users(id integer primary key autoincrement,name nvarchar(50), email nvarchar(50), password nvarchar(20), rol nvarchar(20), shop nvarchar(50));",
                "create table products(id integer primary key autoincrement,name nvarchar(50), price int, units int, shop nvarchar(50));",
        };
        for (String tabla : tablas) {
            db.execSQL(tabla);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists products");
        onCreate(db);
    }
}
