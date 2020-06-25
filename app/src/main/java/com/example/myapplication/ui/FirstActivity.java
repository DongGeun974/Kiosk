package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;
import com.example.myapplication.data.orderData.OrderList;
import com.example.myapplication.ui.menumain.MenuActivity;
import com.example.myapplication.ui.bottombar.InitBottomBar;

/**
 * 처음 화면
 * {@link InitActivity}에서 상속
 * onCreate, checkActivity 메소드 가짐
 */
public class FirstActivity extends InitActivity {

    /**
     * activity_first 보여줌
     * @param savedInstanceState 액티비티 전환 간 데이터 전달하는 {@link Bundle} 객체
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        displayBottomBar();

        Button exit = (Button) findViewById(R.id.btn_actMain_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button start = (Button) findViewById(R.id.btn_actMain_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this, MenuActivity.class);

                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);

                MenuActivity.setCart(new OrderList());
                startActivity(intent);
            }
        });


    }

    /**
     * {@link InitActivity#checkFunctionState()} 재정의
     * <p>
     * 휠체어: 화면 크기만 변경
     *
     * </p>
     * <p>
     * 돋보기: 각자 적기
     *
     * </p>
     * <p>
     * 색맹: 각자 적기
     *
     * </p>
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void checkFunctionState() {
        int functionState =  InitBottomBar.getState();

        Log.d("시작화면에서", String.valueOf(functionState));

        if((functionState & InitBottomBar.WHEEL) != 0) {
            ConstraintLayout c = (ConstraintLayout)findViewById(R.id.lay_actMain_screen);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) c.getLayoutParams();
            layoutParams.matchConstraintPercentHeight = 0.6F;
            layoutParams.verticalBias = 0.75F;
            c.setLayoutParams(layoutParams);

            wheel.setOn(true);

            Log.d("시작화면에서", "휠체어로 변환");
        }else{
            ConstraintLayout c = (ConstraintLayout)findViewById(R.id.lay_actMain_screen);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) c.getLayoutParams();
            layoutParams.matchConstraintPercentHeight = 0.9F;
            layoutParams.verticalBias = 0;
            c.setLayoutParams(layoutParams);

            wheel.setOn(false);

            Log.d("시작화면에서", "휠체어에서 일반으로 변환");
        }

        if((functionState & InitBottomBar.BIGGER) != 0){
            Log.d("시작화면에서", "돋보기 기능");

            magnify.setOn(true);
            magnify.setMagnifyOnView(findViewById(R.id.lay_actMain_root));
        }else{
            Log.d("시작화면에서", "돋보기 해제");

            magnify.setOn(false);
            magnify.setMagnifyOnView(findViewById(R.id.lay_actMain_root));
        }

        if((functionState & InitBottomBar.COLORBLIND) != 0){
            Log.d("시작화면에서", "색맹으로 전환");
        }else{
            Log.d("시작화면에서", "색맹 해제");
        }
    }
}