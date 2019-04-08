package com.android.example.practical2;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText mFirstNumberEditText, mSecondNumberEditText;
    private MaterialButton mCalculateButton;

    private boolean mIsCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpWidgets();
        setListeners();
    }

    private void setListeners() {
        mCalculateButton.setOnClickListener(v -> {
            String first = mFirstNumberEditText.getText().toString().trim();
            String second = mSecondNumberEditText.getText().toString().trim();
            if (first.length() == 0) {
                mFirstNumberEditText.setError("Empty field");
                mIsCorrect = false;
            }
            else if (second.length() == 0) {
                mSecondNumberEditText.setError("Empty field");
                mIsCorrect = false;
            }
            else
                mIsCorrect = true;


            if (mIsCorrect) {
                int firstNumber = Integer.parseInt(Objects.requireNonNull(first.trim()));
                int secondNumber = Integer.parseInt(Objects.requireNonNull(second.trim()));
                int sum = firstNumber + secondNumber;
                Toast.makeText(MainActivity.this, "Sum is " + Integer.toString(sum), Toast.LENGTH_LONG).show();
            }

        });
    }

    private void wireUpWidgets() {
        mFirstNumberEditText = findViewById(R.id.first_number_edit_text);
        mSecondNumberEditText = findViewById(R.id.second_number_edit_text);
        mCalculateButton = findViewById(R.id.calculate_button);
    }
}
