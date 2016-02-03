package com.shuttle.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Deepak Pawar on 1/26/2016.
 */
public class NetworkChangeReceiver extends BroadcastReceiver
{

    public static final int NETWORK_NOT_CONNECTED = 100;
    public static final int NETWORK_CONNECTED = 101;
   // public static final IntentFilter mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private static ConnectivityManager cm;
    protected Context context;
    protected Handler handler;

    public NetworkChangeReceiver(Context _context, Handler _hHandler)
    {

        context = _context;
        handler = _hHandler;
    }

    public static void initNetwork(Context context)
    {
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isOnline()
    {
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null)
        {
            return netInfo.isConnected(); // WIFI connected
        }
        else
        {
            return false;
        }

    }

    public static boolean isWiFiConnected()
    {
        boolean connected = false;
        if (cm != null)
        {
            NetworkInfo mWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWifi.isConnected())
            {
                connected = true;
            }
        }
        return connected;
    }

    @Override
    public void onReceive(Context _context, Intent intent)
    {

        try
        {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))
            {
                initNetwork(context);
                boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
                Message msg = new Message();
                if (noConnectivity)
                {
                    msg.what = NETWORK_NOT_CONNECTED;
                    handler.sendMessage(msg);

                }
                else

                {
                    msg.what = NETWORK_CONNECTED;
                    handler.sendMessage(msg);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();

        }
    }
}

