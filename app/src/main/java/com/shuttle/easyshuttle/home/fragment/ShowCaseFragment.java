package com.shuttle.easyshuttle.home.fragment;

import android.os.Bundle;
import android.view.View;

import com.shuttle.component.verticalpager.BidirectionalViewPager;
import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.appbase.fragment.BaseFragment;
import com.shuttle.easyshuttle.home.interfac.IShowCaseClickListner;
import com.shuttle.easyshuttle.home.model.ShowCaseVo;

/**
 * Created by Deepak Pawar on 1/17/2016.
 */
public class ShowCaseFragment extends BaseFragment implements IShowCaseClickListner
{
    private BidirectionalViewPager showCaseViewPager;

    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.showcase_home_fragment;
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews()
    {
        showCaseViewPager = (BidirectionalViewPager) getView().findViewById(R.id.showCaseViewPager);
        for (int i = 0; i < 9; i++)
        {
            showCaseViewPager.addData(new ShowCaseVo());
        }
        showCaseViewPager.setPagerDataAdapter(this);
        showCaseViewPager.startAutoScroll(7000);
        showCaseViewPager.setInterval(8000);
        showCaseViewPager.setAutoScrollDurationFactor(3);
        showCaseViewPager.setMovementVelocity(1500);
    }

    @Override
    public void onDestroyView()
    {
        showCaseViewPager.stopAutoScroll();
        super.onDestroyView();
    }

    @Override
    public void onShowCaseItemClick(ShowCaseVo showCaseVo)
    {

    }
}
