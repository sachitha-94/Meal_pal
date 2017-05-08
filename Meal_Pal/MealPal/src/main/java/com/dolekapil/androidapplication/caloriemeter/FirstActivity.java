package com.dolekapil.androidapplication.caloriemeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class FirstActivity extends AppCompatActivity {

    String name, age, gender, weight, weight_measure, feet, inches, activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Getting intent data.
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("NAME");
        age = bundle.getString("AGE");
        weight = bundle.getString("WEIGHT");
        weight_measure = bundle.getString("WEIGHT_MEASURE");
        feet = bundle.getString("FEET");
        inches = bundle.getString("INCHES");
        activity = bundle.getString("ACTIVITY");
        gender = bundle.getString("GENDER");


        // On click of calories required button.
        Button calories_required_button = (Button) findViewById(R.id.button9);
        calories_required_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing user data to next activity.
                Intent pass_intent = new Intent(FirstActivity.this,SecondActivity.class);
                pass_intent.putExtra("NAME",name);
                pass_intent.putExtra("AGE",age);
                pass_intent.putExtra("WEIGHT",weight);
                pass_intent.putExtra("WEIGHT_MEASURE",weight_measure);
                pass_intent.putExtra("FEET",feet);
                pass_intent.putExtra("INCHES",inches);
                pass_intent.putExtra("ACTIVITY",activity);
                pass_intent.putExtra("GENDER",gender);
                startActivity(pass_intent);
            }
        });

    // On click of Home button.
    Button home = (Button) findViewById(R.id.button7);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //On click of Calculate ideal weight.
        Button ideal_wt_button = (Button) findViewById(R.id.button8);
        ideal_wt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing data for ideal weight calculation.
                Intent ideal_intent = new Intent(FirstActivity.this,FourthActivity.class);
                ideal_intent.putExtra("NAME",name);
                ideal_intent.putExtra("WEIGHT",weight);
                ideal_intent.putExtra("WEIGHT_MEASURE",weight_measure);
                ideal_intent.putExtra("FEET",feet);
                ideal_intent.putExtra("INCHES",inches);
                startActivity(ideal_intent);
            }
        });

        // On click of calories burned button.
        Button calories_burned_button = (Button) findViewById(R.id.button10);
        calories_burned_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
                Intent calories_burned_intent = new Intent(FirstActivity.this,FifthActivity.class);
                Bundle burn_bundle = new Bundle();
                burn_bundle.putString("NAME_LAST", name);
                burn_bundle.putInt("AGE_LAST", Integer.parseInt(age));
                burn_bundle.putInt("WEIGHT_LAST", Integer.parseInt(weight));
                burn_bundle.putString("WEIGHT_MEASURE_LAST", weight_measure);
                burn_bundle.putInt("FEET_LAST", Integer.parseInt(feet));
                burn_bundle.putInt("INCHES_LAST", Integer.parseInt(inches));
                burn_bundle.putString("ACTIVITY_LAST", activity);
                burn_bundle.putString("GENDER_LAST", gender);
                calories_burned_intent.putExtras(burn_bundle);
                startActivity(calories_burned_intent);
            }
        });
    }

}
