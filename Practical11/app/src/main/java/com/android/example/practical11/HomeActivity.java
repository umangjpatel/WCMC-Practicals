package com.android.example.practical11;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class HomeActivity extends AppCompatActivity {

    private static final String EXTRA_DISPLAY_NAME = "user_display_name";

    public static Intent newIntent(Context packageContext, FirebaseUser user) {
        Intent intent = new Intent(packageContext, HomeActivity.class);
        String userDisplayName = user.getDisplayName();
        intent.putExtra(EXTRA_DISPLAY_NAME, userDisplayName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppCompatTextView displayNameTextView = findViewById(R.id.display_name_text_view);
        displayNameTextView.setText("Welcome, " + getIntent().getStringExtra(EXTRA_DISPLAY_NAME));
    }
}
