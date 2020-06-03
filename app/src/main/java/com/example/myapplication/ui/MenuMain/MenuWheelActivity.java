package com.example.myapplication.ui.MenuMain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.MenuCategoryBar.MenuCategoryBarWheelFragment;
import com.example.myapplication.ui.bottomBar.InitBottomBar;

/**
 * 휠체어 전용 인터페이스, {@link MenuActivity}에서 상속
 */
public class MenuWheelActivity extends MenuActivity {
    //휠체어 >> 일반 시 휠체어 액티비티 종료용
    public static MenuWheelActivity menuWheelActivity;
    //일반 >> 휠체어 시 일반 액티비티 종료용
    MenuActivity menuActivity = (MenuActivity)MenuActivity.menuActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_wheel);

        displayMenuList();

        setEvent();
    }

    @Override
    public void setEvent(){
        super.setEvent();

        Button back = (Button) findViewById(R.id.btn_actMenu_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //이전 액티비티 종료
                    menuActivity.finish();
                }catch (Exception ex){}
                finish();
            }
        });
    }

    @Override
    public void displayCategoryBar(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuCategoryBarWheelFragment buyMenu = new MenuCategoryBarWheelFragment();
        fragmentTransaction.add(R.id.lay_actMain_menuBar, buyMenu);

        fragmentTransaction.commit();
    }

    @Override
    public void checkFunctionState() {
        int functionState =  getFunctionState();

        Log.d("메뉴화면(휠체어)에서", String.valueOf(functionState));

        if((functionState & InitBottomBar.WHEEL) != 0) {
            Log.d("메뉴화면(휠체어)에서", "휠체어로 변환");
        }else{
            Log.d("메뉴화면(휠체어)에서", "휠체어에서 일반으로 변환");

            try {
                //이전 액티비티 종료
                menuActivity.finish();
            }catch (Exception ex){}

            //전환된 액티비티에서 이전 액티비티 종료용
            menuWheelActivity = this;

            Intent intent=new Intent(MenuWheelActivity.this, MenuActivity.class);

            // 출처: https://wingsnote.com/128 [날개의 노트 (Wing's Note)]
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);

            startActivity(intent);
        }

        if((functionState & InitBottomBar.BIGGER) != 0){
            Log.d("메뉴화면(휠체어)에서", "돋보기 기능");
        }else{
            Log.d("메뉴화면(휠체어)에서", "돋보기 해제");
        }

        if((functionState & InitBottomBar.COLORBLIND) != 0){
            Log.d("메뉴화면(휠체어)에서", "색맹으로 전환");
        }else{
            Log.d("메뉴화면(휠체어)에서", "색맹 해제");
        }
    }

}