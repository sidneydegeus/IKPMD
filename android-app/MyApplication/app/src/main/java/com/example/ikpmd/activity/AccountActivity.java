package com.example.ikpmd.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.StudentDataSource;
import com.example.ikpmd.model.Course;
import com.example.ikpmd.model.Student;
import com.example.ikpmd.service.CourseService;
import com.example.ikpmd.service.StudentService;

import java.util.ArrayList;

public class AccountActivity extends BaseActivity {

    StudentDataSource studentDataSource;
    Student student;

    EditText studentNr;
    EditText password;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationView.setVisibility(View.GONE);
        //toolbar.setVisibility(View.GONE);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_account, null, false);
        mDrawer.addView(contentView, 0);
        //setActivityTitle("Account toevoegen");

        student = new Student();
        instantiateFields();
        studentDataSource = new StudentDataSource(this);

        Button button = (Button) findViewById(R.id.buttonAdd);
        TextView textView = (TextView) findViewById(R.id.textTitle);
        Intent i = getIntent();
        if (i.getSerializableExtra("register") != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    register();
                }
            });
        } else {
            button.setText("Login");
            textView.setText("Inloggen");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        }


    }

    private void register() {
        if (checkFieldsRegister()) {
            student.setStudentNr(studentNr.getText().toString());
            student.setPassword(password.getText().toString());
            studentDataSource.addStudent(student);
            StudentService studentService = new StudentService();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if(studentService.registerStudent(student, this).equals("exists")) {
                builder.setMessage("Studentnr is al in gebruik").show();
            } else {
                builder.setMessage("Studentnr is aangemaakt").show();
                getSharedPreferences("com.example.ikpmd", MODE_PRIVATE).edit().putBoolean("firstrun", false).apply();
                Intent i = new Intent(AccountActivity.this, IntroActivity.class);
                startActivity(i);
            }
        }
    }

    private void login() {
        if (checkFieldsLogin()) {
            student.setStudentNr(studentNr.getText().toString());
            student.setPassword(password.getText().toString());
            StudentService studentService = new StudentService();
            if(studentService.loginStudent(student, this).equals("nomatch")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Gegevens zijn niet juist").show();
            } else {
                importData();
                studentDataSource.addStudent(student);
                getSharedPreferences("com.example.ikpmd", MODE_PRIVATE).edit().putBoolean("firstrun", false).apply();
                Intent i = new Intent(AccountActivity.this, OverviewActivity.class);
                startActivity(i);
            }
        }
    }

    private void importData() {
        ArrayList<Course> list = new CourseService().getAllByStudent(student.getStudentNr());
        CourseDataSource courseDataSource = new CourseDataSource(this);
        courseDataSource.deleteAll();
        for (Course course : list) {
            courseDataSource.addCourse(course);
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void instantiateFields() {
        studentNr = (EditText) findViewById(R.id.editTextStudentNr);
        password = (EditText) findViewById(R.id.editTextPassword);
    }

    private boolean checkFieldsRegister() {
        boolean result = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!isNetworkAvailable()) {
            builder.setMessage("Er is geen connectie met het internet. Zorg er voor dat je verbonden bent met het internet").show();
        } else if (studentNr.getText().length() != 8) {
            builder.setMessage("Zorg er voor dat je student nr uit 7 cijfers bestaat en er een s voor staat.\n\nVoorbeeld: s1234567").show();
        } else if (password.getText().length() < 3) {
            builder.setMessage("Je wachtwoord moet minimaal 3 karakters lang zijn ").show();
        } else {
            result = true;
        }
        return result;
    }

    private boolean checkFieldsLogin() {
        boolean result = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!isNetworkAvailable()) {
            builder.setMessage("Er is geen connectie met het internet. Zorg er voor dat je verbonden bent met het internet").show();
        }  else {
            result = true;
        }
        return result;
    }
}
