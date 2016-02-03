package com.shuttle.easyshuttle.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.fragment.BaseFragment;
import com.shuttle.easyshuttle.login.LoginActivity;

/**
 * Created by Deepak Pawar on 1/17/2016.
 */
public class MyAccountFragment extends BaseFragment
{
    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.fragment_my_account;
    }

    @Override
    public int getContentContainerViewGroupId()
    {
        return R.id.contentContainer;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView()
    {
        Button loginButton = (Button) getView().findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginClickListner);
    }

    View.OnClickListener loginClickListner = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent loginIntent=new Intent(getActivity(), LoginActivity.class);
            startActivity(loginIntent);
        }
    };

    @Override
    public void refreshCurrentActiveFragment()
    {

    }


}
