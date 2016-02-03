package com.shuttle.easyshuttle.appbase.View;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.adapter.NavigationItem;
import com.shuttle.utility.CustomFontHelper;

/**
 * Created by Deepak Pawar on 2/1/2016.
 */
public class NavigationCellView extends RelativeLayout
{
    private TextView _navItemText;
    private ImageView _navItemImage;

    public NavigationCellView(Context context)
    {
        super(context);
    }

    public NavigationCellView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public NavigationCellView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void setData(NavigationItem navigationItem)
    {
        _navItemText = (TextView) findViewById(R.id.navItemText);
        _navItemImage = (ImageView) findViewById(R.id.navItemImage);


        if (navigationItem.isSelected())
        {
            CustomFontHelper.setCustomFont(getContext(), _navItemText, "helvetica_neue_65_medium", Typeface.NORMAL);
        }
        else
        {
            CustomFontHelper.setCustomFont(getContext(), _navItemText, "helvetica_neue_65_medium", Typeface.NORMAL);
        }

        _navItemText.setText(navigationItem.getItemName());
    }
}


