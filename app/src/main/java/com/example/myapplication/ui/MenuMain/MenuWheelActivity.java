package com.example.myapplication.ui.MenuMain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.ui.MenuBuy.MenuBuyFragment;

public class MenuWheelActivity extends MenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_wheel);

        displayMenuList();

        ImageButton btnBefore = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuBefore);
        btnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    setMenuPage(getMenuPage()-1);
                    displayMenuList();
                }catch(Exception e){
                    setMenuPage(getMenuPage()+1);
                    displayMenuList();
                }

                Log.d("menuPage:", String.valueOf(getMenuPage()));
            }
        });

        ImageButton btnNext = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMenuPage(getMenuPage()+1);

                if(getMenuPage() > getCategoryMenuList().getMenuList().size() / 6)
                    setMenuPage(getMenuPage()-1);

                displayMenuList();

                Log.d("menuPage:", String.valueOf(getMenuPage()));

            }
        });

        Button btnBuy = (Button) findViewById(R.id.btn_actMenu_Buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                MenuBuyFragment menuBuyFragment = new MenuBuyFragment();
                fragmentTransaction.add(R.id.lay_actMenu_buy, menuBuyFragment);

                fragmentTransaction.commit();
            }
        });
    }

    //변수 하나(역할: 입력되면 gilde 이미지 가져와서 출력)
}