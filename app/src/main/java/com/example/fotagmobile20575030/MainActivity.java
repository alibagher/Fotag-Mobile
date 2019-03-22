package com.example.fotagmobile20575030;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_main);
    }
}
