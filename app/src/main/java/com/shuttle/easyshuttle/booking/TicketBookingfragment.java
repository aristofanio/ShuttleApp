package com.shuttle.easyshuttle.booking;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.fragment.BaseFragment;
import com.shuttle.easyshuttle.home.adapter.LandingPagerAdapter;
import com.shuttle.utility.CustomFontHelper;

/**
 * Created by Deepak Pawar on 1/12/2016.
 */
public class TicketBookingfragment extends BaseFragment implements TabLayout.OnTabSelectedListener
{
    private ViewPager _homeViewPager;
    private TabLayout _homeTablayout;

    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.fragment_ticket_booking;
    }

    @Override
    public int getContentContainerViewGroupId()
    {
        return R.id.containerBooking;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void setupViewPager(final TabLayout tabLayout, View view)
    {
        LandingPagerAdapter landingPagerAdapter = new LandingPagerAdapter(getChildFragmentManager(), _homeTablayout.getTabCount());
        _homeViewPager = (ViewPager) getView().findViewById(R.id.homeViewPager);
        _homeViewPager.setAdapter(landingPagerAdapter);
        /***It will take title from getPageTitle **/
        //_homeTablayout.setupWithViewPager(_homeViewPager);
        _homeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(_homeTablayout));
    }

    private void initViews()
    {
        _homeTablayout = (TabLayout) getView().findViewById(R.id.homeTablayout);

        _homeTablayout.addTab(_homeTablayout.newTab().setCustomView(getCustomizeTabTextView(getResources().getString(R.string.showCase))));
        _homeTablayout.addTab(_homeTablayout.newTab().setCustomView(getCustomizeTabTextView(getResources().getString(R.string.booking))));
        _homeTablayout.addTab(_homeTablayout.newTab().setCustomView(getCustomizeTabTextView(getResources().getString(R.string.myAccount))));

        _homeTablayout.setOnTabSelectedListener(this);
        _homeTablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupViewPager(_homeTablayout, getView());
    }

    public TextView getCustomizeTabTextView(String tabTitle)
    {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custum_tabtextview, null, false);
        textView.setText(tabTitle);
        CustomFontHelper.setCustomFont(getContext(), textView, "helvetica_neue_75_bold", Typeface.NORMAL);

        return textView;
    }

    /**
     * Call This method when after network available retry button is clicked
     **/
    @Override
    public void refreshCurrentActiveFragment()
    {
    }

    /*Tab overrided methods*/
    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        _homeViewPager.setCurrentItem(tab.getPosition());
        LandingPagerAdapter.HomeTabs currentTab = LandingPagerAdapter.HomeTabs.values()[tab.getPosition()];
        switch (currentTab)
        {
            case Showcase:

                break;

            case Booking:

                break;

            case MyAccount:

                break;

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab)
    {

    }
}
