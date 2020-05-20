package com.example.myapplication.data.orderMenuData;

import com.example.myapplication.data.menuData.Menu;

public class OrderMenu {
    int quantity;
    Menu menu;

    public OrderMenu(Menu menu, int quantity){
        setMenu(menu);
        setQuantity(quantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "OrderMenu{" +
                "quantity=" + quantity +
                ", menu=" + menu +
                '}';
    }
}
