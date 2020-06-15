package com.example.myapplication.addfunc.alarm;

import android.app.Activity;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private int sec = 0; // timer
    private int restartTime = 15;
    private int explainTime = 10;
    private Timer timer;
    private Activity activity;
    private TimerTask timerTask;

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

    public void start(){
        sec = 0;

        setTimerTask();
        timer = new Timer();

        timer.schedule(timerTask, 1000 , 1000);

        MyAlarmPlayer.AlarmStartSound();
    }

    public void cancel(){

        if(timer != null)
            timer.cancel();

        if(timerTask != null)
            timerTask.cancel();
    }

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
