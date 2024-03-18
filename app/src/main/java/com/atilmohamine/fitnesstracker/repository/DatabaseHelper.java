package com.atilmohamine.fitnesstracker.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserContract.UserEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        onCreate(db);
    }


    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();


        String[] columns = {UserContract.UserEntry.COLUMN_USERNAME};


        String selection = UserContract.UserEntry.COLUMN_USERNAME + " = ?" + " AND " +
                UserContract.UserEntry.COLUMN_PASSWORD + " = ?";

        String[] selectionArgs = {username, password};


        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        boolean userExists = cursor.moveToFirst();


        cursor.close();
        db.close();

        return userExists;
    }
}
