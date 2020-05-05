package com.example.myapplication.ui.MenuMain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.myapplication.R;
import com.example.myapplication.ui.MenuChoice.MenuChoiceActivity;
import com.example.myapplication.ui.MenuMain.MenuBar.MenuBarFragment;
import com.example.myapplication.ui.InitActivity;

public class MenuActivity extends InitActivity {
    private String[] menuList = {"chicken", "burger", "drink", "side"};
    private int menuState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        MenuBarFragment menu = new MenuBarFragment();

        makeBottomBar();

        Button back = (Button) findViewById(R.id.back_btn_buy);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView target = (ImageView) findViewById(R.id.target);
        target.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MenuActivity.this , MenuChoiceActivity.class);
                startActivity(intent);
            }
        });

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuBarFragment buyMenu = new MenuBarFragment();
        fragmentTransaction.add(R.id.menu_bar, buyMenu);

        fragmentTransaction.commit();


    }
}
