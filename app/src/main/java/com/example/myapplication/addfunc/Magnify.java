package com.example.myapplication.addfunc;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Magnifier;

import androidx.annotation.RequiresApi;

/**
 * 돋보기 관련된 기능 수행
 * <p>
 * {@link #setMagnifyOnView(View)} 통해 해당 뷰에 돋보기 기능 제공
 * </p>
 */
public class Magnify {
    private boolean on;

    private int width;
    private int height;
    private float zoom;
    /**
     * 돋보기 객체
     */
    private Magnifier magnifier;

    /**
     * 뷰 클릭 리스너
     */
    private View.OnTouchListener magnifierTouchListener = new View.OnTouchListener() {
        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE: {
                    final int[] viewPosition = new int[2];
                    v.getLocationOnScreen(viewPosition);
                    magnifier.show(event.getRawX() - viewPosition[0],
                            event.getRawY() - viewPosition[1]);
                    break;
                }
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP: {
                    magnifier.dismiss();
                }
            }
            return true;
        }
    };

    Magnify(){
        width = 600;
        height = 400;
        zoom = 3;
    }

    /**
     * 해당 뷰에 돋보기 기능하는 뷰 리스너 설정
     * @param view 기능을 제공하고자 하는 뷰
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void setMagnifyOnView(View view){
        if(on){
            Magnifier.Builder builder = new Magnifier.Builder(view);
            builder.setSize(width, height);
            builder.setInitialZoom(zoom);
            magnifier = builder.build();

            view.setOnTouchListener(magnifierTouchListener);
        }
        else
            view.setOnTouchListener(null);
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
