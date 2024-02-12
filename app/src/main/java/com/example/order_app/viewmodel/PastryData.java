package com.example.order_app.viewmodel;

public class PastryData {
    private String Item;
    private Integer ItemImage;

    private String quantity;


    public PastryData(String item, Integer itemImage, String quantity) {
        Item = item;
        ItemImage = itemImage;
        this.quantity = quantity;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public Integer getItemImage() {
        return ItemImage;
    }

    public void setItemImage(Integer itemImage) {
        ItemImage = itemImage;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
