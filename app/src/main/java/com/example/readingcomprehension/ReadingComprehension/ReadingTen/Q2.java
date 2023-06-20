package com.example.readingcomprehension.ReadingComprehension.ReadingTen;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Q2 extends AppCompatActivity {

    protected static final int RESULT_SPEECH = 1;
    private ImageView speak;
    private TextToSpeech mTTS;
    private TextView results;

    int count;
    String correct;
    String wrong;

    int countCorrect;
    int correctAns;
    int wrongAns;

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
        setContentView(R.layout.activity_q2);
        speak = findViewById(R.id.mic);
        results = findViewById(R.id.result);
        q1 = findViewById(R.id.q1);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        correct = sharedpreferences.getString(CORRECT_KEY, null);

        wrong = sharedpreferences.getString(WRONG_KEY, null);

        sp = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);

        correctAns = sp.getInt("correct_ans", -1);
        wrongAns = sp.getInt("wrong_ans", -1);

        q1.setText("Leo likes to surf when he goes to the beach.\n" +
                "Which of the following activities can he also do at the beach?\n" +
                        "\n• sleep in his bed" +
                "\n• go for a swim" +
                "\n• go for a dive with his parents");

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
                    if (results.getText().toString().equals("go for a swim")) {

                        correctAns++;
                        //String cor = String.valueOf(correctAns);

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("correct_ans", correctAns);
                        editor.commit();

                        Intent intentQ3 = new Intent(this, Q3.class);
                        startActivity(intentQ3);

                        Toast.makeText(Q2.this, correctAns+"", Toast.LENGTH_SHORT).show();

                    }else {

                        wrongAns++;

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("wrong_ans", wrongAns);
                        editor.commit();

                        Intent intentQ2 = new Intent(this, Q3.class);
                        startActivity(intentQ2);

                    }
                }


                break;
        }
    }
}