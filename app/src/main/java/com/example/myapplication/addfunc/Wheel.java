package com.example.myapplication.addfunc;

/**
 * 휠체어와 관련된 기능 수행
 * <p>
 * {@link #isOnChange(boolean)} 통해 해당 기능의 변화 여부 감지
 * </p>
 */
public class Wheel {
    private boolean on;

    public boolean isOnChange(boolean currentState){
        boolean result;
        if (on ==  currentState)
            result = false;
        else
            result = true;

        on = currentState;
        return result;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
