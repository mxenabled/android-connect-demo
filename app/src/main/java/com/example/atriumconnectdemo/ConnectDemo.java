package com.example.atriumconnectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ConnectDemo extends AppCompatActivity {
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_demo);

        // set up the webview client for url overrides and settings.
        MXWebViewClient mxWebViewClient = new MXWebViewClient(this);
        WebView webView = (WebView) findViewById(R.id.connectWebView);
        WebSettings settings = webView.getSettings();

        webView.setWebViewClient(mxWebViewClient);
        settings.setJavaScriptEnabled(true);


        /*
          In a 'real' application, you would want to get your one time use URL from atrium, then
          load it into the webview. For demo purposes, we simply hardcode it here.

          See the documentation for more details.
          https://atrium.mx.com/docs#get-a-url
         */
        webView.loadUrl("Connect url goes here.");
    }
}