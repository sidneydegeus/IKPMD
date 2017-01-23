package com.example.ikpmd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.model.Course;

import java.util.List;

public class CourseActivity extends MainActivity {

    private CourseDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_course, null, false);
        //setContentView(R.layout.activity_course);
        mDrawer.addView(contentView, 0);
        setActivityTitle("Vakken");


        datasource = new CourseDataSource(this);

        Log.d("Reading: ", "Reading all contacts..");
        List<Course> courses = datasource.getAllCourses();

        for (Course cn : courses) {
            String log = "Id: "+cn.getCode()+" ,Name: " + cn.getName() + " ,Grade: " + cn.getGrade();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               startActivity(new Intent(CourseActivity.this, AddCourseActivity.class));
           }
        });
    }
}
