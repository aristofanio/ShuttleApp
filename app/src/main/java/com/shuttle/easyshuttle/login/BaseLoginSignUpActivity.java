package com.shuttle.easyshuttle.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.shuttle.easyshuttle.R;
import com.shuttle.network.NetworkStatus;
import com.shuttle.utility.NetworkChangeReceiver;
import com.shuttle.utility.ToastUtility;

/**
 * Created by Deepak Pawar on 1/23/2016.
 */
public abstract class BaseLoginSignUpActivity extends AppCompatActivity
{
    private TextView _tvCustomActionbarHeader;

    protected abstract int getContentViewID();

    protected abstract int getActivityContainerViewGroupId();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_login_signup);
        initViews();
        //@todo later refer it to load from savedstate
        //http://blog.xebia.com/android-design-support-navigationview/
        attachSubActivity();
    }

    protected boolean isNetworkConnected()
    {
        NetworkChangeReceiver.initNetwork(this);
        return NetworkChangeReceiver.isOnline();
    }

    private void initViews()
    {
        Toolbar _baseToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(_baseToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_gray);
        _tvCustomActionbarHeader = (TextView) _baseToolbar.findViewById(R.id.tvCustomActionbarHeader);
    }

    private void attachSubActivity()
    {
        FrameLayout containerMainContent = (FrameLayout) findViewById(R.id.loginSignupContainer);
        View subActivityView = LayoutInflater.from(this).inflate(getContentViewID(), containerMainContent, false);
        containerMainContent.addView(subActivityView);
    }

    protected void setHeaderTitle(String headerTitle)
    {
        _tvCustomActionbarHeader.setText(headerTitle);
    }

    protected void showStatus(NetworkStatus status, int stringResourceId)
    {
        showStatus(status, stringResourceId == 0 ? null : getString(stringResourceId));
    }

    protected void showStatus(NetworkStatus status, String statusText)
    {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.networkProgressPar);

        View activityContainerView = findViewById(getActivityContainerViewGroupId());
        View errorDisplayNetworkContainer = findViewById(R.id.containerErrorDisplay);
        // View errorDisplayNoContentContainer = rootView.findViewById(R.id.tvNoContentAvailable);

        switch (status)
        {
            case STATUS_LOADING:
                progressBar.setVisibility(View.VISIBLE);
                activityContainerView.setVisibility(View.VISIBLE);
                // errorDisplayNetworkContainer.setVisibility(View.GONE);
                //errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_SUCCESS:
                progressBar.setVisibility(View.GONE);
                activityContainerView.setVisibility(View.VISIBLE);
                //errorDisplayNetworkContainer.setVisibility(View.GONE);
                //errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_ERROR:
                ToastUtility.showToast(this, getResources().getString(R.string.networkError), Toast.LENGTH_SHORT);
                progressBar.setVisibility(View.GONE);
                activityContainerView.setVisibility(View.VISIBLE);
                // errorDisplayNetworkContainer.setVisibility(View.GONE);
                // errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_NETWORK_ERROR:
                progressBar.setVisibility(View.GONE);
                activityContainerView.setVisibility(View.GONE);
                //errorDisplayNetworkContainer.setVisibility(View.VISIBLE);
                //errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_EMPTY://Specialised case of Error in Watchlist/download cases where the content is Empty.
                progressBar.setVisibility(View.GONE);
                activityContainerView.setVisibility(View.GONE);
                //errorDisplayNetworkContainer.setVisibility(View.GONE);
                // errorDisplayNoContentContainer.setVisibility(View.VISIBLE);
                break;
        }
    }


    protected void handleResponseError(VolleyError networkError)
    {
        // Handle your error types accordingly.For Timeout & No connection error, you can show 'retry' button.
        // For AuthFailure, you can re login with user credentials.
        // For ClientError, 400 & 401, Errors happening on client side when sending api request.
        // In this case you can check how client is forming the api and debug accordingly.
        // For ServerError 5xx, you can do retry or handle accordingly.
        if (networkError instanceof NetworkError)
        {

        }
        else if (networkError instanceof ServerError)
        {
        }
        else if (networkError instanceof AuthFailureError)
        {
        }
        else if (networkError instanceof ParseError)
        {
        }
        else if (networkError instanceof NoConnectionError)
        {
        }
        else if (networkError instanceof TimeoutError)
        {
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
}
