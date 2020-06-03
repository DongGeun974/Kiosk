package com.example.myapplication.data.orderMenuData;

import com.example.myapplication.data.menuData.Menu;

/**
 * 주문 하나를 저장하는 클래스
 * <p>
 * 인스턴스 변수:
 * {@link OrderMenu#quantity}, {@link OrderMenu#menu}
 * </p>
 * <p>
 * 메소드:
 * getter와 setter
 * </p>
 */
public class OrderMenu {
    /**
     * 현재 주문의 수량
     */
    int quantity;
    /**
     * 현재 주문한 메뉴
     */
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
