package com.example.myapplication.menuData;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MenuList {
    private ArrayList<Menu> menuList = new ArrayList<>();

    public void setMenuList(List<Menu> input) {
        menuList = (ArrayList<Menu>) input;
    }

    public List<Menu> getMenuList(){
        return menuList;
    }

    public void setCategoryMenuList(MenuList dbMenuList, String category){
        setMenuList(new ArrayList<>());
        Log.d("asasas", " / "+category);
        for(Menu item : dbMenuList.getMenuList()){
//            Log.d("aaaaaaaa", item.getCategory()+"  "+category);
//            Log.d("aaaaaaaa", String.valueOf(item.getCategory().equals(category)));
            if(item.getCategory().equals(category)) {
                getMenuList().add(item);
            }
        }
    }
}
