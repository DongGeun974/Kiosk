package com.example.myapplication.addfunc;

import android.annotation.SuppressLint;

import com.example.myapplication.addfunc.alarm.Alarm;

public interface AddFunction {
    Magnify magnify = new Magnify();
    @SuppressLint("StaticFieldLeak")
    Alarm alarm = new Alarm();
    ColorBlind colorBlind = new ColorBlind();
    Wheel wheel = new Wheel();
}