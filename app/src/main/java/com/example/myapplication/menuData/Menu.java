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
                ",category='" + category +'\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", url='" + url +'\'' +
                '}';
    }
}
