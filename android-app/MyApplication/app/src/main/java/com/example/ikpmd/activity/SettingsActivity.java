package com.example.ikpmd.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.StudentDataSource;
import com.example.ikpmd.model.Course;
import com.example.ikpmd.model.Student;
import com.example.ikpmd.service.CourseService;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends BaseActivity {

    private boolean loggedIn = false;
    private StudentDataSource studentDataSource;
    private CourseDataSource courseDataSource;
    private Student student = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_settings, null, false);
        mDrawer.addView(contentView, 0);
        //setActivityTitle("Instellingen");
        studentDataSource = new StudentDataSource(this);
        courseDataSource = new CourseDataSource(this);
        List<Student> studentList =  studentDataSource.getAllStudents();
        if (!studentList.isEmpty()) student = studentList.get(0);

        initialize();
    }

    private void initialize() {
        TextView studentNr = (TextView) findViewById(R.id.textViewStudentNr);
        Button buttonAccount = (Button) findViewById(R.id.buttonAccount);

        studentNr.setText("Je bent ingelogd als student: " + student.getStudentNr());
        buttonAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeAccount();
                }
            });

    }

/*    private void exportData() {
        CourseDataSource courseDataSource = new CourseDataSource(this);
        List<Course> courseList = courseDataSource.getAllCourses();
        CourseService courseService = new CourseService();
        for (Course course : courseList) {
            courseService.addCourseByStudent(course, student.getStudentNr());
        }
        //courseService.addCoursesByStudent(courseList, student.getStudentNr());
    }*/

    private void exportData() {
        CourseDataSource courseDataSource = new CourseDataSource(this);
        List<Course> courseList = courseDataSource.getAllCourses();
        CourseService courseService = new CourseService();
        courseService.addCoursesByStudent(courseList, student.getStudentNr());
    }

    private void removeAccount() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        exportData();
                        studentDataSource.deleteStudent(student);
                        courseDataSource.deleteAll();
                        getSharedPreferences("com.example.ikpmd", MODE_PRIVATE).edit().putBoolean("firstrun", true).apply();
                        Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                        startActivity(i);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
                "Weet je zeker dat je wilt uitloggen?"
        ).setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nee", dialogClickListener).show();
    }
}
