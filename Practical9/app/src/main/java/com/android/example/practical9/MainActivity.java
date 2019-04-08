package com.android.example.practical9;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView mWebsiteWebView;
    private TextInputEditText mWebAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpWidgets();
        setListeners();
    }

    private void setListeners() {
        mWebAddressEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String webAddress = Objects.requireNonNull(mWebAddressEditText.getText()).toString().trim();
                    if (webAddress.length() != 0) {
                        mWebsiteWebView.loadUrl("https://" + webAddress);
                        mWebsiteWebView.getSettings().setJavaScriptEnabled(true);
                        mWebsiteWebView.setWebViewClient(new DemoWebViewClient());
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void wireUpWidgets() {
        mWebsiteWebView = findViewById(R.id.website_web_view);
        mWebAddressEditText = findViewById(R.id.web_address_edit_text);
    }

}

class DemoWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}