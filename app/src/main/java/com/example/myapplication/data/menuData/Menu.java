package com.example.myapplication.data.menuData;

import android.media.Image;

/**
 * 메뉴 하나를 저장하는 클래스
 * <p>
 * 인스턴스 변수:
 * id, category, name, price, url
 * </p>
 * <p>
 * 메소드:
 * getter와 setter
 * </p>
 */
public class Menu {
    private int id;
    private String category;
    private String name;
    private int price;
    private String url;
    private Image img;

    public int getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getUrl() {
        return url;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
