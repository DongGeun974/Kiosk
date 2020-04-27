package com.example.myapplication.ui.bottomBar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.myapplication.R;

public class BottomBarCloseFragment extends Fragment implements BottomBarState{
    int state;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        state = getArguments().getInt("bottomBarState");
        Log.d("Close start", String.valueOf(state));
        View view = inflater.inflate(R.layout.fragment_bottom_bar_close, container, false);

        StateView(view);

        LinearLayout l = (LinearLayout) view.findViewById(R.id.bottom_bar);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

        return view;
    }

    public void change(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomBarOpenFragment fragment = new BottomBarOpenFragment();
//        fragmentTransaction.replace(R.id.frame_bottom_bar, fragment);

        Bundle bundle = new Bundle(); bundle.putInt("bottomBarState", state); // Key, Value
        fragment.setArguments(bundle);
        Log.d("Close end", String.valueOf(state));

        fragmentTransaction.replace(R.id.frame_bottom_bar, fragment);

        fragmentTransaction.commit();
    }

    public void StateView(View v){
        ImageView WheelSel = (ImageView) v.findViewById(R.id.icon_wheel_selected);
        ImageView WheelUnSel = (ImageView) v.findViewById(R.id.icon_wheel_unselected);
        ImageView BiggerSel = (ImageView) v.findViewById(R.id.icon_bigger_selected);
        ImageView BiggerUnSel = (ImageView) v.findViewById(R.id.icon_bigger_unselected);
        ImageView BlindSel = (ImageView) v.findViewById(R.id.icon_blind_selected);
        ImageView BlindUnSel = (ImageView) v.findViewById(R.id.icon_blind_unselected);

        if((state & WHEEL) == 0) {
            WheelSel.setVisibility(View.GONE);
            WheelUnSel.setVisibility(View.VISIBLE);
        }else {
            WheelSel.setVisibility(View.VISIBLE);
            WheelUnSel.setVisibility(View.GONE);
        }

        if((state & BIGGER) == 0){
            BiggerSel.setVisibility(View.GONE);
            BiggerUnSel.setVisibility(View.VISIBLE);
        }else{
            BiggerSel.setVisibility(View.VISIBLE);
            BiggerUnSel.setVisibility(View.GONE);
        }

        if((state & COLORBLIND) == 0){
            BlindSel.setVisibility(View.GONE);
            BlindUnSel.setVisibility(View.VISIBLE);
        }else{
            BlindSel.setVisibility(View.VISIBLE);
            BlindUnSel.setVisibility(View.GONE);
        }
    }
}
