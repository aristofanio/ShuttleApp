package com.shuttle.easyshuttle.appbase.adapter;

/**
 * Created by Deepak Pawar on 1/28/2016.
 */
public class NavigationItem
{
    private String itemName;
    private int itemImage;
    private boolean selected;

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public int getItemImage()
    {
        return itemImage;
    }

    public void setItemImage(int itemImage)
    {
        this.itemImage = itemImage;
    }


}
