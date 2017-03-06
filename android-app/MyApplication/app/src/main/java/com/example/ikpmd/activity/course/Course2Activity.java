package com.example.ikpmd.activity.course;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.MainActivity;
import com.example.ikpmd.fragment.ChoiceCourseFragment;
import com.example.ikpmd.fragment.CourseFragment;
import com.example.ikpmd.model.Course;

import java.util.ArrayList;

public class Course2Activity extends MainActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private CourseDataSource courseDataSource;
    private Context context;

    public ArrayList<Course> generalCourses;
    public ArrayList<Course> specialCourses;
    public ArrayList<Course> choiceCourses;
    public ArrayList<Course> minorCourses;
    public ArrayList<Course> internshipCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_course2, null, false);
        mDrawer.addView(contentView, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        courseDataSource = new CourseDataSource(this);

        generalCourses = (ArrayList) courseDataSource.getAllCourses(Course.COURSE_TYPE_GENERAL);
        specialCourses = (ArrayList) courseDataSource.getAllCourses(Course.COURSE_TYPE_SPECIALIZATION);
        choiceCourses = (ArrayList) courseDataSource.getAllCourses(Course.COURSE_TYPE_CHOICE);
        minorCourses = (ArrayList) courseDataSource.getAllCourses(Course.COURSE_TYPE_MINOR);
        internshipCourses = (ArrayList) courseDataSource.getAllCourses(Course.COURSE_TYPE_INTERNSHIP);

        Intent i = getIntent();
        if (i.getSerializableExtra("fragmentNumber") != null) {
            mViewPager.setCurrentItem(Integer.parseInt(i.getSerializableExtra("fragmentNumber").toString()));
        }
    }

    public void checkMessages() {
        Intent i = getIntent();
        if (i.getSerializableExtra("addCourse") != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Het vak is toegevoegd!").show();
        }
        if (i.getSerializableExtra("editCourse") != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Het vak is aangepast!").show();
        }
        if (i.getSerializableExtra("deleteCourse") != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Het vak is verwijderd!").show();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if (position == Course.COURSE_TYPE_CHOICE) {
                fragment = ChoiceCourseFragment.newInstance(position + 1);
            } else {
                fragment = CourseFragment.newInstance(position + 1);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Alg";
                case 1:
                    return "Spec";
                case 2:
                    return "Keuze";
                case 3:
                    return "Minor";
                case 4:
                    return "Stage";
            }
            return null;
        }
    }
}
