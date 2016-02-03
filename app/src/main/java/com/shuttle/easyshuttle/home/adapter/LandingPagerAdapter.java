package com.shuttle.easyshuttle.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shuttle.easyshuttle.home.fragment.BookingFragment;
import com.shuttle.easyshuttle.home.fragment.MyAccountFragment;
import com.shuttle.easyshuttle.home.fragment.ShowCaseFragment;

/**
 * Created by Deepak Pawar on 1/17/2016.
 */
public class LandingPagerAdapter extends FragmentPagerAdapter
{
    int _tabCount;

    public LandingPagerAdapter(FragmentManager fm, int tabCount)
    {
        super(fm);
        this._tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment _contentTabFragment = null;
        HomeTabs currentTab = HomeTabs.values()[position];
        switch (currentTab)
        {
            case Showcase:

                _contentTabFragment = new ShowCaseFragment();
                break;

            case Booking:
                _contentTabFragment = new BookingFragment();
                break;

            case MyAccount:
                _contentTabFragment = new MyAccountFragment();
                break;
        }
        return _contentTabFragment;
    }

    @Override
    public int getCount()
    {
        return _tabCount;
    }


    private String getTabName(int position)
    {
        for (HomeTabs item : HomeTabs.values())
        {
            if (item.getCode() == position)
            {
                return item.getTabName();
            }
        }
        return "";

    }

    public enum HomeTabs
    {
        Showcase("Showcase", 0),
        Booking("Booking", 1),
        MyAccount("My Account", 2);

        String _tabName;
        int _tabPosition;

        HomeTabs(String tabName, int code)
        {
            this._tabName = tabName;
            this._tabPosition = code;
        }

        HomeTabs(int code)
        {
            this._tabPosition = code;
        }

        public String getTabName()
        {
            return _tabName;
        }

        public int getCode()
        {
            return _tabPosition;
        }
    }
}
