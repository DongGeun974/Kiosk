package com.example.myapplication.ui.bottomBar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.ui.InitActivity;

/**
 * 하단 바 프래그먼트, {@link InitBottomBar}에서 상속
 * <p>
 * 인스턴스 변수:
 * view(별 역할 x)
 * </p>
 * <p>
 * 메소드:
 * {@link BottomBarCloseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)},
 * {@link BottomBarCloseFragment#change()}, {@link BottomBarCloseFragment#onStart()},
 * {@link BottomBarCloseFragment#onResume()}
 * </p>
 */
public class BottomBarCloseFragment extends InitBottomBar{
    View view;

    /**
     * xml 요소 이벤트 설정 및 클릭시 {@link BottomBarCloseFragment#change()} 실행
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        state = getArguments().getInt("bottomBarState");
        Log.d("Close start", String.valueOf(state));
        view = inflater.inflate(R.layout.fragment_bottom_bar_close, container, false);

        StateView(view);

        LinearLayout l = (LinearLayout) view.findViewById(R.id.lay_fragBottombarClose_bottomBar);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

        return view;
    }

    /**
     * {@link BottomBarOpenFragment} 프래그먼트 실행
     */
    public void change(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomBarOpenFragment fragment = new BottomBarOpenFragment();

        Bundle bundle = new Bundle(); bundle.putInt("bottomBarState", state); // Key, Value
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_bottom_bar, fragment);

        fragmentTransaction.commit();
    }

    /**
     * 시작시 현재 {@link InitBottomBar#state}에 따라 보여지는 버튼 모양 변경
     */
    @Override
    public void onStart() {
        super.onStart();

        StateView(view);
    }

    /**
     * {@link BottomBarOpenFragment}에서 돌아오면 현재 state를 {@link InitActivity#setFunctionState(int)} 통해 전달,
     * 또한 {@link InitActivity#checkFunctionState()} 호출해서 추가기능 여부 확인
     */
    @Override
    public void onResume(){
        super.onResume();

        ((InitActivity)getActivity()).setFunctionState(state);
        ((InitActivity)getActivity()).checkFunctionState();


    }
}
