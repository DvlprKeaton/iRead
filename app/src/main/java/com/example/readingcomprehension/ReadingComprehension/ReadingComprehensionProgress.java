package com.example.readingcomprehension.ReadingComprehension;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readingcomprehension.MainActivity;
import com.example.readingcomprehension.R;
import com.example.readingcomprehension.ReadingComprehension.ReadingTen.Q1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReadingComprehensionProgress extends AppCompatActivity {

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

    Date currentDate;
    String formattedDate, formattedTime;

    double percentage;

    Button retake,backm;

    TextView q1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_comprehension_progress);

        speak = findViewById(R.id.mic);
        results = findViewById(R.id.result);
        retake = findViewById(R.id.retake);
        backm = findViewById(R.id.back_main);
        q1 = findViewById(R.id.q1);

        currentDate = Calendar.getInstance().getTime();
        formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        formattedTime = time.format(currentDate);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        correct = sharedpreferences.getString(CORRECT_KEY, null);

        wrong = sharedpreferences.getString(WRONG_KEY, null);

        sp = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);

        correctAns = sp.getInt("correct_ans", -1);
        wrongAns = sp.getInt("wrong_ans", -1);
        numberQ = sp.getInt("number_question", -1);

        double dnum = numberQ;

        percentage = (correctAns / dnum) * 100;

        String res = "Your mastery level is "+percentage+"%.\n"+formattedDate+".\nTime taken "+formattedTime+".\nNumber of correct answers "+correctAns+".\nOverall total scores "+correctAns+"/"+numberQ;

        q1.setText(res);

        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadingComprehensionProgress.this, Q1.class);
                startActivity(intent);

            }
        });

        backm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadingComprehensionProgress.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}