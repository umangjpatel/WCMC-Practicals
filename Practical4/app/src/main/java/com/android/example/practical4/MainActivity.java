package com.android.example.practical4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText mCelsiusEditText, mFahrenheitEditText;

    private double mFahrenheitTemp, mCelsiusTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpWidgets();
        initializeValues();
        setListeners();
    }

    private void initializeValues() {
        mCelsiusEditText.setText(String.format(Locale.getDefault(), "%.2f", mCelsiusTemp));
        mFahrenheitEditText.setText(String.format(Locale.getDefault(), "%.2f", mFahrenheitTemp));
    }

    private void setListeners() {
        celsiusListener();
        fahrenheitListener();
    }

    private void fahrenheitListener() {
        mFahrenheitEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mFahrenheitEditText.hasFocus() && !mCelsiusEditText.hasFocus()) {
                    if (s.length() == 0)
                        mCelsiusEditText.setText(String.format(Locale.getDefault(), "%.2f", mCelsiusTemp));
                    else {
                        mFahrenheitTemp = Float.parseFloat(s.toString());
                        mCelsiusTemp = (mFahrenheitTemp - 32 ) / 1.8;
                        mCelsiusEditText.setText(String.format(Locale.getDefault(), "%.2f", mCelsiusTemp));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void celsiusListener() {
        mCelsiusEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mCelsiusEditText.hasFocus() && !mFahrenheitEditText.hasFocus()) {
                    if (s.length() == 0)
                        mFahrenheitEditText.setText(String.format(Locale.getDefault(), "%.2f", mFahrenheitTemp));
                    else {
                        mCelsiusTemp = Float.parseFloat(s.toString());
                        mFahrenheitTemp = mCelsiusTemp * 1.8 + 32;
                        mFahrenheitEditText.setText(String.format(Locale.getDefault(), "%.2f", mFahrenheitTemp));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void wireUpWidgets() {
        mCelsiusEditText = findViewById(R.id.celsius_edit_text);
        mFahrenheitEditText = findViewById(R.id.fahrenheit_edit_text);
    }
}
