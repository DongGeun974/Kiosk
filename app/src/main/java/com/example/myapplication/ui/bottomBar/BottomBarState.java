package com.example.myapplication.ui.bottomBar;

public interface BottomBarState {
    /**
     * 비트로 구성
     * 1: Wheel
     * 2: bigger
     * 4: color blind
     */
    int state = 0;

    int WHEEL = 0x00000001;
    int BIGGER = 0x00000002;
    int COLORBLIND = 0x00000004;

//    void setState();
//    void getState();
}