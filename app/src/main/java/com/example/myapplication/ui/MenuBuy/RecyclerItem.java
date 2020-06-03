package com.example.myapplication.ui.MenuBuy;

import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.data.orderMenuData.OrderMenu;

/**
 * 구매 화면에서 보여주는 리싸이클러뷰의 아이템
 */
public class RecyclerItem extends OrderMenu {
    public RecyclerItem(Menu menu, int quantity) {
        super(menu, quantity);
    }
}