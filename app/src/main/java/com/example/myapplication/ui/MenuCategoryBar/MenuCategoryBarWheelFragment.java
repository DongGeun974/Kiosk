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


public class MenuCategoryBarWheelFragment extends MenuCategoryBarFragment {
    private String[] categoryList = {"chicken", "burger&set&box", "side", "beverage"};
    private int menuState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_menubarwheel, container, false);

        setMenuButtonEvent(view);

        return view;
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
