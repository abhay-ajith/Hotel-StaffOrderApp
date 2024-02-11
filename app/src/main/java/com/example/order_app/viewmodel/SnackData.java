package com.example.order_app.viewmodel;

public class SnackData {
    private String Item;
    private Integer ItemImage;

    public SnackData(String item, Integer itemImage) {
        Item = item;
        ItemImage = itemImage;
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
}
