package com.shuttle.network.networkv2;

/**
 * Created by Deepak Pawar on 1/20/2016.
 */

import android.content.Context;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.shuttle.easyshuttle.BuildConfig;
import com.shuttle.easyshuttle.R;

/**
 * Generic Volley Listener
 *
 * @author Deepak Panwar
 */
public class NetworkListener<T> implements Response.Listener<T>, Response.ErrorListener
{
    private static final String TAG = "NetworkListener";
    private int mReqType;
    private Context mContext;
    private onUpdateListener mUpdateListener;

    public NetworkListener(Context context, onUpdateListener listener, int reqType)
    {
        mContext = context;
        mUpdateListener = listener;
        mReqType = reqType;
    }

    @Override
    public void onResponse(T response)
    {
        mUpdateListener.onUpdateView(response, true, mReqType);
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        String errorMsg;
        if (error instanceof NoConnectionError)
        {
            errorMsg = mContext.getString(R.string.networkError);
        }
        else if (error instanceof ServerError)
        {
            errorMsg = mContext.getString(R.string.serverError);
        }
        else if (error instanceof TimeoutError)
        {
            errorMsg = mContext.getString(R.string.timeoutError);
        }
        else
        {
            errorMsg = mContext.getString(R.string.defaultError);
        }
        if (BuildConfig.DEBUG)
        {
            error.printStackTrace();
        }
        mUpdateListener.onUpdateView(errorMsg, false, mReqType);
    }

    /**
     * Interface which is used to update UI after Network operation finish
     */
    public interface onUpdateListener
    {
        /**
         * Callback method called after Network Operation finish
         *
         * @param response
         * @param success
         * @param reqType
         */
        public void onUpdateView(Object response, boolean success, int reqType);
    }
}