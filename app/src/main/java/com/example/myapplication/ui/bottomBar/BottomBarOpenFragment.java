package com.example.myapplication.ui.bottomBar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;

public class BottomBarOpenFragment extends Fragment implements BottomBarState {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_bar_open, container, false);

        View closeBar = (View) view.findViewById(R.id.view_close_bottom_bar);

        closeBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

        view.findViewById(R.id.fragment_bottom_bar_open).bringToFront();

        final ImageView a = (ImageView) view.findViewById(R.id.icon_wheel);

        a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(a.getId() == R.id.ic_frame){
                    Log.d("000000000000", String.valueOf(a.getBackground() == getResources().getDrawable(R.drawable.icon_frame)));
                    a.setBackgroundResource(R.drawable.icon_frame_selected);
                }else
                    a.setBackgroundResource(R.drawable.icon_frame);
            }
        });


        FrameLayout e = (FrameLayout) view.findViewById(R.id.qqq);



        return view;
    }


    public void change(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomBarCloseFragment fragment = new BottomBarCloseFragment();
        fragmentTransaction.replace(R.id.frame_bottom_bar, fragment);
        fragmentTransaction.commit();
    }
}
