package com.example.hellosharedpref;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private int mColor;
    private TextView mShowCountTextView;
    private SharedPreferences mPreferences;
    private String prefFile = "com.example.hellosharedpref";


    private final String COUNT_KEY = "count";
    private final String COLOR_KEY = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mShowCountTextView = findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this, android.R.color.darker_gray);
        mPreferences = getSharedPreferences(prefFile, MODE_PRIVATE);

        mCount = mPreferences.getInt(COUNT_KEY, 0);
        mShowCountTextView.setText(String.format("%s", mCount));
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowCountTextView.setBackgroundColor(mColor);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editorPreferences = mPreferences.edit();
        editorPreferences.putInt(COUNT_KEY, mCount);
        editorPreferences.putInt(COLOR_KEY, mColor);
        editorPreferences.apply();
    }

    public void countUp(View view) {
        mCount++;
        mShowCountTextView.setText(String.format("%s", mCount));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNT_KEY, mCount);
        outState.putInt(COLOR_KEY, mColor);
    }

    public void reset(View view) {
        mCount = 0;
        mShowCountTextView.setText(String.format("%s", mCount));

        mColor = ContextCompat.getColor(this, android.R.color.darker_gray);
        mShowCountTextView.setBackgroundColor(mColor);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void changeBackground(View view) {
        int color = ((ColorDrawable)view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
    }
}