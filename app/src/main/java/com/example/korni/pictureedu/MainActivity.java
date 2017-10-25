package com.example.korni.pictureedu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Button mButton;
    ImageView mBearImage;
    TextView mTextView;
    int mLastClickedButton;
    boolean completed;

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
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (!completed) {
                mBearImage.setAlpha(0.3f);
            }
            switch (v.getId()) {
                case R.id.button:
                    findViewById(R.id.button).setBackgroundColor(Color.parseColor("#e35959"));
                    findViewById(R.id.button).setClickable(false);
                    mTextView.setText("ㄱ");
                    mLastClickedButton = -1;
                    break;
                case R.id.button10:
                    if(!findViewById(R.id.button).isClickable()){
                        findViewById(R.id.button10).setBackgroundColor(Color.parseColor("#e35959"));
                        findViewById(R.id.button10).setClickable(false);
                        mTextView.setText("고");
                        break;
                    }
                case R.id.button5:
                    if(!findViewById(R.id.button).isClickable() && !findViewById(R.id.button10).isClickable()) {
                        findViewById(R.id.button5).setBackgroundColor(Color.parseColor("#e35959"));
                        findViewById(R.id.button5).setClickable(false);
                        mTextView.setText("곰");
                        mBearImage.setImageDrawable(getResources().getDrawable(R.drawable.bear2));
                        mBearImage.setAlpha(1f);
                        mTextView.setAlpha(0.3f);
                        completed = true;
                        break;
                    }
                default:
                    if(v.getId() != mLastClickedButton && mLastClickedButton != -1) {
                        findViewById(mLastClickedButton).setBackground(getResources().getDrawable(R.drawable.button_border));
                    }
                    findViewById(v.getId()).setBackgroundColor(Color.parseColor("#7f7f7f"));
                    mLastClickedButton = v.getId();
                    break;
            }
        }
    };
}
