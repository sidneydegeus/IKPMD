package com.example.ikpmd.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.fragment.ArcProgressFragment;
import com.example.ikpmd.fragment.ECFragment;
import com.example.ikpmd.model.Course;
import com.example.ikpmd.util.MandatoryCourses;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends MainActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ViewPager mImageViewPager;

    private CourseDataSource courseDataSource;

    //data
    private double averageGrade;
    private int totalEC = 0;
    private int currentEC = 0;
    private int totalCourses = 0;
    private int coursesPassed = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_overview, null, false);
        mDrawer.addView(contentView, 0);
        //setActivityTitle("Overzicht");

        courseDataSource = new CourseDataSource(this);
/*        MandatoryCourses mandatoryCourses = new MandatoryCourses();
        ArrayList<Course> list = mandatoryCourses.createGeneralCourses();
        for (Course course : list) {
            courseDataSource.addCourse(course);
        }
        list = mandatoryCourses.createSpecializationCourses();
        for (Course course : list) {
            courseDataSource.addCourse(course);
        }*/

        calculateData();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager, true);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    private void calculateData() {
        List<Course> list = courseDataSource.getAllCourses();
        double totalGrade = 0;
        int participatedTests = 0;
        for (Course course : list) {
            this.totalEC += course.getEc();
            this.totalCourses++;
            if (course.getGrade() >= 5.5) {
                this.currentEC += course.getEc();
                this.coursesPassed++;
            }
            if (course.getGrade() >= 1 && course.getGrade() <= 10) {
                totalGrade += course.getGrade();
                participatedTests++;
            }
        }
        this.averageGrade = (totalGrade / participatedTests);
    }

    public double getAverageGrade() {
        return this.averageGrade;
    }
    public int getTotalEC() {
        return this.totalEC;
    }
    public int getCurrentEC() {
        return this.currentEC;
    }
    public int getTotalCourses() { return this.totalCourses; }
    public int getCoursesPassed() { return this.coursesPassed; }

    ///////////////////////////////////////////////////////////////////////

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if (position == 0) {
                fragment = ArcProgressFragment.newInstance(position + 1);
            } else {
                fragment = ECFragment.newInstance(position + 1);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
