package com.example.myapplication.data.menuData;

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

    public void addMenuList(Menu menu){
        getMenuList().add(menu);
    }

    public void setMenuListWithCategory(MenuList dbMenuList, String... categorys){
        setMenuList(new ArrayList<>());
        Log.d("setMenuListWithCategory", "category is "+ categorys);

        for(Menu item : dbMenuList.getMenuList())
            for(String category : categorys)
                if(item.getCategory().equals(category))
                    getMenuList().add(item);

    }

    public Menu getMenuWithId(int id){
        for(Menu menu: getMenuList())
            if (menu.getId() == id)
                return menu;

        return null;
    }
}
