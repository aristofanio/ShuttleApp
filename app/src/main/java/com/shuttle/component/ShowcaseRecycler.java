package com.shuttle.component;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.shuttle.customcollection.ObservableDataList;
import com.shuttle.easyshuttle.home.adapter.ShowCaseDataAdapter;
import com.shuttle.easyshuttle.home.interfac.IShowCaseClickListner;
import com.shuttle.easyshuttle.home.model.ShowCaseVo;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Deepak Pawar on 1/20/2016.
 */
public class ShowcaseRecycler extends RecyclerView
{
    private ObservableDataList<ShowCaseVo> showCaseDataList;
    private Handler _scrollHandler;

    public ShowcaseRecycler(Context context)
    {
        super(context);
    }

    public ShowcaseRecycler(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ShowcaseRecycler(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        initialize();
    }

    private void initialize()
    {
        showCaseDataList = new ObservableDataList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.setLayoutManager(linearLayoutManager);
    }

    public void setShowCaseData(IShowCaseClickListner showCaseClickListner)
    {
        ShowCaseDataAdapter showCaseDataAdapter = new ShowCaseDataAdapter(showCaseDataList, showCaseClickListner);
        super.setAdapter(showCaseDataAdapter);
        //this.setItemAnimator(new DefaultItemAnimator());
    }

    private void incrementScrollCount()
    {
        int visibleItemPosition = ((LinearLayoutManager)this.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        if (visibleItemPosition < showCaseDataList.size()-1)
        {
            this.smoothScrollToPosition(visibleItemPosition+1);
        }
        else
        {
            this.scrollToPosition(0);
        }
    }

    public void createTimerThread()
    {
        _scrollHandler = new Handler();
        _scrollHandler.postDelayed(showcaseRunnable, 3000);
    }

    Runnable showcaseRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            ShowcaseRecycler.this.incrementScrollCount();
            _scrollHandler.postDelayed(this, 3000);
        }
    };

    public void stopTimerThread()
    {
        _scrollHandler.removeCallbacks(showcaseRunnable);
    }

    public void addData(List<ShowCaseVo> list)
    {
        showCaseDataList.addAll(list);
    }

    public void addData(ShowCaseVo ShowCaseVo)
    {
        showCaseDataList.add(ShowCaseVo);
    }

    public void resetAdapterWith(List<ShowCaseVo> list)
    {
        showCaseDataList.resetWith(list);
    }

    public void resetAdapterWith(ShowCaseVo ShowCaseVo)
    {
        ArrayList<ShowCaseVo> list = new ArrayList<ShowCaseVo>();
        resetAdapterWith(list);
    }
}
