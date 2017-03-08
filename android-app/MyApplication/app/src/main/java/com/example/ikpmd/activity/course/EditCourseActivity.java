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
import android.widget.TextView;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.BaseActivity;
import com.example.ikpmd.model.Course;

import java.util.ArrayList;

public class EditCourseActivity extends BaseActivity {

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
        //setActivityTitle("Vak Aanpassen");

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
        if (course.getCourseType() == 2 || course.getCourseType() == 3) {
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete();
                }
            });
        } else {
            buttonDelete.setVisibility(View.GONE);
        }
    }

    private void edit() {
        /// check?????????
        if (checkFields()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (editCourse()) {
                builder.setMessage("Het vak is aangepast!").show();
                Intent i = new Intent(EditCourseActivity.this, Course2Activity.class);
                i.putExtra("fragmentNumber", course.getCourseType());
                startActivity(i);
            } else {
                builder.setMessage("Er is iets fout gegaan met het aanpassen!").show();
            }
        }
    }

    private void delete() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        if (deleteCourse()) {
                            builder.setMessage("Het vak is verwijderd!").show();
                            Intent i = new Intent(EditCourseActivity.this, Course2Activity.class);
                            i.putExtra("fragmentNumber", course.getCourseType());
                            startActivity(i);
                        } else {
                            builder.setMessage("Er is iets fout gegaan met het aanpassen!").show();
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        builder.setTitle("Vak verwijderen");
        builder.setMessage(
                "Wil je dit vak verwijderen??"
        ).setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nee", dialogClickListener).show();
    }

    private boolean editCourse() {
        boolean result = false;
        try {
            if (course.getCourseType() == Course.COURSE_TYPE_CHOICE || course.getCourseType() == Course.COURSE_TYPE_MINOR) {
                course.setCode(code.getText().toString());
                course.setName(name.getText().toString());
                course.setGrade(Double.parseDouble(grade.getText().toString()));
                course.setEc(Integer.parseInt(ec.getText().toString()));
            } else {
                course.setGrade(Double.parseDouble(grade.getText().toString()));
            }
            courseDataSource.updateCourse(course);
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean deleteCourse() {
        boolean result = false;
        try {
            courseDataSource.deleteCourse(course);
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
        period = (EditText) findViewById(R.id.editTextPeriod);
        period.setFocusable(false);
        year = (EditText) findViewById(R.id.editTextYear);
        year.setFocusable(false);
        if (course.getCourseType() == Course.COURSE_TYPE_CHOICE || course.getCourseType() == Course.COURSE_TYPE_MINOR) {
            if ( course.getCourseType() == Course.COURSE_TYPE_MINOR) {
                ec.setFocusable(true);
            } else {
                ec.setFocusable(false);
            }
            TextView periodText = (TextView) findViewById(R.id.textViewPeriod);
            TextView yearText = (TextView) findViewById(R.id.textViewYear);
            period.setVisibility(View.GONE);
            periodText.setVisibility(View.GONE);
            year.setVisibility(View.GONE);
            yearText.setVisibility(View.GONE);
        } else {
            ec.setFocusable(false);
            code = (EditText) findViewById(R.id.editTextCode);
            code.setFocusable(false);
            name = (EditText) findViewById(R.id.editTextName);
            name.setFocusable(false);
        }
    }

    private void fillFields(Course course) {
        code.setText(course.getCode());
        name.setText(course.getName());
        grade.setText(String.valueOf(course.getGrade()));
        period.setText(String.valueOf(course.getPeriod()));
        ec.setText(String.valueOf(course.getEc()));
        year.setText(String.valueOf(course.getYear()));
    }

    private boolean checkFields() {
        boolean result = false;

        int minorEc = 0;
        if (course.getCourseType() == Course.COURSE_TYPE_MINOR) {
            ArrayList<Course> list = (ArrayList<Course>) courseDataSource.getAllCourses(3);
            for (Course course : list) {
                minorEc += course.getEc();
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //  if course mandatory is not required (keuze vak)
        if (course.getCourseType() == Course.COURSE_TYPE_CHOICE || course.getCourseType() == Course.COURSE_TYPE_MINOR) {
            if (code.getText().length() == 0
                    || name.getText().length() == 0) {
                builder.setMessage("Je moet de code en naam invoeren!").show();
            } else if (code.getText().length() > 7) {
                builder.setMessage("Code mag maar maximaal 7 karakters bevatten").show();
            } else if (grade.getText().length() != 0
                    && (Double.parseDouble(grade.getText().toString()) < 1.0 || Double.parseDouble(grade.getText().toString()) > 10.0)) {
                builder.setMessage("Je dient een geldig cijfer, of helemaal geen cijfer in te voeren.").show();
            } else if (course.getCourseType() == Course.COURSE_TYPE_MINOR
                    && (minorEc + Integer.parseInt(ec.getText().toString()) > 30 )) {
                builder.setMessage("Minor (of alle minor vakken gecombineerd) kunnen niet meer dan 30 EC bevatten. Huidig gebruikte EC: " + minorEc).show();
            } else {
                result = true;
            }
        } else {
            if (grade.getText().length() != 0
                    && (Double.parseDouble(grade.getText().toString()) < 1.0 || Double.parseDouble(grade.getText().toString()) > 10.0)) {
                builder.setMessage("Je dient een geldig cijfer, of helemaal geen cijfer in te voeren.").show();
            } else {
                result = true;
            }
        }

        return result;
    }
}
