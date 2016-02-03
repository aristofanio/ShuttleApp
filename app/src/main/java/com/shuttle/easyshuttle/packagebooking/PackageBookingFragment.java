package com.shuttle.easyshuttle.packagebooking;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.fragment.BaseFragment;

/**
 * Created by Deepak Pawar on 1/17/2016.
 */
public class PackageBookingFragment extends BaseFragment
{
    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.package_booking_fragment;
    }

    @Override
    public int getContentContainerViewGroupId()
    {
        return R.id.contentFragment;
    }

    @Override
    public void refreshCurrentActiveFragment()
    {

    }
}
