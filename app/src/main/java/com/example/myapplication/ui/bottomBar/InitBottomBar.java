package com.example.myapplication.ui.bottomBar;

import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;


public class InitBottomBar extends Fragment {
    /**
     * 추가 기능을 플래그 형식으로 나타냄
     * 비트로 구성
     * 1: WHEEL (0001)
     * 2: BIGGER (0010)
     * 4: COLORBLIND (0100)
     * state=1 >> WHEEL만 적용
     * state=3 >> WHEEL이랑 BIGGER랑 적용
     * state=5
     *
     */
    static int state;
    int WHEEL = 0x00000001;
    int BIGGER = 0x00000002;
    int COLORBLIND = 0x00000004;

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

    public void WheelViewChange(View v){
        ImageView WheelSel = (ImageView) v.findViewById(R.id.icon_wheel_selected);
        ImageView WheelUnSel = (ImageView) v.findViewById(R.id.icon_wheel_unselected);

        if((state & WHEEL) != 0){
            WheelSel.setVisibility(View.GONE);
            WheelUnSel.setVisibility(View.VISIBLE);

            state = state ^ WHEEL;

        }else{
            WheelSel.setVisibility(View.VISIBLE);
            WheelUnSel.setVisibility(View.GONE);

            state = state | WHEEL;
        }
    }

    public void BiggerViewChange(View v){
        ImageView BiggerSel = (ImageView) v.findViewById(R.id.icon_bigger_selected);
        ImageView BiggerUnSel = (ImageView) v.findViewById(R.id.icon_bigger_unselected);

        if((state & BIGGER) != 0){
            BiggerSel.setVisibility(View.GONE);
            BiggerUnSel.setVisibility(View.VISIBLE);

            state = state ^ BIGGER;

        }else{
            BiggerSel.setVisibility(View.VISIBLE);
            BiggerUnSel.setVisibility(View.GONE);

            state = state | BIGGER;
        }
    }

    public void BlindViewChange(View v){
        ImageView BlindSel = (ImageView) v.findViewById(R.id.icon_blind_selected);
        ImageView BlindUnSel = (ImageView) v.findViewById(R.id.icon_blind_unselected);

        //여기가 껐을때
        if((state & COLORBLIND) != 0){
            BlindSel.setVisibility(View.GONE);
            BlindUnSel.setVisibility(View.VISIBLE);

            state = state ^ COLORBLIND;

        }//여기가 켰을때
        else{
            BlindSel.setVisibility(View.VISIBLE);
            BlindUnSel.setVisibility(View.GONE);

            state = state | COLORBLIND;
        }
    }

    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        InitBottomBar.state = state;
    }
}
