package com.example.myapplication.addfunc.alarm;

import android.app.Activity;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 안내 관련 기능 수행
 * <p>
 * {@link #start()} 통해 시작, {@link #cancel()} 통해 취소, {@link #restart()} 통해 재시작 가능
 * </p>
 */
public class Alarm {
    /**
     * 현재 초
     */
    private int sec;
    /**
     * 초기화면 복귀 시간
     */
    private int restartTime;
    /**
     * 안내문 출력 시간
     */
    private int explainTime;
    private Timer timer;
    private Activity activity;
    private TimerTask timerTask;

    public Alarm(){
        sec = 0;
        restartTime = 90;
        explainTime = 60;
    }

    /**
     * 타이머 시간에 따라 안내문 출력 및 초기화면 복귀
     */
    private void setTimerTask() {
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                //timer handling
                if (sec == restartTime) {
                    Log.i(this.getClass().getName(), "system re-boot");
                    activity.finish();
                } else if (sec == explainTime) {
                    Log.i(this.getClass().getName(), "voice start");
                    MyAlarmPlayer.AlarmNoInputSound();
                }

                sec++;
            }
        };
    }

    /**
     * 타이머 시작
     */
    public void start(){
        sec = 0;

        setTimerTask();
        timer = new Timer();

        timer.schedule(timerTask, 1000 , 1000);

        MyAlarmPlayer.AlarmStartSound();
    }

    /**
     * 현재 타이머 취소
     */
    public void cancel(){

        if(timer != null)
            timer.cancel();

        if(timerTask != null)
            timerTask.cancel();
    }

    /**
     * 타이머 재시작
     */
    public void restart() {
        sec = 0;
        cancel();

        setTimerTask();
        timer = new Timer();

        timer.schedule(timerTask, 0 , 1000);
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
