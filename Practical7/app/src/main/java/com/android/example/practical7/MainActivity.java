package com.android.example.practical7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppCompatTextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpWidgets();
    }

    private void wireUpWidgets() {
        mTitleTextView = findViewById(R.id.title_text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red_color:
                mTitleTextView.setBackgroundColor(getResources().getColor(R.color.colorRed));
                mTitleTextView.setText("Red");
                break;
            case R.id.green_color:
                mTitleTextView.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                mTitleTextView.setText("Green");
                break;
            case R.id.blue_color:
                mTitleTextView.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                mTitleTextView.setText("Blue");
                break;
            default:
                mTitleTextView.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                mTitleTextView.setText("Hello World");
        }
        return super.onOptionsItemSelected(item);
    }
}
