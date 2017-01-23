package com.example.ikpmd.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ikpmd.R;

public class SettingsActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_overview, null, false);
        mDrawer.addView(contentView, 0);
        setActivityTitle("Instellingen");
    }
}
