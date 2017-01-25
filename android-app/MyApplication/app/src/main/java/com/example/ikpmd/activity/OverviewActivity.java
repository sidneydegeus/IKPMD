package com.example.ikpmd.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.MySQLiteHelper;
import com.example.ikpmd.R;
import com.example.ikpmd.model.Course;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.text.DecimalFormat;
import java.util.List;

import static android.graphics.Color.parseColor;

public class OverviewActivity extends MainActivity {

    private CourseDataSource courseDataSource;

    //data
    private double averageGrade;
    private int totalEC = 0;
    private int currentEC = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_overview, null, false);
        mDrawer.addView(contentView, 0);
        setActivityTitle("Overzicht");

        courseDataSource = new CourseDataSource(this);
        calculateData();

        defineArcProgress();
    }

    private void defineArcProgress() {
        ArcProgress arcProgress = (ArcProgress) findViewById(R.id.arc_progress);
        arcProgress.setStrokeWidth(30f);
        arcProgress.setTextSize(250f);
        arcProgress.setSuffixTextSize(100f);
        arcProgress.setMax(10);

        arcProgress.setFinishedStrokeColor(arcColor(averageGrade));
        arcProgress.setProgress((int) Math.floor(averageGrade));

        double fractionalPart = averageGrade % 1;
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String fractional = decimalFormat.format(fractionalPart);
        arcProgress.setSuffixText(fractional);
    }

    private int arcColor(double grade) {
        int color;
        if (grade < 4.0) {
            color = Color.RED;
        } else if (grade >= 4.0 && grade < 5.5) {
            color = parseColor("#FFC6640E");
        } else if (grade >= 5.5 && grade <= 6.9) {
            color = Color.YELLOW;
        } else {
            color = Color.GREEN;
        }
        return color;
    }

    private void calculateData() {
        List<Course> list = courseDataSource.getAllCourses();
        double totalGrade = 0;
        int participatedTests = 0;
        for (Course course : list) {
            this.totalEC += course.getEc();
            if (course.getGrade() >= 5.5) {
                this.currentEC += course.getEc();
            }
            if (course.getGrade() >= 1 && course.getGrade() <= 10) {
                totalGrade += course.getGrade();
                participatedTests++;
            }
        }
        this.averageGrade = (totalGrade / participatedTests);
    }
}
