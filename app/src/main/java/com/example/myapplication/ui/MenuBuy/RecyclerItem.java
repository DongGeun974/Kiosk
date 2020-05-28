package com.example.myapplication.ui.MenuBuy;

import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.data.orderMenuData.OrderMenu;

public class RecyclerItem extends OrderMenu {
    public RecyclerItem(Menu menu, int quantity) {
        super(menu, quantity);
    }
}