package com.example.order_app.viewmodel;

public class MenucatData {

    private String menucat;

    private Integer menucat_image;

    public MenucatData(String menucat, Integer menucat_image) {
        this.menucat = menucat;
        this.menucat_image = menucat_image;
    }

    public String getMenucat() {
        return menucat;
    }

    public void setMenucat(String menucat) {
        this.menucat = menucat;
    }

    public Integer getMenucat_image() {
        return menucat_image;
    }

    public void setMenucat_image(Integer menucat_image) {
        this.menucat_image = menucat_image;
    }
}
