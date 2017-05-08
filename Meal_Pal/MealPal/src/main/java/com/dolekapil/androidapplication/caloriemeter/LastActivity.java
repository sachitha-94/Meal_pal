package com.dolekapil.androidapplication.caloriemeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LastActivity extends AppCompatActivity {

    double BMR, MET, time_duration, burned_weight;
    String time_measure, weight_measure, activity, name;
    long calorie_burn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    //Getting bundle data.
        Bundle bundle = getIntent().getExtras();
        BMR = bundle.getDouble("BMR");
        MET = bundle.getDouble("MET");
        time_duration = bundle.getDouble("TIME_DURATION");
        time_measure = bundle.getString("TIME_MEASURE");
        weight_measure = bundle.getString("WEIGHT_MEASURE");
        activity = bundle.getString("ACTIVITY");
        name = bundle.getString("NAME");

        // On click of back button.
        Button back_button = (Button) findViewById(R.id.button14);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // On click of Home button.
        Button button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(LastActivity.this, MainActivity.class);
                startActivity(intent_home);
            }
        });

        // Converting minutes to hour
        if(time_measure.matches("Minutes")){
            time_duration = time_duration / 60.0;
        }


        // Calculating calories burned.
        calorie_burn = Math.round((BMR/24) * MET * time_duration);

        // calculate weight burned.
        if(weight_measure.matches("Pounds")){
            burned_weight = calorie_burn/3500.0;
            burned_weight = Math.round(burned_weight * 100.0)/100.0;
        }
        else{
            burned_weight = calorie_burn/(3500.0 * 2.2);
            burned_weight = Math.round(burned_weight * 100.0)/100.0;
        }

        // displaying results.
        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView29 = (TextView) findViewById(R.id.textView29);
        TextView textView30 = (TextView) findViewById(R.id.textView30);

        textView28.setText(name+", You have burned");
        textView29.setText(calorie_burn+" Calories");
        if(burned_weight>0){
            textView30.setText("which is equivalent to\n"+burned_weight+" "+weight_measure+" of weight.");
        }
    }
}
