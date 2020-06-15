package com.example.myapplication.addfunc;

public class Wheel {
    public boolean on;

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
