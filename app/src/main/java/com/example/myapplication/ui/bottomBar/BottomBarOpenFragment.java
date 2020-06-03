package com.example.myapplication.ui.bottomBar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.ui.InitActivity;

public class BottomBarOpenFragment extends InitBottomBar {
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
                ((InitActivity)getActivity()).setFunctionState(state);
                change();
            }
        });

        View bottombar = view.findViewById(R.id.lay_fragBottombarClose_bottomBar);

        bottombar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });
//        view.findViewById(R.id.fragment_bottom_bar_open).bringToFront();

        FrameLayout f1 = (FrameLayout) view.findViewById(R.id.icon_wheel);
        f1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                WheelViewChange(v);
            }
        });

        FrameLayout f2 = (FrameLayout) view.findViewById(R.id.icon_bigger);
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
