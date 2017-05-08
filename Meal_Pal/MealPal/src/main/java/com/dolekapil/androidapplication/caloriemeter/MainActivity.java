package com.dolekapil.androidapplication.caloriemeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {
    private Tracker mTracker;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
           mTracker = application.getDefaultTracker();

        // Drop down list for weight(pounds or kilograms)
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_weight, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        //Drop down list for Height feet
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.height_feet, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);

        //Drop down list for height inches.
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.height_inches, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        //Drop down list for Activity
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.spinner_activity, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        //On click of button
        Button button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEditText = (EditText) findViewById(R.id.editText);
                EditText ageEditText = (EditText) findViewById(R.id.editText2);
                EditText weightEditText = (EditText) findViewById(R.id.editText3);
                Spinner feetSpinner=(Spinner) findViewById(R.id.spinner);
                Spinner inchesSpinner=(Spinner) findViewById(R.id.spinner2);
                Spinner activitySpinner=(Spinner) findViewById(R.id.spinner3);
                Spinner weightSpinner = (Spinner) findViewById(R.id.spinner1);
                TextView feeterrorText = (TextView)feetSpinner.getSelectedView();
                TextView incheserrorText = (TextView)inchesSpinner.getSelectedView();
                TextView activityerrorText = (TextView)activitySpinner.getSelectedView();
                RadioGroup gender_group = (RadioGroup) findViewById(R.id.radioGroup);
                RadioButton male = (RadioButton) findViewById(R.id.radioButton);
                RadioButton female = (RadioButton) findViewById(R.id.radioButton2);

                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String weight = weightEditText.getText().toString();
                String weight_measure = spinner1.getSelectedItem().toString();
                String feet = feetSpinner.getSelectedItem().toString();
                String inches = inchesSpinner.getSelectedItem().toString();
                String activity = activitySpinner.getSelectedItem().toString();
                int selected = gender_group.getCheckedRadioButtonId();

                //Name validation
                if (name.matches("")) {
                    nameEditText.requestFocus();
                    nameEditText.setError("Name Required.");
                    return;
                }
                else{
                    nameEditText.setError(null);
                }

                //Age validation.
                if(age.matches("")){
                    ageEditText.requestFocus();
                    ageEditText.setError("Age Required.");
                    return;
                }
                else{
                    ageEditText.setError(null);
                }

                //Age max validation.
                if(Integer.parseInt(age)>150){
                    ageEditText.requestFocus();
                    ageEditText.setError("Please Enter Valid Age.");
                    return;
                }
                else{
                    ageEditText.setError(null);
                }

                //Weight validation.
                if(weight.matches("")){
                    weightEditText.requestFocus();
                    weightEditText.setError("Weight Required.");
                    return;
                }
                else{
                    weightEditText.setError(null);
                }

                // weight max validation.
                if(Integer.parseInt(weight)>1500){
                    weightEditText.requestFocus();
                    weightEditText.setError("Please Enter valid Weight.");
                    return;
                }
                else{
                    weightEditText.setError(null);
                }

                //Height(Feet) validation.
                if(feet.matches("Feets")){
                    feeterrorText.setError("");
                    return;
                }
                else{
                    feeterrorText.setError(null);
                }

                //Weight(Inches) validation.
                if(inches.matches("Inches")){
                    incheserrorText.setError("");
                    return;
                }
                else{
                    incheserrorText.setError(null);
                }

                //Activity validation
                if(activity.matches("Select Activity")){
                    activityerrorText.setError("");
                    return;
                }
                else {
                    activityerrorText.setError(null);
                }

                //Open new Activity (Screen)
                Intent intent = new Intent("com.example.dolek.fitnessguru.FirstActivity");
                intent.putExtra("NAME",name);
                intent.putExtra("AGE",age);
                intent.putExtra("WEIGHT",weight);
                intent.putExtra("WEIGHT_MEASURE",weight_measure);
                intent.putExtra("FEET",feet);
                intent.putExtra("INCHES",inches);
                intent.putExtra("ACTIVITY",activity);
                if(selected==male.getId()){
                    intent.putExtra("GENDER","Male");
                }
                else{
                    intent.putExtra("GENDER","Female");
                }
                startActivity(intent);

                //Set all field values to default.
                nameEditText.setText("");
                ageEditText.setText("");
                weightEditText.setText("");
                feetSpinner.setSelection(0);
                inchesSpinner.setSelection(0);
                weightSpinner.setSelection(0);
                activitySpinner.setSelection(0);
                gender_group.check(R.id.radioButton);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
