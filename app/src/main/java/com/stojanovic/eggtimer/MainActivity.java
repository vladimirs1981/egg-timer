package com.stojanovic.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private SeekBar mSeekBar;
    private Button mButton;
    private TextView mTextView;
    private Boolean counterIsActive = false;
    CountDownTimer mCountDownTimer;

    public void controlTimer (View view) {

        if (counterIsActive == false) {
            counterIsActive = true;
            mSeekBar.setEnabled(false);
            mButton.setText("Stop");

        mCountDownTimer = new CountDownTimer(mSeekBar.getProgress() * 1000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                    countDown((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                mTextView.setText("0:00");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
                resetTimer();

            }
        }.start();

    }else {

            resetTimer();
        }


}
    private void countDown(int progress) {
        int minutes = progress / 60;
        int seconds = progress - minutes * 60;

        String secondString = Integer.toString(seconds);
        if (seconds <= 9){
            secondString = "0" + seconds;
        }
        mTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }
    private void resetTimer() {
        mTextView.setText("0:30");
        mSeekBar.setProgress(30);
        mCountDownTimer.cancel();
        mButton.setText("Go!");
        mSeekBar.setEnabled(true);
        counterIsActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = findViewById(R.id.seekBar);
        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);

        mSeekBar.setMax(300);
        mSeekBar.setProgress(30);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                countDown(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
