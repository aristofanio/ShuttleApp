package com.shuttle.easyshuttle.appbase;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.fragment.DrawerListFragment;
import com.shuttle.easyshuttle.booking.TicketBookingfragment;
import com.shuttle.easyshuttle.home.fragment.ShowCaseFragment;
import com.shuttle.utility.CustomFontHelper;

/**
 * Created by Deepak Pawar on 1/11/2016.
 */
public abstract class BaseNavigationUiActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,DrawerListFragment.NavigationDrawerCallbacks
{
    private Toolbar _baseToolbar;
    private DrawerLayout _drawerLayout;
    private TextView _tvCustomActionbarHeader;
    private View _draggablePanelContainer;
    private ActionBarDrawerToggle _actionBarDrawerToggle;
    private DrawerListFragment _drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation);
        initViews();
        //@todo later refer it to load from savedstate
        //http://blog.xebia.com/android-design-support-navigationview/
        attachSubActivity();
    }

    private void initViews()
    {
        _baseToolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbarView();
        initDrwayerToggle();
    }

    private void initToolbarView()
    {
        setSupportActionBar(_baseToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        _tvCustomActionbarHeader = (TextView) _baseToolbar.findViewById(R.id.tvCustomActionbarHeader);
        CustomFontHelper.setCustomFont(this, _tvCustomActionbarHeader, "helvetica_neue_65_medium", Typeface.BOLD);

    }

    private void initDrwayerToggle()
    {
        _drawerFragment=(DrawerListFragment)getSupportFragmentManager().findFragmentById(R.id.landingFragmentDrawer);
        _draggablePanelContainer = findViewById(R.id.landingFragmentDrawer);
        // Initializing Drawer Layout and ActionBarToggle
        _drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        _actionBarDrawerToggle = new ActionBarDrawerToggle(this, _drawerLayout, _baseToolbar, R.string.openDrawer, R.string.closeDrawer)
        {

            @Override
            public void onDrawerClosed(View drawerView)
            {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        _drawerLayout.setDrawerListener(_actionBarDrawerToggle);
        //calling sync state is necessay or else your hamburger icon wont show up
        _actionBarDrawerToggle.syncState();
    }

    /**************
     * @Newly Added
     ********/
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        _actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        _actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**********
     * @end Newly Added
     ***********/

    private void attachSubActivity()
    {
        LinearLayout containerMainContent = (LinearLayout) findViewById(R.id.mainBaseContentContainer);
        View subActivityView = LayoutInflater.from(this).inflate(getContentViewID(), containerMainContent, false);
        containerMainContent.addView(subActivityView);
    }

    protected abstract int getContentViewID();

    public Fragment getMainFragment()
    {
        return getFragmentInContainer(R.id.fragmentFrameContainer);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem)
    {
        //Checking if the item is in checked state or not, if not make it in checked state
        if (menuItem.isChecked())
        {
            menuItem.setChecked(false);
        }
        else
        {
            menuItem.setChecked(true);
        }
        //Closing drawer on item click
        _drawerLayout.closeDrawers();
        return switchBetweenFragments(menuItem);
    }


    @Override
    public void onLandingDrawerSelected(DrawerListFragment.DrawerItems drawerItems, View view)
    {

    }

    @Override
    public void onLandingDrawerSameItemSelected()
    {

    }

    private boolean switchBetweenFragments(MenuItem menuItem)
    {
        boolean transaction = false;
        Fragment oldFragment = getMainFragment();
        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId())
        {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.ticketBooking:
                if (!(oldFragment instanceof TicketBookingfragment))
                {
                    navigateTo(new TicketBookingfragment());
                    transaction = true;
                }
                break;
            case R.id.survey:
                transaction = true;
                break;
            case R.id.bookingHistory:
                transaction = true;
                break;
            case R.id.termsCondition:
                transaction = true;
                break;
            case R.id.feedback:
                transaction = true;
                break;
            case R.id.userSettings:
                transaction = true;
                break;
            default:
                transaction = false;

        }
        return transaction;
    }

    /****
     * replace using getSupportFragmentManager (1st level fragment)
     ***/
    protected void navigateTo(Fragment fragment)
    {
        replaceContentFragment(getSupportFragmentManager(), fragment, false, R.id.fragmentFrameContainer, 0, 0, 0, 0, false);
    }

    @Override
    public void replaceContentFragment(android.support.v4.app.FragmentManager fragmentManager, Fragment fragment, boolean toAddOrReplace, int containerId, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim, boolean addToBackStack)
    {
        super.replaceContentFragment(fragmentManager, fragment, toAddOrReplace, containerId, enterAnim, exitAnim, popEnterAnim, popExitAnim, addToBackStack);
        fragmentManager.executePendingTransactions();
        toggleDrawerIndicator();
    }

    public void toggleDrawerIndicator()
    {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
        {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            _actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            lockUnlockNavigationDrawer(false);
        }
        else
        {
            _actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            lockUnlockNavigationDrawer(true);
        }
        detectCorrectSection();
    }


    public void detectCorrectSection()
    {
        Fragment currentFragment = getMainFragment();

        NavigationItem selectedItem = NavigationItem.Home;
        if (currentFragment instanceof ShowCaseFragment)
        {
            selectedItem = NavigationItem.Home;
        }
        updateHeader(selectedItem.getToolbarTitle());
    }


    public boolean closeDrawyer()
    {
        if (_drawerLayout != null && _drawerLayout.isDrawerOpen(_draggablePanelContainer))
        {
            _drawerLayout.closeDrawer(_draggablePanelContainer);
            return true;
        }
        return false;
    }

    public void lockUnlockNavigationDrawer(boolean toLock)
    {
        if (toLock)
        {
            _drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        else if (_actionBarDrawerToggle.isDrawerIndicatorEnabled())
        {
            _drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    public void resetTopHeaderView(boolean tohideActionBar)
    {
        if (tohideActionBar)
        {
            getSupportActionBar().hide();
        }
        else
        {
            getSupportActionBar().show();
        }
    }

    public void updateHeader(String titleText)
    {
        if (titleText == null)
        {
            return;
        }
        _tvCustomActionbarHeader.setText(titleText);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH)
        {
            _baseToolbar.setElevation(getApplicationContext().getResources().getDimension(R.dimen.titleBarElevation));

        }
    }

    enum NavigationItem
    {
        Home("Home"),
        Survey("Survey"),
        BookingHistory("Booking History"),
        TermsCondition("Terms and Conditions"),
        Feesback("Feedback"),
        Settings("Settings");

        String toolbarTitle;

        NavigationItem(String title)
        {
            toolbarTitle = title;

        }

        public String getToolbarTitle()
        {
            return toolbarTitle;
        }
    }

}




















