package com.vamnoize.tts;

import android.service.wallpaper.WallpaperService;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(MainActivity.this,MainActivity.this);
        Button btnSpeak = (Button) findViewById(R.id.button);
        final TextView tvText = (TextView) findViewById(R.id.textView);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tts.isSpeaking()){
                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"Sample");
                    tts.speak(tvText.getText().toString(),TextToSpeech.QUEUE_ADD,params);

                }
                else {
                    tts.stop();
                }
            }
        });

    }

    @Override
    public void onInit(int status) {
        tts.setOnUtteranceCompletedListener(this);
    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {
     runOnUiThread(new Runnable() {
         @Override
         public void run() {
             Toast.makeText(MainActivity.this,"Utterance complete",Toast.LENGTH_SHORT).show();
             Button btn = (Button) findViewById(R.id.button);
             btn.setVisibility(View.VISIBLE);
         }
     });
    }
    @Override
    protected void onDestroy(){
        if (tts!=null){
            tts.stop();
            tts.shutdown();
            tts = null;
         super.onDestroy();
        }

    }
}
