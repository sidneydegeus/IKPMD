package com.example.ikpmd;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ikpmd.R;
import com.example.ikpmd.model.Course;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sidney on 1/23/2017.
 */

public class CourseIntroAdapter extends ArrayAdapter<Course> {

    private HashMap<String, Double> gradeValues = new HashMap<String, Double>();

    public CourseIntroAdapter(Context context, ArrayList<Course> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        boolean convertViewWasNull = false;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_intro_course, parent, false);
            convertViewWasNull = true;
        }

        TextView textViewCode = (TextView) convertView.findViewById(R.id.textViewCode);
        textViewCode.setText(course.getCode());

        EditText editTextGrade = (EditText) convertView.findViewById(R.id.editTextGrade);

        if(convertViewWasNull ){

            //be aware that you shouldn't do this for each call on getView, just once by listItem when convertView is null
            editTextGrade.addTextChangedListener(new GenericTextWatcher(editTextGrade));
        }

        editTextGrade.setTag("gradeEdit:"+position);

        return convertView;
    }

    private class GenericTextWatcher implements TextWatcher {

        private View view;
        private int position;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        public void afterTextChanged(Editable editable) {

            String text = editable.toString();
            //save the value for the given tag :
            System.out.println((String) view.getTag());
            CourseIntroAdapter.this.gradeValues.put((String) view.getTag(), Double.parseDouble(editable.toString()));
            //System.out.println(CourseIntroAdapter.this.getItem(position).getGrade());
        }
    }

    //you can implement a method like this one for each EditText with the list position as parameter :
    public double getGrade(int position){
        //here you need to recreate the id for the first editText
        double result = 0;
        if (gradeValues.get("gradeEdit:"+position) != null) {
            result = gradeValues.get("gradeEdit:"+position);
        }
        return result;
    }
}
