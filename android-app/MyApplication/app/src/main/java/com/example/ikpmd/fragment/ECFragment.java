package com.example.ikpmd.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ikpmd.R;
import com.example.ikpmd.activity.OverviewActivity;
import com.github.lzyzsd.circleprogress.ArcProgress;

public class ECFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    int totalEC = 0;
    int currentEC = 0;
    int totalCourses = 0;
    int coursesPassed = 0;

    public ECFragment() {
    }

    public static ECFragment newInstance(int sectionNumber) {
        ECFragment fragment = new ECFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ec_overview, container, false);
        loadData();

        TextView textViewEC = (TextView) rootView.findViewById(R.id.textViewEC);
        textViewEC.setText(String.valueOf(currentEC) + " / " + String.valueOf(totalEC));

        TextView textViewcourses = (TextView) rootView.findViewById(R.id.textViewCourses);
        textViewcourses.setText(String.valueOf(coursesPassed) + " / " + String.valueOf(totalCourses));

        return rootView;
    }

    private void loadData() {
        totalEC = ((OverviewActivity)getActivity()).getTotalEC();
        currentEC = ((OverviewActivity)getActivity()).getCurrentEC();
        totalCourses = ((OverviewActivity)getActivity()).getTotalCourses();
        coursesPassed = ((OverviewActivity)getActivity()).getCoursesPassed();
    }
}
