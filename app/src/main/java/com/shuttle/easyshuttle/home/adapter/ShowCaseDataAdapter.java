package com.shuttle.easyshuttle.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuttle.customcollection.ObservableDataList;
import com.shuttle.customcollection.OnDataListUpdateListener;
import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.home.interfac.IShowCaseClickListner;
import com.shuttle.easyshuttle.home.model.ShowCaseVo;

import java.lang.ref.WeakReference;

/**
 * Created by Deepak Pawar on 1/20/2016.
 */
public class ShowCaseDataAdapter extends RecyclerView.Adapter<ShowCaseDataAdapter.ShowViewHolder> implements OnDataListUpdateListener
{
    private ObservableDataList<ShowCaseVo> _showCaseVoObservableDataList;
    private WeakReference<IShowCaseClickListner> _recyclerCyclerClickListener;

    public ShowCaseDataAdapter(ObservableDataList<ShowCaseVo> showCaseDataList, IShowCaseClickListner recyclerListner)
    {
        _showCaseVoObservableDataList = showCaseDataList;
        _showCaseVoObservableDataList.setOnDataListUpdateListener(this);
        _recyclerCyclerClickListener = new WeakReference<IShowCaseClickListner>(recyclerListner);
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View showCaseView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_case_row_view, parent, false);

        return new ShowViewHolder(showCaseView);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position)
    {
        holder._showTextView.setText("ShowCase");
        //holder._showImageView.setImageResource(R.drawable.ic_launcher);
    }

    @Override
    public int getItemCount()
    {
        return _showCaseVoObservableDataList.size();
    }

    public void destroy()
    {
        _showCaseVoObservableDataList = null;
    }

    public static class ShowViewHolder extends RecyclerView.ViewHolder
    {
        private TextView _showTextView;
        private ImageView _showImageView;

        public ShowViewHolder(View itemView)
        {
            super(itemView);
            _showTextView = (TextView) itemView.findViewById(R.id.showCaseText);
            _showImageView = (ImageView) itemView.findViewById(R.id.showImageView);
        }
    }

    @Override
    public void onDataListUpdated()
    {
        notifyDataSetChanged();
    }
}
