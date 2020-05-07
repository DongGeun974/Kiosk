package com.example.myapplication.menuData;

public class Menu {

    private int id;
    private String category;
    private String name;
    private int price;
    private String url;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
    public String getUrl() {
        return url;
    }
    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ",category='" + category +'\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", url='" + url +'\'' +
                '}';
    }
}
