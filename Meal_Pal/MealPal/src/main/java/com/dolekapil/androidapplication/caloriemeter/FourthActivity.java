package com.dolekapil.androidapplication.caloriemeter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {
    String name, weight_measure;
    int weight, feet, inches, converted_height, lower_ideal_weight_int, higher_ideal_weight_int;
    double BMI, converted_weight, lower_ideal_weight, higher_ideal_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // getting Intent data.
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("NAME");
        weight = Integer.parseInt(bundle.getString("WEIGHT"));
        weight_measure = bundle.getString("WEIGHT_MEASURE");
        feet = Integer.parseInt(bundle.getString("FEET"));
        inches = Integer.parseInt(bundle.getString("INCHES"));

        // Converting weight to pounds.
        if(weight_measure.matches("Kgs")){
            converted_weight = weight * 2.2;
        }
        else{
            converted_weight = weight;
        }

        // Converting height to inches.
        converted_height = 12 * feet + inches;

        // Calculating lower end of ideal weight range.
        lower_ideal_weight = (18.5 * converted_height * converted_height) / 703;
        lower_ideal_weight = Math.round(lower_ideal_weight);
        lower_ideal_weight_int = (int)lower_ideal_weight;

        // Calculating higher end of ideal weight range.
        higher_ideal_weight = (24.9 * converted_height * converted_height) / 703;
        higher_ideal_weight = Math.round(higher_ideal_weight);
        higher_ideal_weight_int = (int)higher_ideal_weight;

        // Displaying ideal range.
        TextView textView18 = (TextView) findViewById(R.id.textView18);
        textView18.setText(name+", Your ideal weight should be in between");

        TextView textView19 = (TextView) findViewById(R.id.textView19);
        if(weight_measure.matches("Pounds")){
            textView19.setText(lower_ideal_weight_int+" Pounds - "+higher_ideal_weight_int+" Pounds.");
        }
        else{
            textView19.setText(Math.round(lower_ideal_weight_int/2.2)+" Kgs - "+Math.round(higher_ideal_weight_int/2.2)+" Kgs.");
        }

        // Calculate BMI and display weight status.
        BMI = Math.round(((converted_weight * 703) / (converted_height * converted_height))* 10.0)/10.0;
        TextView textView21 = (TextView) findViewById(R.id.textView21);
        TextView textView22 = (TextView) findViewById(R.id.textView22);

        if(BMI<18.5){
            textView21.setText("UnderWeight");
            textView21.setTextColor(Color.YELLOW);
            textView22.setText("You need to gain some weight.");
            textView22.setTextColor(Color.YELLOW);
        }
        else if(BMI >= 18.5 && BMI<25){
            textView21.setText("Normal");
            textView21.setTextColor(Color.GREEN);
            textView22.setText("Good job.");
            textView22.setTextColor(Color.GREEN);
        }
        else if(BMI>=25 && BMI<30){
            textView21.setText("OverWeight");
            textView21.setTextColor(Color.parseColor("#ffaa00"));
            textView22.setText("You need to lose some weight.");
            textView22.setTextColor(Color.parseColor("#ffaa00"));
        }
        else{
            textView21.setText("Obese");
            textView21.setTextColor(Color.RED);
            textView22.setText("You really need to lose weight.");
            textView22.setTextColor(Color.RED);
        }

        // On click of Home button.
        Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(FourthActivity.this,MainActivity.class);
                startActivity(intent_home);
            }
        });



    // On click of back button
        Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}