package com.example.myapplication.addfunc;

import android.annotation.SuppressLint;

import com.example.myapplication.addfunc.alarm.Alarm;

/**
 * 추가기능에 접근할 수 있도록 돕는 인터페이스
 */
public interface AddFunction {
    Magnify magnify = new Magnify();
    @SuppressLint("StaticFieldLeak")
    Alarm alarm = new Alarm();
    ColorBlind colorBlind = new ColorBlind();
    Wheel wheel = new Wheel();
}