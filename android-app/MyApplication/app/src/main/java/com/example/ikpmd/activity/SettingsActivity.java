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
import com.example.ikpmd.activity.course.AddCourseActivity;
import com.example.ikpmd.activity.course.CourseActivity;
import com.example.ikpmd.activity.course.EditCourseActivity;
import com.example.ikpmd.model.Course;
import com.example.ikpmd.model.Student;
import com.example.ikpmd.service.CourseService;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends MainActivity {

    private boolean loggedIn = false;
    private StudentDataSource studentDataSource;
    private Student student = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_settings, null, false);
        mDrawer.addView(contentView, 0);
        setActivityTitle("Instellingen");
        studentDataSource = new StudentDataSource(this);

        List<Student> studentList =  studentDataSource.getAllStudents();
        if (!studentList.isEmpty()) student = studentList.get(0);

        initialize();
        checkMessages();
    }

    private void initialize() {
        TextView studentNr = (TextView) findViewById(R.id.textViewStudentNr);
        Button buttonImportData = (Button) findViewById(R.id.buttonImportData);
        Button buttonExportData = (Button) findViewById(R.id.buttonExportData);
        Button buttonAccount = (Button) findViewById(R.id.buttonAccount);

        buttonImportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importData();
            }
        });
        buttonExportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportData();
            }
        });

        if (student == null) {
            studentNr.setText("Er is nog geen\naccount toegevoegd!");
            buttonImportData.setVisibility(View.GONE);
            buttonExportData.setVisibility(View.GONE);
            buttonAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addAccount();
                }
            });
        } else {
            studentNr.setText("U bent ingelogd als student: " + student.getStudentNr());
            buttonAccount.setText("Account verwijderen");
            buttonAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeAccount();
                }
            });
        }
    }

    private void importData() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        ArrayList<Course> list = new CourseService().getAllByStudent(student.getStudentNr());
                        CourseDataSource courseDataSource = new CourseDataSource(context);
                        courseDataSource.deleteAll();
                        for (Course course : list) {
                            courseDataSource.addCourse(course);
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Vakken die op de server staan zijn ingeladen!").show();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vakken laden van server");
        builder.setMessage(
                "Wil je jou vakken laden van de server?\n" +
                        "Als je op ja klikt worden alle vakken die niet " +
                        "op de server staan die momenteel wel op je telefoon staan verwijderd."
        ).setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nee", dialogClickListener).show();
    }

    private void exportData() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        CourseDataSource courseDataSource = new CourseDataSource(context);
                        // code hier om data te sturen naar de api
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Vakken zijn opgeslagen op de server!").show();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vakken opslaan op de server");
        builder.setMessage(
                "Wil je jou vakken opslaan op de server?"
        ).setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nee", dialogClickListener).show();
    }

    private void addAccount() {
        startActivity(new Intent(SettingsActivity.this, AddAccountActivity.class));
    }

    private void removeAccount() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        studentDataSource.deleteStudent(student);
                        Intent i = new Intent(SettingsActivity.this, SettingsActivity.class);
                        i.putExtra("deleteAccount", true);
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
                "Weet je zeker om je account te verwijderen?" +
                        "\nAls je ja klikt verlies je geen gegevens," +
                        "\nje kan alleen niet meer gegevens opslaan\n" +
                        "of ophalen van de server"
        ).setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nee", dialogClickListener).show();
    }

    private void checkMessages() {
        Intent i = getIntent();
        if (i.getSerializableExtra("deleteAccount") != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Je Account is verwijderd!").show();
        }
        if (i.getSerializableExtra("addAccount") != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Je Account is toegevoegd!").show();
        }
    }
}
