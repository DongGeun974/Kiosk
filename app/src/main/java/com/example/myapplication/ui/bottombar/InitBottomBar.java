package com.example.myapplication.ui.bottombar;

import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

/**
 * 하단 바들의 상위 클래스, 이미지뷰를 이용한 상태라 토글버튼으로 수정 필요함(중요도 중간)
 * <p>
 * 인스턴스 변수:
 * {@link InitBottomBar#state}, {@link InitBottomBar#WHEEL},
 * {@link InitBottomBar#BIGGER}, {@link InitBottomBar#COLORBLIND}
 * </p>
 * <p>
 * 메소드:
 * {@link InitBottomBar#StateView(View)}, {@link InitBottomBar#WheelViewChange(View)},
 * {@link InitBottomBar#BiggerViewChange(View)}, {@link InitBottomBar#BlindViewChange(View)}
 * </p> */
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
    public static final int WHEEL = 0x00000001;
    public static final int BIGGER = 0x00000002;
    public static final int COLORBLIND = 0x00000004;

    /**
     * xml의 6가지 이미지 뷰를 현재 상태에 맞게 수정함(아주 안 좋은 방법)
     * @param v
     */
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
