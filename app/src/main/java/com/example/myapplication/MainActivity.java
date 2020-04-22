package com.example.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends initActivity{

    /**
     * checkKeyButton 메서드를 통해 키 값 확인 후 해당 키 입력 이벤트 재정의해서 지움
     *
     * @param keycode 사용자 입력 키값
     * @param event 미리 정의된 각 키 별 변수
     * @return 조건에 일치 시 true, 아니라면 false
     */
    //기능: 키이벤트 발생 시 처리
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(checkKeyButton(keycode)){
            return true;
        }
        return super.onKeyDown(keycode, event);
    }

    /**
     * 입력 키가 백버튼, 볼륨버튼, 왼쪽 소프트키, 오른쪽 소프트키인지 검사
     * @param keycode 사용자 입력 키값
     * @return 조건에 일치 시 true, 아니라면 false
     */
    //기능: 눌린 키가 볼륨키 혹은 소프트키인지 식별 뒤 해당 키라면 T, 아니라면 F
    protected boolean checkKeyButton(int keycode){
        if(keycode == KeyEvent.KEYCODE_BACK ||
                keycode == KeyEvent.KEYCODE_VOLUME_DOWN ||
                keycode == KeyEvent.KEYCODE_VOLUME_UP ||
                keycode == KeyEvent.KEYCODE_SOFT_LEFT ||
                keycode == KeyEvent.KEYCODE_SOFT_RIGHT ){
            return true;
        }else
            return false;
    }

    /**
     * 메뉴버튼 클릭 시 액티비티 위치 변경 통해 유지
     */
    protected void onPause() {
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initScreen();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doExitButton();

        Button button1 = (Button) findViewById(R.id.beverage);
        button1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), beverage.class);
                startActivity(intent);
            }
        }));

        Button button2 = (Button) findViewById(R.id.box);
        button2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), box.class);
                startActivity(intent);
            }
        }));

        Button button3 = (Button) findViewById(R.id.burger);
        button3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), burger.class);
                startActivity(intent);
            }
        }));

        Button button4 = (Button) findViewById(R.id.chicken);
        button4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), chicken.class);
                startActivity(intent);
            }
        }));

        Button button5 = (Button) findViewById(R.id.set);
        button5.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), set.class);
                startActivity(intent);
            }
        }));

        Button button6 = (Button) findViewById(R.id.side);
        button6.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), side.class);
                startActivity(intent);
            }
        }));

//        ConstraintLayout cc = new ConstraintLayout(this);
    }
}
