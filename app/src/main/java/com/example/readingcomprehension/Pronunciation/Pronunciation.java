package com.example.readingcomprehension.Pronunciation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.readingcomprehension.Pronunciation.PronunciationTen.PQ1;
import com.example.readingcomprehension.R;
import com.example.readingcomprehension.ReadingComprehension.ReadingComprehension;
import com.example.readingcomprehension.ReadingComprehension.ReadingTen.Q1;

public class Pronunciation extends AppCompatActivity {

    Button ten, twen, thir;
    int count = 0;

    public static final String SHARED_PREFS = "shared_prefs_pronunciation";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronunciation);

        ten = findViewById(R.id.ten_qst);
        twen = findViewById(R.id.twen_qst);
        thir = findViewById(R.id.thir_qst);

        sp = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count = 10;

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("number_question", count);
                editor.commit();

                Intent intentMove = new Intent(Pronunciation.this, PQ1.class);
                startActivity(intentMove);

            }
        });
        twen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count = 20;

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("number_question", count);
                editor.commit();

                Intent intentMove = new Intent(Pronunciation.this, PQ1.class);
                startActivity(intentMove);
            }
        });
    }
}