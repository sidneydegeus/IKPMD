package com.example.ikpmd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ikpmd.CourseIntroAdapter;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.IntroActivity;
import com.example.ikpmd.model.Course;

import java.util.ArrayList;

/**
 * Created by Sidney on 3/5/2017.
 */

public class CourseIntroFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public CourseIntroAdapter courseIntroAdapter;

    public CourseIntroFragment() {
    }

    public static CourseIntroFragment newInstance(int sectionNumber) {
        CourseIntroFragment fragment = new CourseIntroFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_intro_course, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        ArrayList<Course> list;
        // defining general as the list so that, if it fails, it contains a list atleast
        list = ((IntroActivity)getActivity()).courses;

        courseIntroAdapter = new CourseIntroAdapter(getActivity(), list);
        listView.setAdapter(courseIntroAdapter);

        return rootView;
    }


}
