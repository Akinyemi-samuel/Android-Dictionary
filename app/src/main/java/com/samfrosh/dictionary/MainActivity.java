package com.samfrosh.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotodictionary(View view) {
        startActivity(new Intent(getApplicationContext(),Dictionary.class));
    }

    public void gotoabout(View view) {
        startActivity(new Intent(getApplicationContext(),About.class));
    }
}