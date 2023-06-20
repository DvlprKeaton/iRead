package com.example.readingcomprehension.ReadingComprehension.ReadingTwenty;

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

public class Q11 extends AppCompatActivity {

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

    int numberQ;

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
        setContentView(R.layout.activity_q11);
        speak = findViewById(R.id.mic);
        results = findViewById(R.id.result);
        q1 = findViewById(R.id.q1);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        correct = sharedpreferences.getString(CORRECT_KEY, null);

        wrong = sharedpreferences.getString(WRONG_KEY, null);

        sp = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);

        correctAns = sp.getInt("correct_ans", -1);
        wrongAns = sp.getInt("wrong_ans", -1);
        numberQ = sp.getInt("number_question", -1);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

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

        q1.setText("I am billy, He is my younger brother Thomas.\n" +
                "He is 5 years old. He loves Playing football.\n" +
                        "I help him with his studies\n" +
                        "He is very cute and I love him...\n" +
                        "What is the name of Billy's little brother?\n" +
                "• John" +
                "\n• George" +
                "\n• Thomas");

    }
    public void Q1N2(){

        String Q1N1 = "I am billy, He is my younger brother Thomas." +
                "He is 5 years old. He loves Playing football." +
                "I help him with his studies" +
                "He is very cute and I love him..." +
                "What is the name of Billy's little brother?" +
                "is it John, George, or Thomas";

        float pitch1 = (float) 50 / 50;
        if (pitch1 < 0.1) pitch1 = 0.1f;
        float speed1 = (float) 35 / 50;
        if (speed1 < 0.1) speed1 = 0.1f;
        mTTS.setPitch(pitch1);
        mTTS.setSpeechRate(speed1);

        mTTS.speak(Q1N1, TextToSpeech.QUEUE_FLUSH, null);

        Toast.makeText(Q11.this, Q1N1, Toast.LENGTH_SHORT).show();
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    results.setText(text.get(0));
                    if (results.getText().toString().equals("Thomas")) {

                        correctAns++;

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("correct_ans", correctAns);
                        editor.commit();

                            Intent intentQ3 = new Intent(this, Q12.class);
                            startActivity(intentQ3);

                        Toast.makeText(Q11.this, correctAns+"", Toast.LENGTH_SHORT).show();

                    }else {

                        wrongAns++;

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("wrong_ans", wrongAns);
                        editor.commit();

                            Intent intentQ3 = new Intent(this, Q12.class);
                            startActivity(intentQ3);


                    }
                }

                break;
        }
    }
}