package com.example.ikpmd.activity.course;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ikpmd.CourseDataSource;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.MainActivity;
import com.example.ikpmd.fragment.ChoiceCourseFragment;
import com.example.ikpmd.fragment.CourseFragment;

public class CourseActivity extends MainActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private CourseDataSource courseDataSource;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_course, null, false);
        //setContentView(R.layout.activity_course);
        mDrawer.addView(contentView, 0);
        //setActivityTitle("Vakken");

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        courseDataSource = new CourseDataSource(this);

/*        ArrayList<Course> courses = (ArrayList) courseDataSource.getAllCourses();
        ListView listView = (ListView) findViewById(R.id.listView);
        //ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_expandable_list_item_1, courses);
        CourseAdapter adapter = new CourseAdapter(this, courses);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                Course course = (Course) parent.getAdapter().getItem(position);
                Intent i = new Intent(CourseActivity.this, EditCourseActivity.class);
                i.putExtra("course", course);
                startActivity(i);
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               startActivity(new Intent(CourseActivity.this, AddCourseActivity.class));
           }
        });*/

        checkMessages();
    }

    private void checkMessages() {
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


    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if (position == 0) {
                fragment = CourseFragment.newInstance(position + 1);
            } else {
                fragment = ChoiceCourseFragment.newInstance(position + 1);
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
            switch (position) {
                case 0:
                    return "Algemene";
                case 1:
                    return "Keuze";
            }
            return null;
        }
    }
}
