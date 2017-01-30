package com.example.ikpmd.activity.course;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.MainActivity;
import com.example.ikpmd.activity.SettingsActivity;
import com.example.ikpmd.model.Course;

public class EditCourseActivity extends MainActivity {

    CourseDataSource courseDataSource;
    Course course;
    Context context;

    EditText code;
    EditText name;
    EditText grade;
    EditText period;
    EditText ec;
    EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
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

        Button buttonEdit = (Button) findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }

    private void edit() {
        editCourse();
        Intent i = new Intent(EditCourseActivity.this, CourseActivity.class);
        i.putExtra("editCourse", true);
        startActivity(i);

        //other error?????????
    }

    private void delete() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        DeleteCourse();
                        Intent i = new Intent(EditCourseActivity.this, CourseActivity.class);
                        i.putExtra("deleteCourse", true);
                        startActivity(i);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vak verwijderen");
        builder.setMessage(
                "Wil je dit vak verwijderen??"
        ).setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nee", dialogClickListener).show();
    }

    private void editCourse() {
        course.setCode(code.getText().toString());
        course.setName(name.getText().toString());
        course.setGrade(Double.parseDouble(grade.getText().toString()));
        course.setPeriod(Integer.parseInt(period.getText().toString()));
        course.setEc(Integer.parseInt(ec.getText().toString()));
        course.setYear(Integer.parseInt(year.getText().toString()));
        courseDataSource.updateCourse(course);
    }

    private void DeleteCourse() {
        courseDataSource.deleteCourse(course);
    }

    private void instantiateFields() {
        code = (EditText) findViewById(R.id.editTextCode);
        name = (EditText) findViewById(R.id.editTextName);
        grade = (EditText) findViewById(R.id.editTextGrade);
        period = (EditText) findViewById(R.id.editTextPeriod);
        ec = (EditText) findViewById(R.id.editTextEc);
        year = (EditText) findViewById(R.id.editTextYear);
    }

    private void fillFields(Course course) {
        code.setText(course.getCode());
        name.setText(course.getName());
        grade.setText(String.valueOf(course.getGrade()));
        period.setText(String.valueOf(course.getPeriod()));
        ec.setText(String.valueOf(course.getEc()));
        year.setText(String.valueOf(course.getYear()));
    }
}
