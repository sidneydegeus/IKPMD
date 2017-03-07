package com.example.ikpmd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ikpmd.model.Course;
import com.example.ikpmd.model.Student;

import java.util.ArrayList;
import java.util.List;

import static com.example.ikpmd.MySQLiteHelper.TABLE_STUDENT;

/**
 * Created by Sidney on 1/29/2017.
 */

public class StudentDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {
            MySQLiteHelper.STUDENT_COLUMN_ID,
            MySQLiteHelper.STUDENT_COLUMN_PASSWORD
    };

    public StudentDataSource(Context context) {
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

    public void addStudent(Student student) {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.STUDENT_COLUMN_ID, student.getStudentNr());
        values.put(MySQLiteHelper.STUDENT_COLUMN_PASSWORD, student.getPassword());
        database.insert(TABLE_STUDENT, null, values);
        close();
    }

    public List<Student> getAllStudents() {
        open();
        List<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentNr(cursor.getString(0));
                student.setPassword(cursor.getString(1));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        close();
        return studentList;
    }

    public void deleteStudent(Student student) {
        open();
        database.execSQL("delete from "+ TABLE_STUDENT);
        close();
    }
}
