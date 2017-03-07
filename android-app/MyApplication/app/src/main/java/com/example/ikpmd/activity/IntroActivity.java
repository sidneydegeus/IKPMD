package com.example.ikpmd.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.CourseIntroAdapter;
import com.example.ikpmd.R;
import com.example.ikpmd.StudentDataSource;
import com.example.ikpmd.fragment.CourseIntroFragment;
import com.example.ikpmd.model.Course;
import com.example.ikpmd.model.Student;
import com.example.ikpmd.service.CourseService;
import com.example.ikpmd.util.MandatoryCourses;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidney on 3/7/2017.
 */

public class IntroActivity extends AppIntro {

    public CourseDataSource courseDataSource;
    Student student;

    public ArrayList<Course> courses;

/*    public ArrayList<Course> generalCourses;
    public ArrayList<Course> specialCourses;
    public ArrayList<Course> internshipCourses;*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create database entries
        courseDataSource = new CourseDataSource(this);

        StudentDataSource studentDataSource = new StudentDataSource(this);
        MandatoryCourses mandatoryCourses = new MandatoryCourses();
        ArrayList<Course> generalCourses = mandatoryCourses.createGeneralCourses();
        for (Course course : generalCourses) {
            // if doesnt exist add
            if (courseDataSource.getCourse(course.getCode()) == null) {
                courseDataSource.addCourse(course);
            }
        }
        ArrayList<Course> specialCourses = mandatoryCourses.createSpecializationCourses();
        for (Course course : specialCourses) {
            if (courseDataSource.getCourse(course.getCode()) == null) {
                courseDataSource.addCourse(course);
            }
        }

        ArrayList<Course> internshipCourses = mandatoryCourses.createInternshipCourses();
        for (Course course : internshipCourses) {
            // if doesnt exist add
            if (courseDataSource.getCourse(course.getCode()) == null) {
                courseDataSource.addCourse(course);
            }
        }

        courses = (ArrayList<Course>)courseDataSource.getAllCourses();


        int res = getResources().getIdentifier("com.example.ikpmd:drawable/hsleiden_logo", null, null);
        // Note here that we DO NOT use setContentView();
        student = new Student();
        List<Student> studentList =  studentDataSource.getAllStudents();
        student = studentList.get(0);
        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(AppIntroFragment.newInstance("Studieplanner introductie", "Welkom " + student.getStudentNr() + "!\nOm goed gebruik van de app te maken vragen wij om je cijfers in te voeren.", res, getColor(R.color.primaryBackground)));
        addSlide(CourseIntroFragment.newInstance(1));
        //addSlide(CourseIntroFragment.newInstance(2));
        //addSlide(CourseIntroFragment.newInstance(5));
        addSlide(AppIntroFragment.newInstance("Einde introductie", "Je bent klaar. Keuze vakken en minor dienen handmatig toegevoegd te worden.", res, getColor(R.color.primaryBackground)));



        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        //addSlide(AppIntroFragment.newInstance(title, description, image, backgroundColor));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#c10077"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        if (currentFragment instanceof CourseIntroFragment) {
            CourseIntroFragment courseIntroFragment = (CourseIntroFragment) currentFragment;
            updateCourses(courseIntroFragment.courseIntroAdapter);
            exportData();
        }
        startActivity(new Intent(IntroActivity.this, OverviewActivity.class));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(IntroActivity.this, OverviewActivity.class));
        exportData();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        if (oldFragment instanceof CourseIntroFragment) {
            CourseIntroFragment courseIntroFragment = (CourseIntroFragment) oldFragment;
            updateCourses(courseIntroFragment.courseIntroAdapter);
        }
    }

    private void updateCourses(CourseIntroAdapter courseIntroAdapter) {
        for(int i = 0; i < courseIntroAdapter.getCount(); i++){
            courses.get(i).setGrade(courseIntroAdapter.getGrade(i));
            courseDataSource.updateCourse(courses.get(i));
        }
    }

    private void exportData() {
        CourseDataSource courseDataSource = new CourseDataSource(this);
        List<Course> courseList = courseDataSource.getAllCourses();
        CourseService courseService = new CourseService();
        courseService.addCoursesByStudent(courseList, student.getStudentNr());
    }
}
