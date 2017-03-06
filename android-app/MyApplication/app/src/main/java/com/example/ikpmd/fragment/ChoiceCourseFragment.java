package com.example.ikpmd.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.ikpmd.CourseAdapter;
import com.example.ikpmd.R;
import com.example.ikpmd.activity.course.AddCourseActivity;
import com.example.ikpmd.activity.course.Course2Activity;
import com.example.ikpmd.activity.course.EditCourseActivity;
import com.example.ikpmd.model.Course;

import java.util.List;

/**
 * Created by Sidney on 3/5/2017.
 */

public class ChoiceCourseFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public ChoiceCourseFragment() {
    }

    public static ChoiceCourseFragment newInstance(int sectionNumber) {
        ChoiceCourseFragment fragment = new ChoiceCourseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choice_course, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        //ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_expandable_list_item_1, courses);
        CourseAdapter adapter = new CourseAdapter(getActivity(), ((Course2Activity)getActivity()).choiceCourses);
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

        Button button = (Button) rootView.findViewById(R.id.button);
        if (((Course2Activity)getActivity()).choiceCourses.size() >= 4) {
            button.setEnabled(false);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddCourseActivity.class));
            }
        });

        return rootView;
    }
}
