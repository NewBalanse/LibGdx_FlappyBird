package com.mygdx.game.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.badlogic.gdx.Gdx;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "FlappyDB";
    public static final String DB_TABLE_NAME = "Game";

    public static final String KEY_ID = "_id";
    public static final String KEY_COUNT = "count";
    public static final String KEY_MEDAL = "medal";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_TABLE_NAME + "(" + KEY_ID + " integer primary key," +
                KEY_COUNT + "integer," + KEY_MEDAL + " integer" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + DB_TABLE_NAME);
        onCreate(db);
    }
}
