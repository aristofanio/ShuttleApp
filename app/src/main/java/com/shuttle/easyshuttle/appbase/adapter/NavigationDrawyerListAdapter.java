package com.shuttle.easyshuttle.appbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.View.NavigationCellView;

import java.util.ArrayList;

/**
 * Created by Deepak Pawar on 1/28/2016.
 */
public class NavigationDrawyerListAdapter extends ArrayAdapter<NavigationItem>
{
    private ArrayList<NavigationItem> _navigationItems;

    public NavigationDrawyerListAdapter(Context _context, ArrayList<NavigationItem> navigationItems)
    {
        super(_context, 0, navigationItems);
        _navigationItems = navigationItems;
    }

    @Override
    public int getCount()
    {
        return _navigationItems.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        NavigationCellView navigationCellView = (NavigationCellView) inflater.inflate(R.layout.navigation_drawer_cell_view, null);
        navigationCellView.setData(_navigationItems.get(position));
        return navigationCellView;
    }
}
