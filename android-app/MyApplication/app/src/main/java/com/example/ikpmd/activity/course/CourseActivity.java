package com.example.ikpmd.activity.course;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.ikpmd.CourseAdapter;
import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.MainActivity;
import com.example.ikpmd.model.Course;

import java.util.ArrayList;

public class CourseActivity extends MainActivity {

    private CourseDataSource courseDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_course, null, false);
        //setContentView(R.layout.activity_course);
        mDrawer.addView(contentView, 0);
        setActivityTitle("Vakken");

        courseDataSource = new CourseDataSource(this);

        ArrayList<Course> courses = (ArrayList) courseDataSource.getAllCourses();
        ListView listView = (ListView) findViewById(R.id.listView);
        //ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_expandable_list_item_1, courses);
        CourseAdapter adapter = new CourseAdapter(this, courses);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                Course course = (Course) parent.getAdapter().getItem(position);
                Intent i = new Intent(CourseActivity.this, EditCourseActivity.class);
                i.putExtra("course", course);
                startActivity(i);
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               startActivity(new Intent(CourseActivity.this, AddCourseActivity.class));
           }
        });
    }
}
