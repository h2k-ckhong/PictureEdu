package com.example.korni.pictureedu;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech tts;
    ImageView mImage;
    TextView mTextView;
    Button[] buttons = new Button[10];
    int mLastClickedButtonID;
    int index;
    boolean completed;
    boolean[] checkAnswer = {false,false,false};
    ArrayList <Integer> correctAnswer = new ArrayList();
    ArrayList <Integer> currentAnswer = new ArrayList();
//    final char [] correctCharacter = {'ㄱ', 'ㅗ', 'ㅁ'};
    final char [][] correctCharacter = {{'ㄱ', 'ㅗ', 'ㅁ'}, {'ㄷ','ㅏ','ㄹ'}, {'ㅂ','ㅕ','ㄹ'}, {'ㅁ','ㅜ','ㄴ'},{'ㅋ','ㅗ','ㅇ'},{'ㅂ','ㅜ','ㄱ'},{'ㅈ','ㅣ','ㅂ'},{'ㅁ','ㅏ','ㄹ'}};
    final int [][] imageArray = {{R.drawable.bear1,R.drawable.bear2}, {R.drawable.moon, R.drawable.full_moon},
            {R.drawable.star1, R.drawable.star2}, {R.drawable.door, R.drawable.door_open},
            {R.drawable.bean1, R.drawable.bean2}, {R.drawable.drum1, R.drawable.drum2},
            {R.drawable.house1, R.drawable.house2}, {R.drawable.horse1, R.drawable.horse2}};
    final int [][] correctAnswerSet = {{R.id.button,R.id.button10,R.id.button5},{R.id.button3,R.id.button6,R.id.button4},
            {R.id.button2, R.id.button9, R.id.button4},{R.id.button5, R.id.button10, R.id.button2},
            {R.id.button2, R.id.button9, R.id.button5}, {R.id.button3, R.id.button10, R.id.button},
            {R.id.button5, R.id.button10, R.id.button3}, {R.id.button4, R.id.button6, R.id.button3}};

    final char[][] characterSet = {{'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅏ','ㅑ','ㅓ','ㅕ','ㅗ'},{'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅏ','ㅑ','ㅓ','ㅕ','ㅗ'},{'ㄱ','ㅂ','ㅅ','ㄹ','ㅇ','ㅏ','ㅑ','ㅓ','ㅕ','ㅗ'},
            {'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅏ','ㅑ','ㅓ','ㅕ','ㅜ'},{'ㄱ','ㅋ','ㄴ','ㄷ','ㅇ','ㅏ','ㅑ','ㅓ','ㅗ','ㅜ'},
            {'ㄱ','ㄹ','ㅂ','ㅅ','ㅇ','ㅏ','ㅑ','ㅓ','ㅓ','ㅜ'},{'ㄴ','ㄹ','ㅂ','ㅅ','ㅈ','ㅓ','ㅕ','ㅜ','ㅡ','ㅣ'},{'ㄱ','ㄷ','ㄹ','ㅁ','ㅈ','ㅏ','ㅓ','ㅜ','ㅡ','ㅣ'}};

    final char[] ChoSung = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};
    final char[] JungSung	= {'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'};
    final char[] JongSung	= {' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLastClickedButtonID = -1;
        index = 0;
        mImage = (ImageView) findViewById(R.id.imageView);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText("");
        completed = false;

        buttons[0] = (Button) findViewById(R.id.button);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);
        buttons[5] = (Button) findViewById(R.id.button6);
        buttons[6] = (Button) findViewById(R.id.button7);
        buttons[7] = (Button) findViewById(R.id.button8);
        buttons[8] = (Button) findViewById(R.id.button9);
        buttons[9] = (Button) findViewById(R.id.button10);
        findViewById(R.id.imageView).setOnClickListener(imgClickListener);

        correctAnswer.add(correctAnswerSet[index][0]);
        correctAnswer.add(correctAnswerSet[index][1]);
        correctAnswer.add(correctAnswerSet[index][2]);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status){
                if(status != ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }

    public void onButtonClick(View v){
        if (v.getId() == correctAnswer.get(0)) {
            findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
            ((Button) findViewById(v.getId())).setTextColor(Color.parseColor("#f4bcbc"));
            if(!checkAnswer[0]){
                currentAnswer.add(v.getId());
                mTextView.setText(String.valueOf(correctCharacter[index][0]));
                checkAnswer[0] = true;
            }
        } else if (v.getId() == correctAnswer.get(1) && currentAnswer.size() >= 1) {
            findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
            ((Button) findViewById(v.getId())).setTextColor(Color.parseColor("#f4bcbc"));
            mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[index][0], correctCharacter[index][1], ' ')));
            currentAnswer.add(v.getId());
        } else if (v.getId() == correctAnswer.get(2) && currentAnswer.size() >= 2) {
            findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
            ((Button) findViewById(v.getId())).setTextColor(Color.parseColor("#f4bcbc"));
            currentAnswer.add(v.getId());
            mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[index][0], correctCharacter[index][1], correctCharacter[index][2])));
            completed = true;
        } else {
            findViewById(v.getId()).setBackgroundColor(Color.parseColor("#7f7f7f"));
        }

        if (!completed) {
            mImage.setAlpha(0.3f);
        } else {
            mImage.setImageDrawable(getResources().getDrawable(imageArray[index][1]));
            mImage.setAlpha(1f);
            mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[index][0], correctCharacter[index][1], correctCharacter[index][2])));
            mTextView.setAlpha(0.3f);
            tts.setPitch(1.1f);
            tts.setSpeechRate(0.7f);
            tts.speak(mTextView.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
        }

        if(mLastClickedButtonID != - 1 && !currentAnswer.contains(mLastClickedButtonID)){
            findViewById(mLastClickedButtonID).setBackground(getResources().getDrawable(R.drawable.button_border));
        }
        mLastClickedButtonID = v.getId();
    }

    ImageView.OnClickListener imgClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if(completed){
                setExample();
            }
        }
    };

    /*Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == correctAnswer.get(0)) {
                findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
                ((Button) findViewById(v.getId())).setTextColor(Color.parseColor("#f4bcbc"));
                currentAnswer.add(v.getId());
                mTextView.setText(String.valueOf(correctCharacter[index][0]));
            } else if (v.getId() == correctAnswer.get(1) && currentAnswer.size() >= 1) {
                findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
                ((Button) findViewById(v.getId())).setTextColor(Color.parseColor("#f4bcbc"));
                mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[index][0], correctCharacter[index][1], ' ')));
                currentAnswer.add(v.getId());
            } else if (v.getId() == correctAnswer.get(2) && currentAnswer.size() >= 2) {
                findViewById(v.getId()).setBackgroundColor(Color.parseColor("#e35959"));
                ((Button) findViewById(v.getId())).setTextColor(Color.parseColor("#f4bcbc"));
                currentAnswer.add(v.getId());
                mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[index][0], correctCharacter[index][1], correctCharacter[index][2])));
                completed = true;
            } else {
                findViewById(v.getId()).setBackgroundColor(Color.parseColor("#7f7f7f"));
            }

            if (!completed) {
                mImage.setAlpha(0.3f);
            } else {
                mImage.setImageDrawable(getResources().getDrawable(R.drawable.bear2));
                mImage.setAlpha(1f);
                mTextView.setText(String.valueOf(CharacterCombination(correctCharacter[index][0], correctCharacter[index][1], correctCharacter[index][2])));
                mTextView.setAlpha(0.3f);
            }

            if(mLastClickedButtonID != - 1 && !currentAnswer.contains(mLastClickedButtonID)){
                findViewById(mLastClickedButtonID).setBackground(getResources().getDrawable(R.drawable.button_border));
            }
            mLastClickedButtonID = v.getId();
        }
    };*/

    public char CharacterCombination(char ch1, char ch2, char ch3) {
        char ret_val;

        int a = Arrays.binarySearch(ChoSung, ch1);
        int b = Arrays.binarySearch(JungSung, ch2);
        int c = Arrays.binarySearch(JongSung, ch3);

        ret_val = (char)(0xAC00 + ((a*21)+b)*28+c);
        return ret_val;
    }

    public void setExample(){
        if(index < 7){
            index++;
        } else {
            index = 0;
        }
        correctAnswer.clear();
        currentAnswer.clear();
        mTextView.setText("");
        completed = false;
        checkAnswer[0] = false;
        mImage.setImageDrawable(getResources().getDrawable(imageArray[index][0]));

        for(int i = 0; i < 10; i++){
            buttons[i].setTextColor(Color.parseColor("#d2d2d2"));
            buttons[i].setBackground(getResources().getDrawable(R.drawable.button_border));
            buttons[i].setText(characterSet[index][i]+"");
        }

        correctAnswer.add(correctAnswerSet[index][0]);
        correctAnswer.add(correctAnswerSet[index][1]);
        correctAnswer.add(correctAnswerSet[index][2]);
    }
}
