package com.example.myapplication.addfunc;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Magnifier;

import androidx.annotation.RequiresApi;

public class Magnify {
    //돋보기 객체
    private Magnifier magnifier;
    private boolean on;

    private int width = 600;
    private int height = 400;
    private float zoom = 3;

    //돋보기 리스너
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void setMagnifyOnView(View view, boolean isOn){
        on = isOn;
        if(isOn){
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
