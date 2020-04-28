package com.example.myapplication.ui.BuyActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.InitActivity;

public class BuyActivity extends InitActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_main);

        makeBottomBar();

        Button back = (Button) findViewById(R.id.back_btn_buy);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
