package com.dolekapil.androidapplication.caloriemeter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private String name, sex, weight_measure, activity;
    private int age, weight, feet, inches, activity_int;
    RadioGroup group;
    RadioButton maintained, slim, fat;
    double BMR;
    Spinner spinner4, spinner5;

    //Calculating BMR (basic metabolic rate).
    public double calculateCalories(int age, String sex, int weight, String weight_measure, int feet, int inches, int activity){
        double BMR, weight_kgs, height;

        //converting weight from pounds to kgs.
        if(weight_measure.equals("Pounds")){
            weight_kgs = weight/2.2;
        }
        else{
            weight_kgs = weight;
        }

        //converting feet and inches to centimeters.
        height = feet*30.48 + inches*2.54;

        //calculating BMR for male and female separately using Harris Benedict Formula.
        if(sex.equals("Male")){
            BMR = 9.99 * weight_kgs + 6.25 * height - 4.92 * age + 5;
        }
        else{
            BMR = 9.99 * weight_kgs + 6.25 * height - 4.92 * age + 5 - 161;
        }

        //calculation for maintaining weight using Harris Benedict Formula.
        switch (activity){

            case 0: BMR = BMR * 1.2;
                break;
            case 1: BMR = BMR * 1.375;
                break;
            case 2: BMR = BMR * 1.55;
                break;
            case 3: BMR = BMR * 1.725;
                break;
            case 4: BMR = BMR * 1.9;
                break;
        }

        return BMR;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // On click of Back button.
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });

        // On click of Home button.
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent_home);
            }
        });

        // Drop down list for weight(pounds or kilograms)
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_weight, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter);

        // Drop down list for duration.
        spinner5 = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinner_duration, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter2);

    // Getting bundle data.
    Bundle bundle = getIntent().getExtras();
        name = bundle.getString("NAME");
        sex = bundle.getString("GENDER");
        age = Integer.parseInt(bundle.getString("AGE"));
        weight = Integer.parseInt(bundle.getString("WEIGHT"));
        weight_measure = bundle.getString("WEIGHT_MEASURE");
        feet = Integer.parseInt(bundle.getString("FEET"));
        inches = Integer.parseInt(bundle.getString("INCHES"));
        activity = bundle.getString("ACTIVITY");
        if(activity.contains("Sedentary")){
            activity_int = 0;
        }
        else if(activity.contains("Lightly")){
            activity_int = 1;
        }
        else if(activity.contains("Moderate")){
            activity_int = 2;
        }
        else if(activity.contains("Very")){
            activity_int = 3;
        }
        else {
            activity_int = 4;
        }


        //Displaying images on the basis of sex.
        group = (RadioGroup) findViewById(R.id.sex_group);
        maintained = (RadioButton) findViewById(R.id.radioButton4);
        slim = (RadioButton)findViewById(R.id.radioButton3);
        maintained = (RadioButton)findViewById(R.id.radioButton4);
        fat = (RadioButton)findViewById(R.id.radioButton5);
        Drawable men_slim = ContextCompat.getDrawable(this, R.drawable.men_slim_small1);
        Drawable men_maintained = ContextCompat.getDrawable(this, R.drawable.men_maintained_small1);
        Drawable men_fat = ContextCompat.getDrawable(this, R.drawable.men_fat_small);
        Drawable women_slim = ContextCompat.getDrawable(this, R.drawable.women_slim_small);
        Drawable women_maintained = ContextCompat.getDrawable(this, R.drawable.women_maintained_small);
        Drawable women_fat = ContextCompat.getDrawable(this, R.drawable.women_fat_small);
        if(sex.matches("Male")){
            slim.setCompoundDrawablesWithIntrinsicBounds(null, null, men_slim, null);
            maintained.setCompoundDrawablesWithIntrinsicBounds(null, null, men_maintained, null);
            fat.setCompoundDrawablesWithIntrinsicBounds(null, null, men_fat, null);
        }
        else{
            slim.setCompoundDrawablesWithIntrinsicBounds(null, null, women_slim, null);
            maintained.setCompoundDrawablesWithIntrinsicBounds(null, null, women_maintained, null);
            fat.setCompoundDrawablesWithIntrinsicBounds(null, null, women_fat, null);
        }

        // Calorie calculation.
        BMR = calculateCalories(age, sex, weight, weight_measure, feet, inches, activity_int);

        //On click event of Calculate button.
        Button calculate = (Button)findViewById(R.id.button4);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit_weight = (EditText) findViewById(R.id.editText4);
                EditText duration = (EditText) findViewById(R.id.editText5);
                Spinner spinner_weight = (Spinner) findViewById(R.id.spinner4);
                Spinner spinner_duration = (Spinner) findViewById(R.id.spinner5);

                String weight_str = edit_weight.getText().toString();
                String duration_str = duration.getText().toString();
                String spinner_weight_str = spinner_weight.getSelectedItem().toString();
                String spinner_duration_str = spinner_duration.getSelectedItem().toString();

                // Weight validation.
                double weight_validation_before=0, weight_validation_after=0, weight_text_box=0;
                if(edit_weight.getVisibility() == View.VISIBLE){

                    //Weight empty validation.
                    if (weight_str.matches("")) {
                        edit_weight.requestFocus();
                        edit_weight.setError("Weight Required.");
                        return;
                    } else {
                        edit_weight.setError(null);
                    }

                    //Weight max validation.
                    if (Integer.parseInt(weight_str)>1500) {
                        edit_weight.requestFocus();
                        edit_weight.setError("Please Enter Realistic Weight.");
                        return;
                    } else {
                        edit_weight.setError(null);
                    }

                    // Duration validation.
                    if (duration_str.matches("")) {
                        duration.requestFocus();
                        duration.setError("Duration Required.");
                        return;
                    } else {
                        duration.setError(null);
                    }

                    // Duration validation. spinner_duration_str
                    if(spinner_duration_str.matches("Weeks")){
                        if (Integer.parseInt(duration_str)>1000) {
                            duration.requestFocus();
                            duration.setError("Please Enter Realistic Period.");
                            return;
                        }
                    }
                    else if(spinner_duration_str.matches("Months")){
                        if (Integer.parseInt(duration_str)>100) {
                            duration.requestFocus();
                            duration.setError("Please Enter Realistic Period.");
                            return;
                        }
                    }
                    else if(spinner_duration_str.matches("Years")){
                        if (Integer.parseInt(duration_str)>10) {
                            duration.requestFocus();
                            duration.setError("Please Enter Realistic Period.");
                            return;
                        }
                    }
                    else {
                        duration.setError(null);
                    }

                    // weight validation.
                    if (weight_measure.equals("Pounds")) {
                        // convert before and after weight in KG.
                        weight_validation_before = weight / 2.2;

                    } else {
                        weight_validation_before = weight;
                    }

                    weight_text_box = Integer.parseInt(edit_weight.getText().toString());
                    if (spinner_weight_str.equals("Pounds")) {
                        weight_validation_after = weight_text_box / 2.2;
                    }
                    else{
                        weight_validation_after = weight_text_box;
                    }

                    //checking if before and after weight are valid or not.
                    if (group.getCheckedRadioButtonId() == slim.getId()) {
                        if (weight_validation_before <= weight_validation_after) {
                            edit_weight.requestFocus();
                            edit_weight.setError("Goal weight should be less than Current weight.");
                            return;
                        } else {
                            edit_weight.setError(null);
                        }
                    } else if (group.getCheckedRadioButtonId() == fat.getId()) {
                        if (weight_validation_before >= weight_validation_after) {
                            edit_weight.requestFocus();
                            edit_weight.setError("Goal weight should be more than Current weight.");
                            return;
                        } else {
                            edit_weight.setError(null);
                        }
                    }
                }


                //Open new Activity - Activity 3 (Screen)
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                Bundle b = new Bundle();
                if(maintained.isChecked()){
                    b.putString("GOAL","maintain");
                }
                else {
                    b.putDouble("WEIGHT_BEFORE", weight_validation_before);
                    b.putDouble("WEIGHT_AFTER", weight_validation_after);
                    b.putString("DURATION_STR", spinner_duration_str);
                    b.putString("DURATION",duration.getText().toString());
                    if(slim.isChecked()){
                        b.putString("GOAL","slim");
                    }
                    else{
                        b.putString("GOAL","fat");
                    }
                }

                b.putDouble("BMR",BMR);
                b.putString("NAME", name);
                b.putString("WEIGHT_MEASURE",weight_measure);
                intent.putExtras(b);
                startActivity(intent);

                // set all other fields to default values.
                edit_weight.setText("");
                duration.setText("");
                spinner4.setSelection(0);
                spinner5.setSelection(0);
                maintained.setChecked(true);
            }
        });


        // On change listener for radio button for showing and hiding the below text.
        final int maintained_id = maintained.getId();
        final TextView select_text = (TextView) findViewById(R.id.textView13);
        final TextView goal_text = (TextView) findViewById(R.id.textView14);
        final TextView duration_text = (TextView) findViewById(R.id.textView15);
        final EditText edit_weight = (EditText) findViewById(R.id.editText4);
        final EditText edit_duration = (EditText) findViewById(R.id.editText5);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == maintained_id) {
                    select_text.setVisibility(View.INVISIBLE);
                    goal_text.setVisibility(View.INVISIBLE);
                    duration_text.setVisibility(View.INVISIBLE);
                    spinner4.setVisibility(View.INVISIBLE);
                    spinner5.setVisibility(View.INVISIBLE);
                    edit_weight.setVisibility(View.INVISIBLE);
                    edit_duration.setVisibility(View.INVISIBLE);
                } else {
                    select_text.setVisibility(View.VISIBLE);
                    goal_text.setVisibility(View.VISIBLE);
                    duration_text.setVisibility(View.VISIBLE);
                    spinner4.setSelection(0);
                    spinner5.setSelection(0);
                    spinner4.setVisibility(View.VISIBLE);
                    spinner5.setVisibility(View.VISIBLE);
                    edit_weight.setText("");
                    edit_duration.setText("");
                    edit_weight.setVisibility(View.VISIBLE);
                    edit_duration.setVisibility(View.VISIBLE);

                }
            }
        });
    }

}
