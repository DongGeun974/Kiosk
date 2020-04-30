package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.ui.BuyActivity.BuyActivity;
import com.example.myapplication.ui.InitActivity;

public class MainActivity extends InitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeBottomBar();

        Button exit = (Button) findViewById(R.id.button_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView toHere = (ImageView) findViewById(R.id.tohere);
        ImageView toGo = (ImageView) findViewById(R.id.togo);
        toHere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setGoOrHere(true);

                Intent intent=new Intent(MainActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });
        toGo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setGoOrHere(false);

                Intent intent=new Intent(MainActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });
    }
}