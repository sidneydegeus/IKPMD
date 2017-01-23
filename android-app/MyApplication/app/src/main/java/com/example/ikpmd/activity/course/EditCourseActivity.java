package com.example.ikpmd.activity.course;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.MainActivity;
import com.example.ikpmd.model.Course;

public class EditCourseActivity extends MainActivity {

    CourseDataSource courseDataSource;
    Course course;

    EditText code;
    EditText name;
    EditText grade;
    EditText period;
    EditText ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_edit_course, null, false);
        //setContentView(R.layout.activity_course);
        mDrawer.addView(contentView, 0);
        setActivityTitle("Vak Aanpassen");

        Intent i = getIntent();
        course = (Course)i.getSerializableExtra("course");
        instantiateFields();
        fillFields(course);

        courseDataSource = new CourseDataSource(this);

        Button button = (Button) findViewById(R.id.buttonEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
    }

    private void edit() {
        if (true) {
            editCourse();
            startActivity(new Intent(EditCourseActivity.this, CourseActivity.class));
        } else {
            //popup here
        }
    }

    private void editCourse() {
        course.setCode(code.getText().toString());
        course.setName(name.getText().toString());
        course.setGrade(Double.parseDouble(grade.getText().toString()));
        course.setPeriod(Integer.parseInt(period.getText().toString()));
        course.setEc(Integer.parseInt(ec.getText().toString()));
        courseDataSource.updateCourse(course);
    }

    private void instantiateFields() {
        code = (EditText) findViewById(R.id.editTextCode);
        name = (EditText) findViewById(R.id.editTextName);
        grade = (EditText) findViewById(R.id.editTextGrade);
        period = (EditText) findViewById(R.id.editTextPeriod);
        ec = (EditText) findViewById(R.id.editTextEc);
    }

    private void fillFields(Course course) {
        code.setText(course.getCode());
        name.setText(course.getName());
        grade.setText(String.valueOf(course.getGrade()));
        period.setText(String.valueOf(course.getPeriod()));
        ec.setText(String.valueOf(course.getEc()));
    }
}
