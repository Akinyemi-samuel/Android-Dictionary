package com.samfrosh.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity {
TextView samuel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        samuel=findViewById(R.id.samuellink);

        samuel.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void backbtn(View view) {
        onBackPressed();
    }
}