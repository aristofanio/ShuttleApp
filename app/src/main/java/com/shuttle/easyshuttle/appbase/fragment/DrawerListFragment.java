package com.shuttle.easyshuttle.appbase.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.adapter.NavigationDrawyerListAdapter;
import com.shuttle.easyshuttle.appbase.adapter.NavigationItem;

import java.util.ArrayList;

/**
 * Created by Deepak Pawar on 1/28/2016.
 */
public class DrawerListFragment extends BaseFragment implements AdapterView.OnItemClickListener
{

    private NavigationDrawerCallbacks _drawerListner;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }

    public interface NavigationDrawerCallbacks
    {
        void onLandingDrawerSelected(DrawerListFragment.DrawerItems drawerItems, View view);

        void onLandingDrawerSameItemSelected();
    }

    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.fragment_drawer_list;
    }

    @Override
    public int getContentContainerViewGroupId()
    {
        return R.id.contentDrawerContent;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            _drawerListner = (NavigationDrawerCallbacks) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.getClass().getSimpleName() + " must implement NavigationDrawerCallbacks");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews()
    {
        ListView navigationDrawerList = (ListView) getView().findViewById(R.id.navigationDrawerList);
        /***Set Header For Listview****/
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.navigation_header_view, navigationDrawerList,false);
        navigationDrawerList.addHeaderView(headerView);

        ArrayList<NavigationItem> navigationItems = initDrawerItems();
        NavigationDrawyerListAdapter navigationAdapter = new NavigationDrawyerListAdapter(getActivity(), navigationItems);
        navigationDrawerList.setAdapter(navigationAdapter);
        navigationDrawerList.setOnItemClickListener(this);
    }

    private ArrayList<NavigationItem> initDrawerItems()
    {
        ArrayList<NavigationItem> navigationItems = new ArrayList<>();
        String[] menuItems = getResources().getStringArray(R.array.navigationMenuItems);
        for (int i = 0; i < DrawerItems.values().length; i++)
        {
            DrawerItems item = DrawerItems.values()[i];
            if (i == 0)
            {
                item.initDrawerItems(i, "Home");
                continue;
            }
            item.initDrawerItems(i, menuItems[i]);
            /**********************initialize listitems*********/
            NavigationItem navItem = new NavigationItem();
            navItem.setItemName(menuItems[i]);
            navigationItems.add(navItem);
        }
        return navigationItems;
    }

    public enum DrawerItems
    {
        BOOKTICKET(),
        SURVEY(),
        OFFERAVAILABLE(),
        BOOKINGHISTORY(),
        USERPROFILE(),
        FEEDBACK(),
        SETTING(),
        TERMSANDCONDITION(),
        ABOUTUS(),
        LOGOUT();

        private int _intValue;

        private String _name;

        public Integer getIntValue()
        {
            return _intValue;
        }

        public String getName()
        {
            return _name;
        }

        public void initDrawerItems(int value, String name)
        {
            _intValue = value;
            _name = name;
        }
    }

    @Override
    public void refreshCurrentActiveFragment()
    {

    }
}
