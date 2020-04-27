package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.ui.InitActivity;


public class MainActivity extends InitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button exit = (Button) findViewById(R.id.button_exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}