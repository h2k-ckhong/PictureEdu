package com.example.korni.pictureedu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView mBearImage;
    TextView mTextView;
    int mLastClickedButton;
    boolean completed;
    ArrayList <Integer> correctAnswer = new ArrayList();
    ArrayList <Integer> currentAnswer = new ArrayList();
    char [] correctCharacter = {'ㄱ', 'ㅗ', 'ㅁ'};

    final char[] ChoSung = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};
    final char[] JungSung	= {'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'};
    final char[] JongSung	= {' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLastClickedButton = -1;
        mBearImage = (ImageView) findViewById(R.id.imageView);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText("");
        completed = false;

        findViewById(R.id.button).setOnClickListener(mClickListener);
        findViewById(R.id.button2).setOnClickListener(mClickListener);
        findViewById(R.id.button3).setOnClickListener(mClickListener);
        findViewById(R.id.button4).setOnClickListener(mClickListener);
        findViewById(R.id.button5).setOnClickListener(mClickListener);
        findViewById(R.id.button6).setOnClickListener(mClickListener);
        findViewById(R.id.button7).setOnClickListener(mClickListener);
        findViewById(R.id.button8).setOnClickListener(mClickListener);
        findViewById(R.id.button9).setOnClickListener(mClickListener);
        findViewById(R.id.button10).setOnClickListener(mClickListener);

        correctAnswer.add(R.id.button);
        correctAnswer.add(R.id.button10);
        correctAnswer.add(R.id.button5);
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == correctAnswer.get(0)) {
                findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
                currentAnswer.add(v.getId());
                mTextView.setText(String.valueOf(correctCharacter[0]));
            } else if (v.getId() == correctAnswer.get(1) && currentAnswer.size() >= 1) {
                findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
                mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[0], correctCharacter[1], ' ')));
                currentAnswer.add(v.getId());
            } else if (v.getId() == correctAnswer.get(2) && currentAnswer.size() >= 2) {
                findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
                currentAnswer.add(v.getId());
                mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[0], correctCharacter[1], correctCharacter[2])));
                completed = true;
            } else {
                findViewById(v.getId()).setBackgroundColor(Color.parseColor("#7f7f7f"));
            }

            if (!completed) {
                mBearImage.setAlpha(0.3f);
            } else {
                mBearImage.setImageDrawable(getResources().getDrawable(R.drawable.bear2));
                mBearImage.setAlpha(1f);
                mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[0], correctCharacter[1], correctCharacter[2])));
                mTextView.setAlpha(0.3f);
            }

            if(mLastClickedButton != - 1 && !currentAnswer.contains(mLastClickedButton)){
                findViewById(mLastClickedButton).setBackground(getResources().getDrawable(R.drawable.button_border));
            }
            mLastClickedButton = v.getId();
        }
    };

    public char CharacterCombination(char ch1, char ch2, char ch3) {
        char ret_val;

        int a = Arrays.binarySearch(ChoSung, ch1);
        int b = Arrays.binarySearch(JungSung, ch2);
        int c = Arrays.binarySearch(JongSung, ch3);

        ret_val = (char)(0xAC00 + ((a*21)+b)*28+c);
        return ret_val;
    }
}
