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
import com.example.ikpmd.fragment.PieChartFragment;
import com.example.ikpmd.model.Course;

import java.util.List;

public class OverviewActivity extends BaseActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ViewPager mImageViewPager;

    private CourseDataSource courseDataSource;

    //data
    public double averageGrade;
    public int totalEC = 0;
    public int currentEC = 0;
    public int failedEC = 0;
    public int noGradeEc = 0;
    public int totalCourses = 0;
    public int coursesPassed = 0;
    public int coursesFailed = 0;
    public int coursesNoGrade = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_overview, null, false);
        mDrawer.addView(contentView, 0);
        //setActivityTitle("Overzicht");

        courseDataSource = new CourseDataSource(this);
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
            } else if (course.getGrade() >= 1.0 && course.getGrade() < 5.5) {
                this.failedEC += course.getEc();
                this.coursesFailed++;
            } else if (course.getGrade() == 0) {
                this.noGradeEc += course.getEc();
                coursesNoGrade++;
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
                fragment = PieChartFragment.newInstance(position +1);
            } else if (position == 1) {
                fragment = ArcProgressFragment.newInstance(position + 1);
            } else {
                fragment = ECFragment.newInstance(position + 1);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
