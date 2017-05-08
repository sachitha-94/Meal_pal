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

public class ThirdActivity extends AppCompatActivity {

    double before_weight, after_weight, BMR, weight_difference;
    String name, goal, duration_str, weight_measure;
    int duration;
    TextView calorie_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Getting intent data.
        Bundle bundle = getIntent().getExtras();
        goal = bundle.getString("GOAL");
        if(!goal.matches("maintain")){
            before_weight = bundle.getDouble("WEIGHT_BEFORE");
            after_weight = bundle.getDouble("WEIGHT_AFTER");
            duration = Integer.parseInt(bundle.getString("DURATION"));
            duration_str = bundle.getString("DURATION_STR");
        }
        BMR = bundle.getDouble("BMR");
        name = bundle.getString("NAME");
        weight_measure = bundle.getString("WEIGHT_MEASURE");

        // On click of Back button.
        Button back = (Button) findViewById(R.id.button5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // On click of Home button.
        Button button3 = (Button) findViewById(R.id.button6);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(ThirdActivity.this,MainActivity.class);
                startActivity(intent_home);
            }
        });


        // Showing results.
        calorie_result = (TextView) findViewById(R.id.textView16);
        TextView calories = (TextView) findViewById(R.id.textView17);
        double weight_pounds_before, weight_pounds_after, calories_required;
        int days_conversion;
        weight_pounds_before = before_weight * 2.2;
        weight_pounds_after = after_weight * 2.2;
        if(goal.matches("maintain")){
            calorie_result.setText(name+", In order to maintain your weight, you need");
            calories.setText(Math.round(BMR)+" Calories/day");
            calories.setTextColor(Color.GREEN);
        }
        else {
            weight_difference = Math.abs(weight_pounds_before - weight_pounds_after);
            calories_required = weight_difference * 3500;

            if(duration_str.matches("Weeks")){
                days_conversion = duration * 7;
            }
            else if(duration_str.matches("Months")){
                days_conversion = duration * 30;
            }
            else{
                days_conversion = duration * 365;
            }

            calories_required = calories_required / days_conversion;
            if(goal.matches("slim")){
                if(BMR < calories_required){
                    calorie_result.setText("Sorry, This is impossible.");
                    calories.setText("");
                }
                else{
                    if(weight_measure.matches("Pounds")){
                        calorie_result.setText(name+", In order to lose your weight from \n"+(int)Math.round(weight_pounds_before)+" Pounds to "+(int)Math.round(weight_pounds_after)+" Pounds \nin "+duration+" "+duration_str+", you need");
                    }
                    else{
                        calorie_result.setText(name+", In order to lose your weight from \n"+(int)Math.round(before_weight) + " Kgs to "+(int)Math.round(after_weight) + " Kgs \nin "+duration+" "+duration_str+", you need");
                    }

                    calories.setText(Math.round(BMR-calories_required)+" Calories/day");
                    calories.setTextColor(Color.YELLOW);
                }
            }
            else{
                if (Math.round(BMR + calories_required)>100000){
                    calorie_result.setText("Sorry, This is impossible.");
                    calories.setText("");
                }
                else{
                    if(weight_measure.matches("Pounds")){
                        calorie_result.setText(name+", In order to increase your weight from \n"+(int)Math.round(weight_pounds_before)+" Pounds to "+(int)Math.round(weight_pounds_after)+" Pounds \nin "+duration+" "+duration_str+", you need");
                    }
                    else{
                        calorie_result.setText(name+", In order to increase your weight from \n"+(int)Math.round(before_weight) + " Kgs to "+(int)Math.round(after_weight) + " Kgs \nin "+duration+" "+duration_str+", you need");
                    }

                    calories.setText(Math.round(BMR+calories_required)+" Calories/day");
                }
            }
        }
    }

}
