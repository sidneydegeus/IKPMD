package com.example.ikpmd.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ikpmd.R;
import com.example.ikpmd.activity.OverviewActivity;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.text.DecimalFormat;

import static android.graphics.Color.parseColor;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArcProgressFragment extends Fragment {

    private Activity mActivity;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public ArcProgressFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ArcProgressFragment newInstance(int sectionNumber) {
        ArcProgressFragment fragment = new ArcProgressFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arc_overview, container, false);
        ArcProgress arcProgress = (ArcProgress) rootView.findViewById(R.id.arc_progress);
/*        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/

        loadData(arcProgress);
        return rootView;
    }

    private void loadData(ArcProgress arcProgress) {
        double averageGrade = ((OverviewActivity)getActivity()).getAverageGrade();

        arcProgress.setStrokeWidth(30f);
        arcProgress.setTextSize(250f);
        arcProgress.setSuffixTextSize(100f);
        arcProgress.setMax(10);

        arcProgress.setFinishedStrokeColor(arcColor(averageGrade));
        arcProgress.setProgress((int) Math.floor(averageGrade));

        double fractionalPart = averageGrade % 1;
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String fractional = decimalFormat.format(fractionalPart);
        arcProgress.setSuffixText(fractional);
    }

    private int arcColor(double grade) {
        int color;
        if (grade < 4.0) {
            color = Color.RED;
        } else if (grade >= 4.0 && grade < 5.5) {
            color = parseColor("#FFC6640E");
        } else if (grade >= 5.5 && grade <= 6.9) {
            color = Color.YELLOW;
        } else {
            color = Color.GREEN;
        }
        return color;
    }
}
