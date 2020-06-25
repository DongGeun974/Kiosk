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
import com.example.myapplication.addfunc.AddFunction;
import com.example.myapplication.data.menuData.GitHubService;
import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.data.menuData.MenuList;
import com.example.myapplication.ui.bottombar.BottomBarCloseFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 * 다른 액티비티들의 최상위 액티비티용 클래스
 *
 * </p>
 * <p>
 * 인스턴스 변수: {@link InitActivity#dbMenuList}
 * </p>
 * <p>
 * 메소드: {@link InitActivity#onKeyDown(int, KeyEvent)}(재정의), {@link InitActivity#checkKeyButton(int)},
 * {@link InitActivity#onPause()}(재정의), {@link InitActivity#onCreate(Bundle)}((재정의),
 * {@link InitActivity#displayBottomBar()}, {@link InitActivity#makeFullScreen()},
 * {@link InitActivity#getFromNetwork()}, {@link InitActivity#checkFunctionState()},
 *
 * </p>
 */
public abstract class InitActivity extends Activity implements AddFunction {
    /**
     * DB에서 가져온 항목들을 {@link MenuList} 클래스로 저장
     */
    static MenuList dbMenuList = new MenuList();

    /**
     * 최대화면으로 전환({@link InitActivity#makeFullScreen()}, 네트워크에서 DB 가져오기({@link InitActivity#getFromNetwork()}
     * @param savedInstanceState 액티비티 전환 간 데이터 전달하는 {@link Bundle} 객체
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();

        getFromNetwork();
    }

    @Override
    protected void onResume() {
        super.onResume();

        makeFullScreen();
    }

    /**
     * 메뉴버튼 클릭 시 액티비티 위치 변경 통해 유지 및 액티비티 전환 애니메이션 삭제
     */
    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);

        overridePendingTransition(0, 0);
    }

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
     * {@link BottomBarCloseFragment}를 화면으로 보여줌
     */
    protected void displayBottomBar(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomBarCloseFragment bottomBarCloseFragment = new BottomBarCloseFragment();
        fragmentTransaction.add(R.id.frame_bottom_bar, bottomBarCloseFragment);

        fragmentTransaction.commit();
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
     * 몰입 모드(전체화면) 적용하기 위해 해당하는 플래그 설정
     */
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

    /**
     * DB에서 각 항목들을 가져와 {@link InitActivity#dbMenuList}에 저장
     */
    public void getFromNetwork(){
        GitHubService gitHubService;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rldnd2637.dothome.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
        Call<List<Menu>> call = gitHubService.getRepos();

        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if (response.isSuccessful()) {
                    dbMenuList.setMenuList(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.e("Not Response", call + " " + t.getLocalizedMessage());
            }
        });
    }

    /**
     * 하단바 상태 변경 확인하는 메서드,
     * 추상 클래스라 상속받은 모든 클래스에서는 필수적으로 재정의 필요
     */
    public abstract void checkFunctionState();

    public static MenuList getDbMenuList() {
        return dbMenuList;
    }

    public static void setDbMenuList(MenuList dbMenuList) {
        InitActivity.dbMenuList = dbMenuList;
    }
}