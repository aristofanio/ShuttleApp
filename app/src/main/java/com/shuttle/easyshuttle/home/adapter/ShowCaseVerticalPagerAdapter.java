package com.shuttle.easyshuttle.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.shuttle.component.verticalpager.PagerAdapter;
import com.shuttle.customcollection.ObservableDataList;
import com.shuttle.customcollection.OnDataListUpdateListener;
import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.home.interfac.IShowCaseClickListner;
import com.shuttle.easyshuttle.home.model.ShowCaseVo;
import java.lang.ref.WeakReference;

/**
 * Created by Deepak Pawar on 1/20/2016.
 */
public class ShowCaseVerticalPagerAdapter extends PagerAdapter implements OnDataListUpdateListener
{
    private WeakReference<Context> _mContext;
    private ObservableDataList<ShowCaseVo> _showCaseDataList;
    private WeakReference<IShowCaseClickListner> _showCaseClickListner;

    public ShowCaseVerticalPagerAdapter(Context context, ObservableDataList<ShowCaseVo> showCaseObservableDataList, IShowCaseClickListner showCaseClickListner)
    {
        super();
        _mContext = new WeakReference<>(context);
        _showCaseDataList = showCaseObservableDataList;
        _showCaseDataList.setOnDataListUpdateListener(this);
        _showCaseClickListner = new WeakReference<>(showCaseClickListner);
    }

    @Override
    public int getCount()
    {
        return _showCaseDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o)
    {
        return view == o;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View pagerView = LayoutInflater.from(_mContext.get()).inflate(R.layout.show_case_row_view, container, false);

        TextView showTextView = (TextView) pagerView.findViewById(R.id.showCaseText);
        ImageView showImageView = (ImageView) pagerView.findViewById(R.id.showImageView);
        showTextView.setText("ShowCase");

        container.addView(pagerView);

        return pagerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public void onDataListUpdated()
    {

    }
}

