package com.example.myapplication.ui.bottomBar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class BottomBarOpenFragment extends Fragment {

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
