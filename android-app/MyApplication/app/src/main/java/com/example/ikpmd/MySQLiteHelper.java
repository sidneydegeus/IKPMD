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
    public static final String COURSE_COLUMN_ID = "id";
    public static final String COURSE_COLUMN_CODE = "code";
    public static final String COURSE_COLUMN_NAME = "name";
    public static final String COURSE_COLUMN_GRADE = "grade";
    public static final String COURSE_COLUMN_PERIOD = "period";
    public static final String COURSE_COLUMN_EC = "ec";
    public static final String COURSE_COLUMN_YEAR = "year";
    public static final String COURSE_COLUMN_TYPE = "type";

    public static final String TABLE_STUDENT = "student";
    public static final String STUDENT_COLUMN_ID = "student_nr";
    public static final String STUDENT_COLUMN_PASSWORD = "password";

    private static final String DATABASE_NAME = "ikpmd.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_COURSE_CREATE = "CREATE TABLE " + TABLE_COURSE + " ( "
            + COURSE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COURSE_COLUMN_CODE + " VARCHAR, "
            + COURSE_COLUMN_NAME + " VARCHAR NOT NULL, "
            + COURSE_COLUMN_GRADE + " INTEGER, "
            + COURSE_COLUMN_PERIOD + " INTEGER, "
            + COURSE_COLUMN_EC + " INTEGER NOT NULL, "
            + COURSE_COLUMN_YEAR + " INTEGER, "
            + COURSE_COLUMN_TYPE + " INTEGER NOT NULL"
            + ");";

    private static final String TABLE_STUDENT_CREATE = "CREATE TABLE " + TABLE_STUDENT + " ( "
            + STUDENT_COLUMN_ID + " VARCHAR PRIMARY KEY, "
            + STUDENT_COLUMN_PASSWORD + " VARCHAR"
            + ");";

    // Database creation sql statement
    //private static final String DATABASE_CREATE = TABLE_COURSE_CREATE + "\n" + TABLE_STUDENT_CREATE;


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_COURSE_CREATE);
        database.execSQL(TABLE_STUDENT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }
}
