package com.example.myapplication.data.orderData;

import java.util.ArrayList;

/**
 * 주문들을 저장하는 클래스
 * <p>
 * 인스턴스 변수:
 * {@link OrderList#orderList}
 * </p>
 * <p>
 * 메소드:
 * {@link OrderList#addOrderMenuAtList(Order)},
 * {@link OrderList#clearOrderMenuList()},
 * {@link OrderList#deleteZeroQuantityItem()}
 * </p>
 */
public class OrderList {
    /**
     * {@link Order}로 이루어진 ArrayList
     */
    ArrayList<Order> orderList = new ArrayList<>();

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> menuCart) {
        this.orderList = menuCart;
    }

    /**
     * {@link OrderList#orderList}에 주문 추가
     * @param order 추가를 원하는 주문
     */
    public void addOrderMenuAtList(Order order){
        getOrderList().add(order);
    }

    /**
     * {@link OrderList#orderList}의 모든 주문 삭제
     */
    public void clearOrderMenuList(){
        setOrderList(new ArrayList<>());
    }

    /**
     * {@link OrderList#orderList}의 수량이 0개인 주문 삭제
     */
    public void deleteZeroQuantityItem(){
        for(Order order : getOrderList())
            if(order.getQuantity() == 0) {
                getOrderList().remove(order);
            }
    }

    @Override
    public String toString() {
        return "OrderMenuList{" +
                "orderMenuList=" + orderList +
                '}';
    }
}
