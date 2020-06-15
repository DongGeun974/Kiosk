package com.example.myapplication.ui.bottombar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.myapplication.R;

/**
 * 하단 바 클릭시 프래그먼트, {@link InitBottomBar}에서 상속
 * <p>
 * 인스턴스 변수: x
 * </p>
 * <p>
 * 메소드:
 * {@link BottomBarCloseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)},
 * {@link BottomBarCloseFragment#change()}
 * </p>
 */
public class BottomBarOpenFragment extends InitBottomBar {
    /**
     * xml 요소 이벤트 설정 및 클릭시 {@link BottomBarOpenFragment#change()} 실행
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        state = getArguments().getInt("bottomBarState");
        Log.d("open start", String.valueOf(state));
        View view = inflater.inflate(R.layout.fragment_bottom_bar_open, container, false);

        StateView(view);

        View closeBar = (View) view.findViewById(R.id.view_close_bottom_bar);

        closeBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((InitActivity)getActivity()).setFunctionState(state);
                change();
            }
        });

        View bottombar = view.findViewById(R.id.lay_fragBottombarClose_bottomBar);

        bottombar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });

        FrameLayout f1 = (FrameLayout) view.findViewById(R.id.frame_fragBottomBarClose_iconWheel);
        f1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                WheelViewChange(v);
            }
        });

        FrameLayout f2 = (FrameLayout) view.findViewById(R.id.frame_fragBottomBarClose_iconBigger);
        f2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BiggerViewChange(v);

            }
        });

        FrameLayout f3 = (FrameLayout) view.findViewById(R.id.icon_blind);
        f3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BlindViewChange(v);
            }
        });

        return view;
    }

    /**
     * {@link BottomBarCloseFragment} 프래그먼트 실행
     */
    public void change(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomBarCloseFragment fragment = new BottomBarCloseFragment();

        Bundle bundle = new Bundle(); bundle.putInt("bottomBarState", state); // Key, Value
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_bottom_bar, fragment);

        fragmentTransaction.commit();
    }
}
