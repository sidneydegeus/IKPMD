package com.example.ikpmd.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.ikpmd.R;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = getSharedPreferences("com.example.ikpmd", MODE_PRIVATE);
        //prefs.edit().putBoolean("firstrun", true).apply();
    }

    @Override protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            final Intent i;
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            // add account activity
                            Intent i = new Intent(MainActivity.this, AccountActivity.class);
                            i.putExtra("register", 1);
                            startActivity(i);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            ///login activity
                            i = new Intent(MainActivity.this, AccountActivity.class);
                            startActivity(i);
                            break;
                    }
                }
            };

            builder.setTitle("Welkom!");
            builder.setMessage(
                    "Is dit de eerste keer dat je gebruik maakt van deze app?"
            ).setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nee", dialogClickListener).show();
        } else {
            startActivity(new Intent(MainActivity.this, OverviewActivity.class));
        }
    }
}
