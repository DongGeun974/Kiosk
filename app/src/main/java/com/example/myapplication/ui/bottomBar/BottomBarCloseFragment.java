package com.example.myapplication.ui.bottomBar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.ui.InitActivity;

public class BottomBarCloseFragment extends InitBottomBar{
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        state = getArguments().getInt("bottomBarState");
        Log.d("Close start", String.valueOf(state));
        view = inflater.inflate(R.layout.fragment_bottom_bar_close, container, false);

        StateView(view);

        LinearLayout l = (LinearLayout) view.findViewById(R.id.lay_fragBottombarClose_bottomBar);
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

        Bundle bundle = new Bundle(); bundle.putInt("bottomBarState", state); // Key, Value
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_bottom_bar, fragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();

        StateView(view);
    }

    @Override
    public void onResume(){
        super.onResume();

        ((InitActivity)getActivity()).setFunctionState(state);
        ((InitActivity)getActivity()).checkFunctionState();
    }
}
