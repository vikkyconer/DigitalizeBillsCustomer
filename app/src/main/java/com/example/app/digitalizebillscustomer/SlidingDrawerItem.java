package com.example.app.digitalizebillscustomer;

/**
 * Created by vikkycorner on 28/05/16.
 */
public class SlidingDrawerItem {
    private String title;
    private int icon;
    private String count = "0";

    private boolean isCounterVisible = false;

    public SlidingDrawerItem() {}

    public SlidingDrawerItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public SlidingDrawerItem(String title, int icon, boolean isCounterVisible, String count){
        this.title = title;
        this.icon = icon;
        this.isCounterVisible = isCounterVisible;
        this.count = count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public boolean isCounterVisible() {
        return isCounterVisible;
    }

    public void setCounterVisible(boolean counterVisible) {
        isCounterVisible = counterVisible;
    }
}
