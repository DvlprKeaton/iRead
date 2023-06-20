package com.example.readingcomprehension.ReadingComprehension.ReadingTen;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.readingcomprehension.R;

import java.util.ArrayList;
import java.util.Locale;

public class Q1 extends AppCompatActivity {

    protected static final int RESULT_SPEECH = 1;
    private ImageView speak;
    private TextToSpeech mTTS;
    private TextView results;

    int count = 0;
    String correct;
    String wrong;

    int correctAns = 0;
    int wrongAns = 0;

    float pitch = (float) 50 / 50;

    float speed = (float) 35 / 50;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String CORRECT_KEY = "correct_key";

    // key for storing password.
    public static final String WRONG_KEY = "wrong_key";

    private static final int MY_CAMERA_REQUEST_CODE = 100;

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    SharedPreferences sp;

    TextView q1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_q1);
        speak = findViewById(R.id.mic);
        results = findViewById(R.id.result);
        q1 = findViewById(R.id.q1);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        correct = sharedpreferences.getString(CORRECT_KEY, null);

        wrong = sharedpreferences.getString(WRONG_KEY, null);

        sp = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);

        correctAns = 0;
        wrongAns = 0;

        q1.setText("Leo is my friend.\n" +
                "He likes to go to the beach.\n" +
                        "He can surf on his surfboard.\n" +
                        "He is really happy when he surfs.\n" +
                        "Who likes to go to the beach?\n" +
                "\n• Brad" +
                "\n• Leo" +
                "\n• James");

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    results.setText("");
                }catch (ActivityNotFoundException e){
                    Toast.makeText(getApplicationContext(), "Your device doesn't support Speech to Text" , Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    results.setText(text.get(0));
                    if (results.getText().toString().equals("Leo") || results.getText().toString().equals("leo")) {

                        correctAns++;

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("correct_ans", correctAns);
                        editor.commit();

                        Intent intentQ2 = new Intent(this, Q2.class);
                        startActivity(intentQ2);

                    }else{

                        wrongAns++;

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("wrong_ans", wrongAns);
                        editor.commit();

                        Intent intentQ2 = new Intent(this, Q2.class);
                        startActivity(intentQ2);

                    }
                }


                break;
        }
    }
}