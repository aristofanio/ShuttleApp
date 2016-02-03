package com.shuttle.easyshuttle.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.BaseNavigationUiActivity;
import com.shuttle.easyshuttle.booking.TicketBookingfragment;

/**
 * Created by Deepak Pawar on 1/11/2016.
 */
public class ShuttleHomeActivity extends BaseNavigationUiActivity
{
    @Override
    protected int getContentViewID() {return R.layout.activity_shuttle_homeactivity;}

    /***get which fragment is the current fragment in container***/
    public Fragment getCurrentFragment()
    {
        return getFragmentInContainer(R.id.fragmentFrameContainer);
    }
    /*******repace or add fragment****/
    public void setCurrentFragment(Fragment fragment, boolean toAdd, boolean addToBackStack, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim, boolean clearBackStack)
    {
        replaceContentFragment(getSupportFragmentManager(), fragment, toAdd, R.id.fragmentFrameContainer, enterAnim, exitAnim, popEnterAnim, popExitAnim, addToBackStack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getMainFragment() == null)
        {
            navigateTo(new TicketBookingfragment());
        }
    }

}
