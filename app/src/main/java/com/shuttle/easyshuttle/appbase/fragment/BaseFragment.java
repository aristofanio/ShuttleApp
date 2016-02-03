package com.shuttle.easyshuttle.appbase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.IRefreshViewListner;
import com.shuttle.network.NetworkStatus;
import com.shuttle.utility.ToastUtility;

/**
 * Created by Deepak Pawar on 1/12/2016.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, IRefreshViewListner
{
    private static final int REQUEST_SETTINGS = 1088;

    public abstract int getFragmentLayoutId();

    public abstract int getContentContainerViewGroupId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.base_fragment_view_container, container, false);
        initViews(view, inflater);
        return view;
    }

    private void initViews(View view, LayoutInflater inflater)
    {
        ViewGroup subFragmentViewContainer = (ViewGroup) view.findViewById(R.id.subFragmentViewContainer);
        View subFragmentView = inflater.inflate(getFragmentLayoutId(), subFragmentViewContainer, false);
        subFragmentViewContainer.removeAllViews();
        subFragmentViewContainer.addView(subFragmentView);
        Button _btnRetry = (Button) view.findViewById(R.id.btnRetry);
        _btnRetry.setOnClickListener(this);
        Button _btnSettings = (Button) view.findViewById(R.id.btnSettings);
        _btnSettings.setOnClickListener(this);
    }

    protected void showStatus(NetworkStatus status, String statusText)
    {
        if (getView() == null)
        {
            return;
        }

        View rootView = getView();

        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.pbBaseFragmentProgress);

        View fragmentContentView = rootView.findViewById(getContentContainerViewGroupId());
        View errorDisplayNetworkContainer = rootView.findViewById(R.id.containerErrorDisplay);
        View errorDisplayNoContentContainer = rootView.findViewById(R.id.tvNoContentAvailable);

        switch (status)
        {
            case STATUS_LOADING:
                progressBar.setVisibility(View.VISIBLE);
                fragmentContentView.setVisibility(View.GONE);
                errorDisplayNetworkContainer.setVisibility(View.GONE);
                errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_SUCCESS:
                progressBar.setVisibility(View.GONE);
                fragmentContentView.setVisibility(View.VISIBLE);
                errorDisplayNetworkContainer.setVisibility(View.GONE);
                errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_ERROR:
                ToastUtility.showToast(getActivity(), getResources().getString(R.string.networkError), Toast.LENGTH_SHORT);
                progressBar.setVisibility(View.GONE);
                fragmentContentView.setVisibility(View.VISIBLE);
                errorDisplayNetworkContainer.setVisibility(View.GONE);
                errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_NETWORK_ERROR:
                progressBar.setVisibility(View.GONE);
                fragmentContentView.setVisibility(View.GONE);
                errorDisplayNetworkContainer.setVisibility(View.VISIBLE);
                errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_EMPTY://Specialised case of Error in Watchlist/download cases where the content is Empty.
                progressBar.setVisibility(View.GONE);
                fragmentContentView.setVisibility(View.GONE);
                errorDisplayNetworkContainer.setVisibility(View.GONE);
                errorDisplayNoContentContainer.setVisibility(View.VISIBLE);
                break;
        }
    }

    protected void showStatus(NetworkStatus status, int stringResourceId)
    {
        showStatus(status, stringResourceId == 0 ? null : getString(stringResourceId));
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnRetry:
                refreshCurrentActiveFragment();
                break;

            case R.id.btnSettings:
                startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), REQUEST_SETTINGS);
                break;
        }
    }


}
