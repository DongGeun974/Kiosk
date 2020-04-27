package com.example.myapplication;

import android.os.Bundle;

import com.example.myapplication.ui.InitActivity;


public class MainActivity extends InitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}