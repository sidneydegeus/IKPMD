package com.example.ikpmd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ikpmd.R;
import com.example.ikpmd.StudentDataSource;
import com.example.ikpmd.activity.course.AddCourseActivity;
import com.example.ikpmd.activity.course.CourseActivity;
import com.example.ikpmd.model.Student;
import com.example.ikpmd.service.StudentService;

import java.io.UnsupportedEncodingException;

public class AddAccountActivity extends MainActivity {

    StudentDataSource studentDataSource;
    Student student;

    EditText studentNr;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_add_account, null, false);
        mDrawer.addView(contentView, 0);
        //setActivityTitle("Account toevoegen");

        student = new Student();
        instantiateFields();
        studentDataSource = new StudentDataSource(this);

        Button button = (Button) findViewById(R.id.buttonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    private void add() {
        if (true) {
            addStudent();
            Intent i = new Intent(AddAccountActivity.this, SettingsActivity.class);
            i.putExtra("addAccount", true);
            startActivity(i);
        } else {
            //popup here
        }
    }

    private void addStudent() {
        student.setStudentNr(studentNr.getText().toString());
        student.setPassword(password.getText().toString());
        studentDataSource.addStudent(student);
        StudentService studentService = new StudentService();
        studentService.postStudent(student, this);
    }


    private void instantiateFields() {
        studentNr = (EditText) findViewById(R.id.editTextStudentNr);
        password = (EditText) findViewById(R.id.editTextPassword);
    }
}
