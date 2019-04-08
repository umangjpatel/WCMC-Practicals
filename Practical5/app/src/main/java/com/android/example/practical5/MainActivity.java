package com.android.example.practical5;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText mUsernameEditText, mPasswordEditText;
    private MaterialButton mLoginButton, mCancelButton;

    private int mLoginAttempts = 2;

    private static final String USER_NAME = "abcd", PASS_WORD = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpWidgets();
        setListeners();
    }

    private void setListeners() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mUsernameEditText.getText().toString();
                String passWord = mPasswordEditText.getText().toString();
                if (userName.length() == 0)
                    mUsernameEditText.setError("Empty field");
                else if (passWord.length() == 0)
                    mPasswordEditText.setError("Empty field");
                else {
                    if (userName.equals(USER_NAME)) {
                        if (passWord.equals(PASS_WORD)) {
                            startActivity(HomeActivity.getIntent(MainActivity.this));
                            finish();
                        } else {
                            if (mLoginAttempts > 0) {
                                mLoginAttempts--;
                                mPasswordEditText.setError("Incorrect password");
                            } else
                                mLoginButton.setEnabled(false);

                        }
                    } else {
                        if (mLoginAttempts > 0) {
                            mLoginAttempts--;
                            mUsernameEditText.setError("Incorrect username");
                        } else
                            mLoginButton.setEnabled(false);
                    }
                }


            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void wireUpWidgets() {
        mUsernameEditText = findViewById(R.id.username_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mLoginButton = findViewById(R.id.login_button);
        mCancelButton = findViewById(R.id.cancel_button);
    }
}
