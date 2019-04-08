package com.android.example.practical3;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText mSecondsEditText;
    private MaterialButton mShowToastButton;

    private int mTimeSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpWidgets();
        setListeners();
    }

    private void setListeners() {
        mShowToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeSecondsString = mSecondsEditText.getText().toString().trim();
                if (timeSecondsString.length() == 0)
                    mSecondsEditText.setError("Empty field");
                else {
                    mTimeSeconds = Integer.parseInt(timeSecondsString);
                    mTimeSeconds *= 1000;
                    showToast();
                }
            }
        });
    }

    private void showToast() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "This is a toast message", Toast.LENGTH_LONG).show();
                showToast();
            }
        }, mTimeSeconds);
    }

    private void wireUpWidgets() {
        mSecondsEditText = findViewById(R.id.time_edit_text);
        mShowToastButton = findViewById(R.id.show_toast_button);
    }
}
