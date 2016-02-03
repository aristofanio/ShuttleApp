package com.shuttle.easyshuttle.home.fragment;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.fragment.BaseFragment;

/**
 * Created by Deepak Pawar on 1/17/2016.
 */
public class BookingFragment extends BaseFragment
{
    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.fragment_home_booking;
    }

    @Override
    public int getContentContainerViewGroupId()
    {
        return R.id.contentContainer;
    }

    @Override
    public void refreshCurrentActiveFragment()
    {

    }
}
