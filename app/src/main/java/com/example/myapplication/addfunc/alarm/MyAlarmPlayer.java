package com.example.myapplication.addfunc.alarm;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * 서버 음성 파일 재생 관련 클래스
 * <p>
 * 메소드: {@link #AlarmStartSound()}, {@link #AlarmNoInputSound()}
 * </p>
 */
class MyAlarmPlayer {
    private static MediaPlayer Start;
    private static MediaPlayer NoInput;

    /**
     * 메뉴 화면으로 넘어갈 시 재생
     */
    static void AlarmStartSound() {
        try {
            Start = new MediaPlayer();
            Start.setDataSource("http://zxcasd3004.dothome.co.kr/project/menu_cart.mp3");
            Start.prepare();
            Start.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 메뉴 화면에서 입력 없을 시 재생
     */
    static void AlarmNoInputSound() {
        try {
            NoInput = new MediaPlayer();
            NoInput.setDataSource("http://zxcasd3004.dothome.co.kr/project/no_input.mp3");
            NoInput.prepare();
            NoInput.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
