package com.example.atriumconnectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openConnect(View view) {
        // Open the connect widget activity
        Intent intent = new Intent(this, ConnectDemo.class);
        startActivity(intent);
    }
}