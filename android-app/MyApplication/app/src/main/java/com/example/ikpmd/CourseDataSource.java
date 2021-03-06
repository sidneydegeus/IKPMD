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
            MySQLiteHelper.COURSE_COLUMN_CODE,
            MySQLiteHelper.COURSE_COLUMN_NAME,
            MySQLiteHelper.COURSE_COLUMN_GRADE,
            MySQLiteHelper.COURSE_COLUMN_PERIOD,
            MySQLiteHelper.COURSE_COLUMN_EC,
            MySQLiteHelper.COURSE_COLUMN_YEAR
    };

    public CourseDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        //dbHelper.onUpgrade(database, 2, 3);
    }

    public void close() {
        database.close();
        dbHelper.close();
    }

    public void addCourse(Course course) {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COURSE_COLUMN_CODE, course.getCode());
        values.put(MySQLiteHelper.COURSE_COLUMN_NAME, course.getName());
        values.put(MySQLiteHelper.COURSE_COLUMN_GRADE, course.getGrade());
        values.put(MySQLiteHelper.COURSE_COLUMN_PERIOD, course.getPeriod());
        values.put(MySQLiteHelper.COURSE_COLUMN_EC, course.getEc());
        values.put(MySQLiteHelper.COURSE_COLUMN_YEAR, course.getYear());
        values.put(MySQLiteHelper.COURSE_COLUMN_TYPE, course.getCourseType());

        database.insert(MySQLiteHelper.TABLE_COURSE, null, values);
        close();
    }

    public Course getCourse(String code) {
        open();
        String selectQuery = "SELECT  * FROM " + MySQLiteHelper.TABLE_COURSE + " WHERE code = '" + code.toUpperCase() + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        Course course = null;
        if (cursor.moveToFirst()) {
            course = new Course();
                course.setId(Integer.parseInt(cursor.getString(0)));
                course.setCode(cursor.getString(1));
                course.setName(cursor.getString(2));
                course.setGrade(Double.parseDouble(cursor.getString(3)));
                course.setPeriod(Integer.parseInt(cursor.getString(4)));
                course.setEc(Integer.parseInt(cursor.getString(5)));
                course.setYear(Integer.parseInt(cursor.getString(6)));
                course.setCourseType(Integer.parseInt(cursor.getString(7)));

        }
        close();
        return course;
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
                course.setYear(Integer.parseInt(cursor.getString(6)));
                course.setCourseType(Integer.parseInt(cursor.getString(7)));
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        close();
        return courseList;
    }

    public List<Course> getAllCourses(int mandatory) {
        open();
        List<Course> courseList = new ArrayList<Course>();
        String selectQuery = "SELECT  * FROM " + MySQLiteHelper.TABLE_COURSE + " WHERE type = " + mandatory;
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
                course.setYear(Integer.parseInt(cursor.getString(6)));
                course.setCourseType(Integer.parseInt(cursor.getString(7)));
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        close();
        return courseList;
    }

    public void updateCourse(Course course) {
        open();
        ContentValues values = new ContentValues();
        if (course.getCourseType() == Course.COURSE_TYPE_CHOICE || course.getCourseType() == Course.COURSE_TYPE_MINOR) {
            if (course.getCourseType() == Course.COURSE_TYPE_MINOR) {
                values.put(MySQLiteHelper.COURSE_COLUMN_EC, course.getEc());
            }
            values.put(MySQLiteHelper.COURSE_COLUMN_CODE, course.getCode());
            values.put(MySQLiteHelper.COURSE_COLUMN_NAME, course.getName());
        }
        values.put(MySQLiteHelper.COURSE_COLUMN_GRADE, course.getGrade());
/*        values.put(MySQLiteHelper.COURSE_COLUMN_PERIOD, course.getPeriod());
        values.put(MySQLiteHelper.COURSE_COLUMN_EC, course.getEc());
        values.put(MySQLiteHelper.COURSE_COLUMN_YEAR, course.getYear());
        values.put(MySQLiteHelper.COURSE_COLUMN_TYPE, course.getCourseType());*/
        // updating row
        database.update(MySQLiteHelper.TABLE_COURSE, values, MySQLiteHelper.COURSE_COLUMN_ID + " = ?",
                new String[] { String.valueOf(course.getId()) });
        close();
    }

    public void deleteCourse(Course course) {
        open();
        database.delete(MySQLiteHelper.TABLE_COURSE, MySQLiteHelper.COURSE_COLUMN_CODE + " = ?", new String[] { String.valueOf(course.getCode())});
        close();
    }

    public void deleteAll() {
        open();
        database.delete(MySQLiteHelper.TABLE_COURSE, "", new String [] {} );
        close();
    }
}
