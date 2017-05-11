package com.quwu.xinwo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

	public class SearchSQLiteOpenHelper extends SQLiteOpenHelper {
		 
	    private static String name = "temp.db";
	    private static Integer version = 1;
	 
	    public SearchSQLiteOpenHelper(Context context) {
	        super(context, name, null, version);
	    }
	 
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
	    }
	 
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	 
	    }
	}
