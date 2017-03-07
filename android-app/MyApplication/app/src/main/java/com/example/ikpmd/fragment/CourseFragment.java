package com.example.ikpmd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ikpmd.CourseAdapter;
import com.example.ikpmd.R;
import com.example.ikpmd.CourseIntroAdapter;
import com.example.ikpmd.activity.IntroActivity;
import com.example.ikpmd.activity.course.Course2Activity;
import com.example.ikpmd.activity.course.EditCourseActivity;
import com.example.ikpmd.model.Course;

import java.util.ArrayList;

/**
 * Created by Sidney on 3/5/2017.
 */

public class CourseFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public CourseFragment() {
    }

    public static CourseFragment newInstance(int sectionNumber) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        ArrayList<Course> list;
        // defining general as the list so that, if it fails, it contains a list atleast
        list = ((Course2Activity)getActivity()).generalCourses;

        if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
            list = ((Course2Activity)getActivity()).specialCourses;
        } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 4) {
            list = ((Course2Activity)getActivity()).minorCourses;
        } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 5) {
            list = ((Course2Activity)getActivity()).internshipCourses;
        }

        CourseAdapter adapter = new CourseAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                Course course = (Course) parent.getAdapter().getItem(position);
                Intent i = new Intent(getActivity(), EditCourseActivity.class);
                i.putExtra("course", course);
                startActivity(i);
            }
        });

        return rootView;
    }

}
