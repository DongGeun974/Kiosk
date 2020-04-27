package com.example.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myapplication.ui.bottomBar.BottomBarFragment;


public class MainActivity extends Activity {

    private BottomBarFragment F1;

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
        //출처: https://altongmon.tistory.com/395
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i("Is on?", "Turning immersive mode mode off. ");
        } else {
            Log.i("Is on?", "Turning immersive mode mode on.");
        }
        // 몰입 모드를 꼭 적용해야 한다면 아래의 3가지 속성을 모두 적용시켜야 합니다
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

        //////////////////////////////////////////////////////////////////

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.fragment_bottom_bar_close, null);

        LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        addContentView(linear, paramlinear);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bottom_bar :
                        Log.d("log01","bottom");
                        break;
                    case R.id.exitButton :
                        Log.d("log01","exit");
                        break;
                }
            }
        };

//        findViewById(R.id.bottom_bar).setOnClickListener(clickListener);

//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.add(R.layout.fragment_bottom_bar_open, );
//        fragmentTransaction.commit();


        findViewById(R.id.bottom_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.bottom_bar){
//                    setContentView(R.layout.activity_main);

                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.fragment_bottom_bar_open, null);

                    LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    );

                    addContentView(linear, paramlinear);

                    findViewById(R.id.close_bottom_bar).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                }
            }
        });

    }
}
