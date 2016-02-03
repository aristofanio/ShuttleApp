package com.shuttle.network.networkv2;

/**
 * Created by Deepak Pawar on 1/20/2016.
 */

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.shuttle.easyshuttle.BuildConfig;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by Gson.
 *
 * @author Deepak Panwar
 */
public class NetworkRequest<T> extends Request<T>
{
    private static final String TAG = "NetworkRequest";
    private final Class<T> mClass;
    private final NetworkListener<T> mListener;
    private final Gson mGson = new Gson();
    private final Map<String, String> mParams;
    private Context mContext;

    /**
     * Network Request
     * Map<String, String> map = new HashMap<>();
     * <p/>
     * /* NetworkRequest networkRequest = new NetworkRequest(getBaseContext(), NetworkAPI.URL, Place.class, new NetworkUpdateListener(getBaseContext(), this, NetworkAPI.REQ_URL), map);
     * NearbyAppController.getInstance().addToRequestQueue(networkRequest, TAG);
     * Make a POST request and return a parsed object from JSON.
     *
     * @param url      for request
     * @param tClass   Gson reflection class object
     * @param listener response listener
     */
    public NetworkRequest(Context context, String url, Class<T> tClass, NetworkListener listener, Map<String, String> params)
    {
        super(Method.GET, url, listener);
        mContext = context;
        mClass = tClass;
        mListener = listener;
        mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return mParams;
    }

    @Override
    protected void deliverResponse(T response)
    {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            if (BuildConfig.DEBUG)
            {
                Log.d(TAG, "Response :: " + response.data == null ? null : new String(response.data));
            }
            return Response.success(
                    mGson.fromJson(json, mClass), HttpHeaderParser.parseCacheHeaders(response));
        }
        catch (UnsupportedEncodingException ex)
        {
            if (BuildConfig.DEBUG)
            {
                ex.printStackTrace();
            }
            return Response.error(new ParseError(ex));
        }
        catch (JsonSyntaxException ex)
        {
            if (BuildConfig.DEBUG)
            {
                ex.printStackTrace();
            }
            return Response.error(new ParseError(ex));
        }
    }
}
