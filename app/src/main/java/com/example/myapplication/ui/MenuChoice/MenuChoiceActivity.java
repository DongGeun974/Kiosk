package com.example.myapplication.ui.MenuChoice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.InitActivity;

public class MenuChoiceActivity extends InitActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choice_opt);

        makeBottomBar();

        Button backBtn = (Button) findViewById(R.id.back_btn_menu_choice);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
