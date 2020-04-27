package com.example.myapplication.ui.bottomBar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.example.myapplication.R;

public class BottomBarCloseFragment extends Fragment implements BottomBarState{
    int state = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_bar_close, container, false);

        Log.d("cccccc", String.valueOf(view.getId()));

        LinearLayout l = (LinearLayout) view.findViewById(R.id.bottom_bar);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

        return view;
    }

    public void change(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomBarOpenFragment fragment = new BottomBarOpenFragment();
        fragmentTransaction.replace(R.id.frame_bottom_bar, fragment);
        fragmentTransaction.commit();
    }

    public void buttonChange(View v){
        if((state & 1) != 0){
//            ImageView
        }
    }
}
