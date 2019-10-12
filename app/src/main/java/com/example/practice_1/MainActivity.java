package com.example.practice_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    EditText editText;
    SeekBar seekBarpitch;
    SeekBar seekBarspeed;
    Button button;
    Button translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.Sayit);
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("tts", "Language not suppported");
                    } else {
                        button.setEnabled(true);

                    }
                }

            }
        });
        editText = findViewById(R.id.edit_text);
        seekBarpitch = findViewById(R.id.seek_pitch);
        seekBarspeed = findViewById(R.id.seek_speed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }

    public void speak() {
        String text = editText.getText().toString();
        float pitch = (float) seekBarpitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) seekBarspeed.getProgress() / 50;
        if (pitch < 0.1) speed = 0.1f;
        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);


    }

    @Override
    protected void onDestroy() {
        if(textToSpeech!=null){
            textToSpeech.stop();
        textToSpeech.shutdown();}
        super.onDestroy();
    }
}