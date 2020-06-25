package com.example.myapplication.addfunc;

/**
 * 색맹 관련 기능 수행
 * <p>
 * {@link #changeURL(String)} 통해 Glide 라이브러리 URL 변경
 * </p>
 */
public class ColorBlind {
    private boolean on;

    /**
     * Glide 라이브러리 URL 변경
     * @param url 기존 URL
     * @return 기능이 켜져있다면 변환해서 반환, 꺼져있다면 그대로 반환
     */
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
