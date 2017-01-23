package com.example.ikpmd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ikpmd.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidney on 1/23/2017.
 */

public class CourseDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {
            MySQLiteHelper.COLUMN_CODE,
            MySQLiteHelper.COLUMN_NAME,
            MySQLiteHelper.COLUMN_GRADE,
            MySQLiteHelper.COLUMN_PERIOD
    };

    public CourseDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        //dbHelper.onUpgrade(database, 1, 2);
    }

    public void close() {
        database.close();
        dbHelper.close();
    }

    public void addCourse(Course course) {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CODE, course.getCode());
        values.put(MySQLiteHelper.COLUMN_NAME, course.getName());
        values.put(MySQLiteHelper.COLUMN_GRADE, course.getGrade());
        values.put(MySQLiteHelper.COLUMN_PERIOD, course.getPeriod());
        values.put(MySQLiteHelper.COLUMN_EC, course.getEc());
        database.insert(MySQLiteHelper.TABLE_COURSE, null, values);
        close();
    }

    public List<Course> getAllCourses() {
        open();
        List<Course> courseList = new ArrayList<Course>();
        String selectQuery = "SELECT  * FROM " + MySQLiteHelper.TABLE_COURSE;
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(Integer.parseInt(cursor.getString(0)));
                course.setCode(cursor.getString(1));
                course.setName(cursor.getString(2));
                course.setGrade(Double.parseDouble(cursor.getString(3)));
                course.setPeriod(Integer.parseInt(cursor.getString(4)));
                course.setEc(Integer.parseInt(cursor.getString(5)));
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        close();
        return courseList;
    }

    public void updateCourse(Course course) {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CODE, course.getCode());
        values.put(MySQLiteHelper.COLUMN_NAME, course.getName());
        values.put(MySQLiteHelper.COLUMN_GRADE, course.getGrade());
        values.put(MySQLiteHelper.COLUMN_PERIOD, course.getPeriod());
        values.put(MySQLiteHelper.COLUMN_EC, course.getEc());

        // updating row
        database.update(MySQLiteHelper.TABLE_COURSE, values, MySQLiteHelper.TABLE_ID + " = ?",
                new String[] { String.valueOf(course.getId()) });
    }
}
