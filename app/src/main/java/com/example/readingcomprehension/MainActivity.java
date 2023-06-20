package com.example.readingcomprehension;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.readingcomprehension.Pronunciation.Pronunciation;
import com.example.readingcomprehension.ReadingComprehension.ReadingComprehension;

public class MainActivity extends AppCompatActivity {

    Button rdc, prn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdc = findViewById(R.id.rdc_btn);
        prn = findViewById(R.id.prn_btn);

        rdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReadingComprehension = new Intent(MainActivity.this, ReadingComprehension.class);
                startActivity(intentReadingComprehension);
            }
        });

        prn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPronunciation = new Intent(MainActivity.this, Pronunciation.class);
                startActivity(intentPronunciation);
            }
        });
    }
}