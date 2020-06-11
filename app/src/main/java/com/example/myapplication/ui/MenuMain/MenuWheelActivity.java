package com.example.myapplication.ui.MenuMain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Magnifier;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;
import com.example.myapplication.ui.MenuCategoryBar.MenuCategoryBarWheelFragment;
import com.example.myapplication.ui.bottomBar.InitBottomBar;

/**
 * 휠체어 전용 인터페이스, {@link MenuActivity}에서 상속
 */
public class MenuWheelActivity extends MenuActivity {

    //돋보기 객체
    private Magnifier magnifier;
    //돋보기 레이아웃 접근
    private ConstraintLayout constraintLayout1;
    //돋보기 리스
    private View.OnTouchListener magnifierTouchListener = new View.OnTouchListener() {
        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE: {
                    final int[] viewPosition = new int[2];
                    v.getLocationOnScreen(viewPosition);
                    magnifier.show(event.getRawX() - viewPosition[0],
                            event.getRawY() - viewPosition[1]);
                    break;
                }
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP: {
                    magnifier.dismiss();
                }
            }
            return true;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_wheel);

        //////////////////////////////////////////////////
        //타이머 시작
//        timer.schedule(timerTask, 1000, 1000);
//
//        //시작 음성 안내 시작
//        AlarmStartSound();
        ///////////////////////////////////////////////////

        //돋보기
        constraintLayout1 = findViewById(R.id.lay_actMenu_all);
        Magnifier.Builder builder = new Magnifier.Builder(constraintLayout1);
        builder.setSize(600, 400);
        builder.setInitialZoom(3f);
        magnifier = builder.build();
        //돋보기


        displayCategoryBar();
        displayBottomBar();
        setEvent();
        changeCartState();

        displayMenuList();
    }

    @Override
    public void displayCategoryBar(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuCategoryBarWheelFragment buyMenu = new MenuCategoryBarWheelFragment();
        fragmentTransaction.add(R.id.lay_actMain_menuBar, buyMenu);

        fragmentTransaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void checkFunctionState() {
        int functionState =  getFunctionState();

        Log.d("메뉴화면(휠체어)에서", String.valueOf(functionState));

        if((functionState & InitBottomBar.WHEEL) != 0) {
            Log.d("메뉴화면(휠체어)에서", "휠체어로 변환");
        }else{
            Log.d("메뉴화면(휠체어)에서", "휠체어에서 일반으로 변환");

            Intent intent=new Intent(MenuWheelActivity.this, MenuActivity.class);

            // 출처: https://wingsnote.com/128 [날개의 노트 (Wing's Note)]
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);

            startActivity(intent);
            finish();
        }

        if((functionState & InitBottomBar.BIGGER) != 0){
            Log.d("메뉴화면에서", "돋보기 기능");
            constraintLayout1.setOnTouchListener(magnifierTouchListener);
        }else{
            Log.d("메뉴화면에서", "돋보기 해제");
            constraintLayout1.setOnTouchListener(null);
        }

        if((functionState & InitBottomBar.COLORBLIND) != 0){
            Log.d("메뉴화면(휠체어)에서", "색맹으로 전환");
        }else{
            Log.d("메뉴화면(휠체어)에서", "색맹 해제");
        }
    }

}