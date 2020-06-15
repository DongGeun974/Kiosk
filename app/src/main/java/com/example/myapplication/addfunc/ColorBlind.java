package com.example.myapplication.addfunc;

public class ColorBlind {
    private boolean on;

    public String changeURL(String url){
        if(on)
            return url.replace("original", "colorblind");
        else
            return url;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
