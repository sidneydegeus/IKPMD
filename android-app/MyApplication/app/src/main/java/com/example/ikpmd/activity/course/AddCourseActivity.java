package com.example.ikpmd.activity.course;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.BaseActivity;
import com.example.ikpmd.model.Course;

import java.util.ArrayList;

public class AddCourseActivity extends BaseActivity {

    CourseDataSource courseDataSource;
    Course course;

    EditText code;
    EditText name;
    EditText grade;
    EditText period;
    EditText ec;
    EditText year;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_add_course, null, false);
        //setContentView(R.layout.activity_course);
        mDrawer.addView(contentView, 0);
        //setActivityTitle("Vak Toevoegen");

        i = getIntent();

        System.out.println(i.getSerializableExtra("course").toString());
        course = new Course();
        instantiateFields();

        courseDataSource = new CourseDataSource(this);

        Button button = (Button) findViewById(R.id.buttonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    private void add() {
        // ADD MORE ERROR HANDLING
        if (checkFields()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (addCourse()) {
                builder.setMessage("Het vak is toegevoegd!").show();
                Intent j = new Intent(AddCourseActivity.this, Course2Activity.class);
                j.putExtra("fragmentNumber", i.getSerializableExtra("course").equals("choice") ? 2 : 3);
                startActivity(j);
            } else {
                builder.setMessage("Er is iets fout gegaan met het toevoegen!").show();
            }
        }
    }

    private boolean addCourse() {
        boolean result = false;
        try {
            course.setCode(code.getText().toString());
            course.setName(name.getText().toString());
            if (grade.getText().length() > 0) {
                course.setGrade(Double.parseDouble(grade.getText().toString()));
            }
            if (i.getSerializableExtra("course") != null && i.getSerializableExtra("course").equals("choice")) {
                course.setEc(3);
                course.setCourseType(Course.COURSE_TYPE_CHOICE);
            } else {
                course.setEc(Integer.parseInt(ec.getText().toString()));
                course.setCourseType(Course.COURSE_TYPE_MINOR);
            }

            courseDataSource.addCourse(course);
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private void instantiateFields() {
        code = (EditText) findViewById(R.id.editTextCode);
        name = (EditText) findViewById(R.id.editTextName);
        grade = (EditText) findViewById(R.id.editTextGrade);
        ec = (EditText) findViewById(R.id.editTextEc);
        if (i.getSerializableExtra("course") != null && i.getSerializableExtra("course").equals("choice")) {
            ec.setEnabled(false);
            ec.setFocusable(false);
            ec.setText("3");
        }

    }

    private boolean checkFields() {
        boolean result = false;
        int minorEc = 0;
        if (i.getSerializableExtra("course") != null && i.getSerializableExtra("course").equals("minor")) {
            ArrayList<Course> list = (ArrayList<Course>) courseDataSource.getAllCourses(3);
            for (Course course : list) {
                minorEc += course.getEc();
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (code.getText().length() == 0
                || name.getText().length() == 0) {
            builder.setMessage("Je moet de code en naam invoeren!").show();
        } else if(code.getText().length() > 7) {
            builder.setMessage("Code mag maar maximaal 7 karakters bevatten").show();
        } else if (grade.getText().length() != 0
                && (Double.parseDouble(grade.getText().toString()) < 1.0 || Double.parseDouble(grade.getText().toString()) > 10.0)) {
            builder.setMessage("Je dient een geldig cijfer, of helemaal geen cijfer in te voeren.").show();
        } else if (code.getText().length() != 0 && courseDataSource.getCourse(code.getText().toString()) != null) {
            builder.setMessage("Het vak met de code " + code.getText().toString() + " bestaat al.").show();
        } else if ((i.getSerializableExtra("course") != null
                && i.getSerializableExtra("course").equals("minor"))
                && (minorEc + Integer.parseInt(ec.getText().toString()) > 30 )) {
            builder.setMessage("Minor (of alle minor vakken gecombineerd) kunnen niet meer dan 30 EC bevatten. Huidig gebruikte EC: \" + minorEc").show();
        }else {
            result = true;
        }
        return result;
    }
}
