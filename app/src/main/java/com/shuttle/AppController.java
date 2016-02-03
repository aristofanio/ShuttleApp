package com.shuttle;

import android.app.Application;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.shuttle.square.OkHttpStack;
import com.shuttle.utility.LruBitmapCache;


/**
 * Created by Deepak Pawar on 1/19/2016.
 */
public class AppController extends Application
{
    public static final String TAG = AppController.class
            .getSimpleName();

    private RequestQueue networkRequestQue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance()
    {
        return mInstance;
    }

    /**
     * Returns a Volley request queue for creating network requests
     * new request queue has an HttpStack as a parameter. If you use the method that don’t provide an
     * HttpStack Volley will create an stack depending on your API level.
     * (based on the AndroidHttpClient for API level 9 and and HttpURLConnection stack for API level 10 and above)
     * OkHttp as our transport layer, that’s the reason we are using as a parameter an OkHttpStack.
     * @return {@link com.android.volley.RequestQueue}
     */
    public RequestQueue getRequestQueue()
    {
        if (networkRequestQue == null)
        {
            networkRequestQue = Volley.newRequestQueue(getApplicationContext(), new OkHttpStack());
        }
        return networkRequestQue;
    }

    public ImageLoader getImageLoader()
    {
        getRequestQueue();
        if (mImageLoader == null)
        {
            mImageLoader = new ImageLoader(this.networkRequestQue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag)
    {
        if (networkRequestQue != null)
        {
            networkRequestQue.cancelAll(tag);
        }
    }
}
