package com.example.myapplication.ui.MenuMain.MenuBar;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.myapplication.R;


public class MenuBarFragment extends Fragment {
    private String[] menuList = {"special", "premium", "burger", "alldayking", "chicken", "side", "drinkdesssert"};
    private int menuState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_menubar, container, false);

        setMenuButtonEvent(view);

        return view;
    }

    public void setMenuButtonEvent(View v) {
        final int menuBtnCount = 8;
        final ToggleButton[] menuBtnList = new ToggleButton[menuBtnCount];

        for (int i = 0; i < menuBtnCount; i++) {
            menuBtnList[i] = v.findViewWithTag("menu" + i);
            if(i==0){
                menuBtnList[i].setChecked(true);
                menuBtnList[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.frame_act_buy_menu_ic_selected));
                menuBtnList[i].setTextSize(15);
                menuBtnList[i].setTextColor(getResources().getColor(R.color.main_color));
            }
        }

        for (final ToggleButton menuBtn : menuBtnList) {
            menuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nowBtnState = Integer.parseInt(menuBtn.getTag().toString().split("menu")[1]);
                    ToggleButton beforeOnBtn = menuBtnList[menuState];

                    if (menuBtn.isChecked()) {
                        beforeOnBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.frame_act_buy_menu_ic_unselected));
                        beforeOnBtn.setTextSize(12);
                        beforeOnBtn.setTextColor(getResources().getColor(R.color.buy_menu_text_color));
                        beforeOnBtn.setChecked(false);

                        menuBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.frame_act_buy_menu_ic_selected));
                        menuBtn.setTextSize(15);
                        menuBtn.setTextColor(getResources().getColor(R.color.main_color));
                        menuBtn.setChecked(true);

                        menuState = nowBtnState;

                        Log.d("Menu_bar", "버튼 On & "+menuList[menuState]);
                    }
                }
            });
        }

//    public void sendMenuState(){
//        Bundle bundle = new Bundle(); bundle.putInt("bottomBarState", state); // Key, Value
//        fragment.setArguments(bundle);
//    }

//    public int getMenuState() {
//        return menuState;
//    }

//    public void setMenuState(int menuState) {
//        this.menuState = menuState;
//    }
    }
}
