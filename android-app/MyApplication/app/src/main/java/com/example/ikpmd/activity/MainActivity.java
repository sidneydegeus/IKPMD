package com.example.ikpmd.activity;

import android.content.Intent;
import android.support.v4.view.WindowCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ikpmd.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    protected DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);

        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToOverview(MenuItem item) {
        startActivity(new Intent(MainActivity.this, OverviewActivity.class));
    }

    public void goToCourses(MenuItem menuItem) {
        startActivity(new Intent(MainActivity.this, CourseActivity.class));
    }

    public void goToSettings(MenuItem menuItem) {
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }

    protected void setActivityTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.screenTitle);
        textView.setText(title);
    }
}
