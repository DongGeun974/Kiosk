package com.example.myapplication.ui.MenuCategoryBar;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.myapplication.R;
import com.example.myapplication.ui.MenuMain.MenuActivity;

import static com.example.myapplication.ui.InitActivity.getDbMenuList;


public class MenuCategoryBarFragment extends Fragment {
    private String[] categoryList = {"chichen", "burger", "set", "beverage", "side"};
    private int menuState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_menubar, container, false);

        setMenuButtonEvent(view);

        return view;
    }

    public void setMenuButtonEvent(View v) {
        final int menuBtnCount = 4;
        final ToggleButton[] menuBtnList = new ToggleButton[menuBtnCount];

        for (int i = 0; i < menuBtnCount; i++)
            menuBtnList[i] = v.findViewWithTag("menu" + i);

        menuBtnList[0].setTextColor(getResources().getColor(R.color.white));
        menuBtnList[0].setChecked(true);

        for (final ToggleButton menuBtn : menuBtnList) {
            menuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nowBtnState = Integer.parseInt(menuBtn.getTag().toString().split("menu")[1]);
                    ToggleButton beforeOnBtn = menuBtnList[menuState];

                    if (menuBtn.isChecked()) {
                        beforeOnBtn.setTextColor(getResources().getColor(R.color.dark_grey));
                        beforeOnBtn.setChecked(false);

                        menuBtn.setTextColor(getResources().getColor(R.color.white));
                        menuBtn.setChecked(true);

                        menuState = nowBtnState;
                        ((MenuActivity)getActivity()).setCategoryState(menuState);
                        ((MenuActivity)getActivity()).getCategoryMenuList().setMenuListWithCategory(getDbMenuList(), categoryList[menuState]);
                        ((MenuActivity)getActivity()).displayMenuList();

                        Log.d("Menu_bar", "버튼 On & "+categoryList[menuState]);

                    }else if(menuBtn == beforeOnBtn){
                        menuBtn.setTextColor(getResources().getColor(R.color.white));
                        menuBtn.setChecked(true);
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
