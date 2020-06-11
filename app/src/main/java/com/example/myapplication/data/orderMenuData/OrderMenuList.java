package com.example.myapplication.data.orderMenuData;

import java.util.ArrayList;

/**
 * 주문들을 저장하는 클래스
 * <p>
 * 인스턴스 변수:
 * {@link OrderMenuList#orderMenuList}
 * </p>
 * <p>
 * 메소드:
 * {@link OrderMenuList#addOrderMenuAtList(OrderMenu)},
 * {@link OrderMenuList#clearOrderMenuList()},
 * {@link OrderMenuList#deleteZeroQuantityItem()}
 * </p>
 */
public class OrderMenuList {
    /**
     * {@link OrderMenu}로 이루어진 ArrayList
     */
    ArrayList<OrderMenu> orderMenuList = new ArrayList<>();

    public ArrayList<OrderMenu> getOrderMenuList() {
        return orderMenuList;
    }

    public void setOrderMenuList(ArrayList<OrderMenu> menuCart) {
        this.orderMenuList = menuCart;
    }

    /**
     * {@link OrderMenuList#orderMenuList}에 주문 추가
     * @param orderMenu 추가를 원하는 주문
     */
    public void addOrderMenuAtList(OrderMenu orderMenu){
        getOrderMenuList().add(orderMenu);
    }

    /**
     * {@link OrderMenuList#orderMenuList}의 모든 주문 삭제
     */
    public void clearOrderMenuList(){
        setOrderMenuList(new ArrayList<>());
    }

    /**
     * {@link OrderMenuList#orderMenuList}의 수량이 0개인 주문 삭제
     */
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
