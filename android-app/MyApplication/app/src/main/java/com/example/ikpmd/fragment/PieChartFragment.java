package com.example.ikpmd.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ikpmd.R;
import com.example.ikpmd.activity.MainActivity;
import com.example.ikpmd.activity.OverviewActivity;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.graphics.Color.parseColor;

/**
 * A placeholder fragment containing a simple view.
 */
public class PieChartFragment extends Fragment {

    private static String TAG = "MainActivity";

    PieChart pieChart;

    private int[] yData;
    private String[] xData;

    private Activity mActivity;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PieChartFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PieChartFragment newInstance(int sectionNumber) {
        PieChartFragment fragment = new PieChartFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_piechart, container, false);
        pieChart = (PieChart) rootView.findViewById(R.id.pieChart);

        //pieChart.setDescription();
        pieChart.setRotationEnabled(false);
        pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        loadData();
        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int pos1 = e.toString().indexOf("(sum): ");
                for(int i = 0; i < yData.length; i++){
                    if(yData[i] == e.getY()){
                        pos1 = i;
                        break;
                    }
                }
                String status = xData[pos1];
                String text = "";
                if (status.equals("Passed")) {
                    text = "" + ((OverviewActivity)getActivity()).coursesPassed
                        + " van de " + ((OverviewActivity)getActivity()).totalCourses
                        + " vakken gehaald.\nDeze vakken bevatten " + ((OverviewActivity)getActivity()).currentEC
                            + " EC van de totale " + ((OverviewActivity)getActivity()).totalEC + " EC";
                } else if (status.equals("Failed")) {
                    text = "" + ((OverviewActivity)getActivity()).coursesFailed
                            + " van de " + ((OverviewActivity)getActivity()).totalCourses
                            + " vakken niet gehaald.\nDeze vakken bevatten " + ((OverviewActivity)getActivity()).failedEC
                            + " EC van de totale " + ((OverviewActivity)getActivity()).totalEC + " EC";
                } else {
                    text = "" + ((OverviewActivity)getActivity()).coursesNoGrade
                            + " van de " + ((OverviewActivity)getActivity()).totalCourses
                            + " vakken waar nog geen cijfer voor gehaald zijn.\nDeze vakken bevatten " + ((OverviewActivity)getActivity()).noGradeEc
                            + " EC van de totale " + ((OverviewActivity)getActivity()).totalEC + " EC";
                }
                Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        return rootView;
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Overzicht behaalde vakken");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);





        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void loadData() {
        yData = new int[]{
                ((OverviewActivity)getActivity()).coursesPassed,
                ((OverviewActivity)getActivity()).coursesFailed,
                ((OverviewActivity)getActivity()).coursesNoGrade
         };
        xData = new String[]{"Passed", "Failed" , "No grade"};

    }
}
