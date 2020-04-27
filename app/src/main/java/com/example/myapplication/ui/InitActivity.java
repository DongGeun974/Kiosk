package com.example.myapplication.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.ui.bottomBar.BottomBarCloseFragment;


public class InitActivity extends Activity {

    /**
     * checkKeyButton 메서드를 통해 키 값 확인 후 해당 키 입력 이벤트 재정의해서 지움
     *
     * @param keycode 사용자 입력 키값
     * @param event 미리 정의된 각 키 별 변수
     * @return 조건에 일치 시 true, 아니라면 false
     */
    @Override
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
    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomBarCloseFragment fragment = new BottomBarCloseFragment();
        fragmentTransaction.add(R.id.frame_bottom_bar, fragment);

        Bundle bundle = new Bundle(); bundle.putInt("bottomBarState", 0); // Key, Value
        fragment.setArguments(bundle);

        fragmentTransaction.commit();
    }

    protected void makeFullScreen(){
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
    }
}
