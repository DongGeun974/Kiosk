package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.ui.MenuMain.MenuActivity;
import com.example.myapplication.ui.InitActivity;

public class MainActivity extends InitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayBottomBar();

        Button exit = (Button) findViewById(R.id.btn_act_main_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button start = (Button) findViewById(R.id.btn_act_main_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

//        ImageView toHere = (ImageView) findViewById(R.id.tohere);
//        ImageView toGo = (ImageView) findViewById(R.id.togo);
//        toHere.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                setGoOrHere(true);
//
//                Intent intent=new Intent(MainActivity.this, MenuActivity.class);
//                startActivity(intent);
//            }
//        });
//        toGo.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                setGoOrHere(false);
//
//                Intent intent=new Intent(MainActivity.this, MenuActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}