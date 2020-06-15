package com.example.myapplication.addfunc.alarm;

import android.media.MediaPlayer;

import java.io.IOException;

public class MyAlarmPlayer {
    private static MediaPlayer Start;
    private static MediaPlayer NoInput;

    public static void AlarmStartSound() {
        try {
            Start = new MediaPlayer();
            Start.setDataSource("http://zxcasd3004.dothome.co.kr/project/menu_cart.mp3");
            Start.prepare();
            Start.start();

            //Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AlarmNoInputSound() {
        try {
            NoInput = new MediaPlayer();
            NoInput.setDataSource("http://zxcasd3004.dothome.co.kr/project/no_input.mp3");
            NoInput.prepare();
            NoInput.start();

            //Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void AlarmStartSound() {
//        try {
//            MediaPlayer Start = new MediaPlayer();
//            Start.setDataSource("http://zxcasd3004.dothome.co.kr/project/menu_cart.mp3");
//            Start.prepare();
//            Start.start();
//
//            //Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void AlarmNoInputSound() {
//        try {
//            MediaPlayer NoInput = new MediaPlayer();
//            NoInput.setDataSource("http://zxcasd3004.dothome.co.kr/project/no_input.mp3");
//            NoInput.prepare();
//            NoInput.start();
//
//            //Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
