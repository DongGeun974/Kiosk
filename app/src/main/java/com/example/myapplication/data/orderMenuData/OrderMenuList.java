package com.example.myapplication.data.orderMenuData;

import java.util.ArrayList;

public class OrderMenuList {
    ArrayList<OrderMenu> orderMenuList = new ArrayList<>();

    public ArrayList<OrderMenu> getOrderMenuList() {
        return orderMenuList;
    }

    public void setOrderMenuList(ArrayList<OrderMenu> menuCart) {
        this.orderMenuList = menuCart;
    }

    public void addOrderMenuList(OrderMenu orderMenu){
        getOrderMenuList().add(orderMenu);
    }

    public void clearOrderMenuList(){
        setOrderMenuList(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "OrderMenuList{" +
                "orderMenuList=" + orderMenuList +
                '}';
    }
}
