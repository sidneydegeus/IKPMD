package com.example.ikpmd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ikpmd.model.Course;

import java.util.ArrayList;

/**
 * Created by Sidney on 1/23/2017.
 */

public class CourseAdapter extends ArrayAdapter<Course> {


    public CourseAdapter(Context context, ArrayList<Course> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_course, parent, false);
        }

        TextView textViewCode = (TextView) convertView.findViewById(R.id.textViewCode);
        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        TextView textViewEc = (TextView) convertView.findViewById(R.id.textViewEc);
        TextView textViewGrade = (TextView) convertView.findViewById(R.id.textViewGrade);

        textViewCode.setText(course.getCode());
        textViewName.setText(course.getName());
        textViewEc.setText(String.valueOf(course.getEc()));
        textViewGrade.setText(String.valueOf(course.getGrade()));

        return convertView;
    }

}
