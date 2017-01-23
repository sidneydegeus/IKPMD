package com.example.ikpmd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sidney on 1/22/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COURSE = "course";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GRADE = "grade";
    public static final String COLUMN_PERIOD = "period";

    private static final String DATABASE_NAME = "ikpmd.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_COURSE + " ( "
            + COLUMN_CODE + " VARCHAR, "
            + COLUMN_NAME + " VARCHAR NOT NULL, "
            + COLUMN_GRADE + " INTEGER, "
            + COLUMN_PERIOD + " INTEGER NOT NULL, "
            + "CONSTRAINT code_pk PRIMARY KEY (" + COLUMN_CODE + ")"
            + ");";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        onCreate(db);
    }
}
