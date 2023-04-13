package com.example.clinic;

import java.util.List;

public class FrontOfficeNavItem {
    private String title;
    private int icon;
    private List<FrontOfficeSubNavItem> frontsubItems;

    public FrontOfficeNavItem(String title, int icon, List<FrontOfficeSubNavItem> frontsubItems) {
        this.title = title;
        this.icon = icon;
        this.frontsubItems = frontsubItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public List<FrontOfficeSubNavItem> getFrontsubItems() {
        return frontsubItems;
    }

    public void setFrontsubItems(List<FrontOfficeSubNavItem> frontsubItems) {
        this.frontsubItems = frontsubItems;
    }
}
