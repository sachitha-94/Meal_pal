package com.mp.ucsc.meal_pal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        


    }

    private void layoutdesign(){
       selectIngredients_spinner=(Spinner)findViewById(R.id.select_ingre_spi);


    }

    private Spinner selectIngredients_spinner;

}
