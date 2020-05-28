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

    public void addOrderMenuAtList(OrderMenu orderMenu){
        getOrderMenuList().add(orderMenu);
    }

    public void clearOrderMenuList(){
        setOrderMenuList(new ArrayList<>());
    }

    public void deleteZeroQuantityItem(){
        for(OrderMenu orderMenu: getOrderMenuList())
            if(orderMenu.getQuantity() == 0) {
                getOrderMenuList().remove(orderMenu);
            }
    }

    @Override
    public String toString() {
        return "OrderMenuList{" +
                "orderMenuList=" + orderMenuList +
                '}';
    }
}
