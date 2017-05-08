package com.dolekapil.androidapplication.caloriemeter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class FifthActivity extends AppCompatActivity {

    // Intent values
    String name, gender, weight_measure, final_activity, activity;
    int age, weight, feet, inches, activity_int;
    double BMR, MET_value, time_duration;


    // Gym activities icons and strings.
    String[] gym_activities_strings = {"Select Activity","Aerobics: General","Aerobics: Water","Bicycling Stationary: General",
    "Calisthenics (e.g. Pushups, Situps, Pullups): Vigorous Effort","Calisthenics, Home Exercise (e.g. Up-Down On Stairs): Moderate Effort",
    "Circuit Training: General","Health Club Exercise: General","Rowing, Stationary Ergometer, Ski Machine: General","Rowing, Stationary Ergometer: Vigorous Effort",
    "Stretching, Hatha Yoga","Stair-Treadmill Ergometer: General", "Weight Lifting, Power Lifting or Body Building, Slimnastics: Vigorous Effort",
    "Weight Lifting, Light Workout: Moderate Effort"};

    int gym_activities_images[] = { R.drawable.select_activity_icon, R.drawable.aerobics_general_icon, R.drawable.aerobics_water_icon, R.drawable.bicycle_stationary_icon,
    R.drawable.calisthenics_vigorous_icon, R.drawable.calisthenics_moderate_icon, R.drawable.circuit_training_icon, R.drawable.health_club_icon,
    R.drawable.rowing_stationary_icon, R.drawable.rowing_stationary_icon, R.drawable.streaching_yoga_icon, R.drawable.treadmill_icon,
            R.drawable.weightlifting_icon, R.drawable.powerlifting_icon};

    double gym_activities_MET [] = {0, 6.5, 4.0, 7.0, 8.0, 3.5, 8.0, 5.5, 12.0, 7.0, 2.5, 9.0, 6.0, 3.0};


    //Sports activities icons and strings.
    String[] sports_activities_strings ={"Select Activity", "Archery (Non-Hunting)","Badminton: Competitive", "Basketball: Game", "Billiards",
    "Bowling","Boxing In Ring: General", "Boxing: Punching Bag", "Cricket: Batting, Bowling", "Cycling: BMX or Mountain", "Cycling: 12-13.9 mph",
    "Cycling: 14-15.9 mph", "Cycling: 16-19 mph", "Cycling: >20 mph", "Dancing: Slow, Waltz, Foxtrot", "Dancing: Disco, Ballroom, Square",
    "Dancing: Fast, Ballet, Twist", "Football: Competitive", "Frisbee Playing: General", "Golf: General", "Gymnastics: General", "Handball: Team",
    "Hockey: Field or Ice", "Judo, Jujitsu, Karate, Kick Boxing, Tae Kwan", "Jogging: General", "Rugby", "Running: 5-5.9 mph",
    "Running: 6-6.9 mph", "Running: 7-7.9 mph", "Running: 8-8.9 mph", "Running: 9-9.9 mph", "Running: >10 mph", "Running: Cross Country",
    "Skateboarding", "Soccer: Competitive", "Softball or Baseball: General", "Swimming Laps Freestyle: Vigorous", "Swimming Laps Freestyle: Moderate",
    "Swimming Backstroke: General", "Swimming Breaststroke: General", "Swimming Butterfly, Crawl: General", "Table Tennis, Ping Pong","Tennis: General",
    "Volleyball, Volleyball Beach: Competitive", "Wrestling (One Match = 5 Minutes)","Walking: 3.0 mph", "Walking: 4.0 mph", "Walking: 5.0 mph"};

    int sports_activities_images[]={R.drawable.select_activity_icon, R.drawable.archery_icon, R.drawable.badminton_icon, R.drawable.basketball_icon, R.drawable.billiards_icon,
    R.drawable.bowling_icon,R.drawable.boxing_icon,R.drawable.boxing_icon, R.drawable.cricket_icon, R.drawable.cycling_icon, R.drawable.cycling_icon,
    R.drawable.cycling_icon, R.drawable.cycling_icon, R.drawable.cycling_icon, R.drawable.dancing_icon, R.drawable.dancing_icon,
    R.drawable.dancing_icon, R.drawable.football_icon, R.drawable.frisbee_icon, R.drawable.golf_icon, R.drawable.gymnastics_icon, R.drawable.handball_icon,
    R.drawable.hockey_icon, R.drawable.judo_karate_icon, R.drawable.calisthenics_moderate_icon, R.drawable.football_icon, R.drawable.running_icon,
    R.drawable.running_icon, R.drawable.running_icon, R.drawable.running_icon, R.drawable.running_icon, R.drawable.running_icon, R.drawable.running_icon,
    R.drawable.skateboarding_icon, R.drawable.soccer_icon, R.drawable.baseball_icon, R.drawable.swimming_icon, R.drawable.swimming_icon,
    R.drawable.swimming_icon, R.drawable.swimming_icon, R.drawable.swimming_icon, R.drawable.pingpong_icon, R.drawable.tennis_icon,
    R.drawable.volleyball_icon, R.drawable.wrestling_icon, R.drawable.walking_icon, R.drawable.walking_icon, R.drawable.walking_icon};

    double sports_activities_MET []= {0, 3.5, 7.0, 8.0, 2.5, 3.0, 12.0, 6.0, 5.0, 8.5, 8.0, 10.0, 12.0, 16.0, 3.0, 4.5, 4.8, 9.0,
    3.0, 4.5, 4.0, 8.0, 8.0, 10.0, 7.0, 10.0, 8.5, 10.5, 12.0, 14.0, 15.0, 17.0, 9.0, 5.0, 10.0, 5.0, 10.0, 7.0, 7.0, 10.0, 11.0, 4.0,
    7.0, 8.0, 6.0, 3.3, 5.0, 8.0};


    //outdoor activities and icons.
    String[] outdoor_activities_strings = {"Select Activity","Chopping and Splitting Wood", "Digging, Spading Dirt, Carrying and Stacking Wood, Laying Sod / Crushed Rock",
    "Mowing Lawn: Push, Power", "Mowing Lawn: Push, Hand", "Operate Snow Blower: Walking", "Planting: Seedlings, Shrubs, Trees",
    "Raking Lawn", "Sacking Grass or Leaves, Gardening: General", "Shoveling Snow: By Hand"};

    int outdoor_activities_images[] ={R.drawable.select_activity_icon,R.drawable.chopping_icon, R.drawable.digging_icon, R.drawable.mowing_icon, R.drawable.mowing_icon,
    R.drawable.snow_blower_icon, R.drawable.planting_icon, R.drawable.raking_lawn_icon, R.drawable.raking_lawn_icon, R.drawable.digging_icon};

    double outdoor_activities_MET[] = {0, 6.0, 5.0, 5.5, 6.0, 4.5, 4.5, 4.3, 4.0, 6.0};


    // Home and daily activities.
    String[] home_daily_strings = {"Select Activity", "Child-Care: Bathing, Feeding, etc.", "Child Games: Hop-Scotch, Jacks, etc.", "Cooking", "Food Shopping: With Cart",
    "Heavy Cleaning: Wash Car, Windows, etc.", "Moving: Unpacking", "Moving: Household Furniture, Carrying Boxes", "Playing With Kids: Moderate Effort",
    "Playing With Kids: Vigorous Effort", "Reading: Sitting, Sitting Quietly, Sitting Smoking, Listening to Music, Meditating", "Sleeping",
    "Standing in Line", "Watching TV"};

    int home_daily_images[]= {R.drawable.select_activity_icon, R.drawable.child_care_icon, R.drawable.child_games_icon, R.drawable.cooking_icon, R.drawable.shopping_icon,
    R.drawable.cleaning_icon, R.drawable.unpacking_icon, R.drawable.furniture_moving_icon, R.drawable.playing_kids_icon, R.drawable.playing_kids_icon,
    R.drawable.sitting_icon, R.drawable.sleep_icon, R.drawable.standing_line_icon, R.drawable.television_icon};

    double home_daily_MET[]= {0, 2.5, 5.0, 2.0, 2.3, 3.0, 3.5, 6.0, 4.0, 5.0, 1.0, 0.9, 1.2, 1.0};


    // Sexual activities.
    String[] sex_activity_strings={"Select Activity", "Intercourse: Vigorous Effort", "Intercourse: Moderate Effort", "Intercourse: Light Effort, Kissing, Hugging"};

    int sex_activity_images[]={R.drawable.select_activity_icon, R.drawable.sex_new_icon, R.drawable.sex_new_icon, R.drawable.sex_new_icon};

    double sex_activity_MET[]={0, 1.5, 1.3, 1.0};


    //Calculating BMR (basic metabolic rate).
    public double calculateCalories(int age, String sex, int weight, String weight_measure, int feet, int inches){
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

        return BMR;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // getting intent data.
        Bundle intent_bundle = getIntent().getExtras();
        name = intent_bundle.getString("NAME_LAST");
        age = intent_bundle.getInt("AGE_LAST");
        gender = intent_bundle.getString("GENDER_LAST");
        weight = intent_bundle.getInt("WEIGHT_LAST");
        weight_measure = intent_bundle.getString("WEIGHT_MEASURE_LAST");
        feet = intent_bundle.getInt("FEET_LAST");
        inches = intent_bundle.getInt("INCHES_LAST");
        activity = intent_bundle.getString("ACTIVITY_LAST");

        // On click of back button.
        Button back_button = (Button) findViewById(R.id.button20);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // On click of Home button.
        Button button21 = (Button) findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(FifthActivity.this, MainActivity.class);
                startActivity(intent_home);
            }
        });


        // Spinner time duration.
        Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
        ArrayAdapter<CharSequence> adapter_duration = ArrayAdapter.createFromResource(this,
                R.array.spinner_time_duration, android.R.layout.simple_spinner_item);
        adapter_duration.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter_duration);


        // Spinner Activity types
        final Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter_activity_type = ArrayAdapter.createFromResource(this,
                R.array.spinner_activity_types, android.R.layout.simple_spinner_item);
        adapter_activity_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter_activity_type);

        // On click event of spinner.
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_item = parent.getItemAtPosition(position).toString();
                TextView textView26 = (TextView) findViewById(R.id.textView26);
                Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);

                if (selected_item.matches("Select Activity Type")) {
                    textView26.setVisibility(View.INVISIBLE);
                    spinner7.setVisibility(View.INVISIBLE);
                } else if (selected_item.matches("Gym Activities")) {
                    spinner7.setAdapter(new GymAdapter(FifthActivity.this, R.layout.row, gym_activities_strings));
                    textView26.setVisibility(View.VISIBLE);
                    spinner7.setVisibility(View.VISIBLE);
                } else if (selected_item.matches("Training and Sport Activities")) {
                    spinner7.setAdapter(new SportAdapter(FifthActivity.this, R.layout.row, sports_activities_strings));
                    textView26.setVisibility(View.VISIBLE);
                    spinner7.setVisibility(View.VISIBLE);
                } else if (selected_item.matches("Outdoor Activities")) {
                    spinner7.setAdapter(new OutDoorAdapter(FifthActivity.this, R.layout.row, outdoor_activities_strings));
                    textView26.setVisibility(View.VISIBLE);
                    spinner7.setVisibility(View.VISIBLE);
                } else if (selected_item.matches("Home and Daily Life Activities")) {
                    spinner7.setAdapter(new DailyAdapter(FifthActivity.this, R.layout.row, home_daily_strings));
                    textView26.setVisibility(View.VISIBLE);
                    spinner7.setVisibility(View.VISIBLE);
                } else if (selected_item.matches("Sexual Activities")) {
                    spinner7.setAdapter(new SexAdapter(FifthActivity.this, R.layout.row, sex_activity_strings));
                    textView26.setVisibility(View.VISIBLE);
                    spinner7.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // On click of calculate button.
        Button button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
                Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
                Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
                final EditText editText6 = (EditText) findViewById(R.id.editText6);
                final TextView spinner6_textview = (TextView) spinner6.getSelectedView();
                final TextView spinner7_textview = (TextView) findViewById(R.id.activity);


                // Activity type validation.
                if(spinner6.getSelectedItem().toString().matches("Select Activity Type")){
                    spinner6_textview.setError("");
                    return;
                }

                // Activity validation.
                if(spinner7.getSelectedItem().toString().matches("Select Activity")){
                    spinner7_textview.setError("");
                    return;
                }

                // Duration text box validation.
                if(editText6.getText().toString().matches("")){
                    editText6.setError("Duration Required.");
                    return;
                }

                // send the data to next screen.
                BMR = calculateCalories(age,gender,weight,weight_measure,feet,inches);

                // getting MET value.
                if(spinner6.getSelectedItem().toString().matches("Gym Activities")){
                    MET_value = gym_activities_MET[spinner7.getSelectedItemPosition()];
                    final_activity = gym_activities_strings[spinner7.getSelectedItemPosition()];
                }
                else if(spinner6.getSelectedItem().toString().matches("Training and Sport Activities")){
                    MET_value = sports_activities_MET[spinner7.getSelectedItemPosition()];
                    final_activity = sports_activities_strings[spinner7.getSelectedItemPosition()];
                }
                else if(spinner6.getSelectedItem().toString().matches("Outdoor Activities")){
                    MET_value = outdoor_activities_MET[spinner7.getSelectedItemPosition()];
                    final_activity = outdoor_activities_strings[spinner7.getSelectedItemPosition()];
                }
                else if(spinner6.getSelectedItem().toString().matches("Home and Daily Life Activities")){
                    MET_value = home_daily_MET[spinner7.getSelectedItemPosition()];
                    final_activity = home_daily_strings[spinner7.getSelectedItemPosition()];
                }
                else if(spinner6.getSelectedItem().toString().matches("Sexual Activities")){
                    MET_value = sex_activity_MET[spinner7.getSelectedItemPosition()];
                    final_activity = sex_activity_strings[spinner7.getSelectedItemPosition()];
                }


                // getting time.
                time_duration = Integer.parseInt(editText6.getText().toString());


                // Sending intent data to last screen.
                Intent last_intent = new Intent(FifthActivity.this,LastActivity.class);
                Bundle last_bundle = new Bundle();
                String time_measure_final = spinner8.getSelectedItem().toString();
                last_bundle.putString("NAME", name);
                last_bundle.putDouble("BMR", BMR);
                last_bundle.putDouble("MET", MET_value);
                last_bundle.putDouble("TIME_DURATION", time_duration);
                last_bundle.putString("TIME_MEASURE", time_measure_final);
                last_bundle.putString("WEIGHT_MEASURE", weight_measure);
                last_bundle.putString("ACTIVITY", final_activity);
                last_intent.putExtras(last_bundle);
                startActivity(last_intent);


                // Setting default values.
                spinner6.setSelection(0);
                spinner8.setSelection(0);
                editText6.setText("");
            }
        });
    }


    // Custom adapter for gym activities.
    public class GymAdapter extends ArrayAdapter<String> {

        public GymAdapter(Context context, int textViewResourceId,   String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.activity);
            label.setText(gym_activities_strings[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            icon.setImageResource(gym_activities_images[position]);

            return row;
        }
    }



    // custom adapter for sport activities.
    public class SportAdapter extends ArrayAdapter<String> {

        public SportAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.activity);
            label.setText(sports_activities_strings[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            icon.setImageResource(sports_activities_images[position]);

            return row;
        }
    }



    // Custom adapter for outdoor activities.
    public class OutDoorAdapter extends ArrayAdapter<String> {

        public OutDoorAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.activity);
            label.setText(outdoor_activities_strings[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            icon.setImageResource(outdoor_activities_images[position]);

            return row;
        }
    }


    //Custom adapter for home and daily activities.
    public class DailyAdapter extends ArrayAdapter<String> {

        public DailyAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.activity);
            label.setText(home_daily_strings[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            icon.setImageResource(home_daily_images[position]);

            return row;
        }
    }



    //Custom adapter for sex activities.
    public class SexAdapter extends ArrayAdapter<String> {

        public SexAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.activity);
            label.setText(sex_activity_strings[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            icon.setImageResource(sex_activity_images[position]);

            return row;
        }
    }
}
