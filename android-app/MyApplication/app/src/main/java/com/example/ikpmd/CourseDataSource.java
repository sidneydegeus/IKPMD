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
                course.setCode(cursor.getString(0));
                course.setName(cursor.getString(1));
                course.setGrade(Integer.parseInt(cursor.getString(2)));
                course.setPeriod(Integer.parseInt(cursor.getString(3)));
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        close();
        return courseList;
    }
}
