package com.example.myapplication.ui.MenuCategoryBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/**
 * {@link MenuCategoryBarFragment}에서 상속. xml 파일 제외 동일
 */
public class MenuCategoryBarWheelFragment extends MenuCategoryBarFragment {
    private String[] categoryList = {"chicken", "burger&set&box", "side", "beverage"};
    private int menuState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_menubarwheel, container, false);

        setCategoryButtonEvent(view);

        return view;
    }

}
