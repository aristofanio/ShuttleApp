package com.shuttle.utility;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;
import com.shuttle.easyshuttle.BuildConfig;

/**
 * Created by Deepak Pawar on 1/19/2016.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements
        ImageLoader.ImageCache
{
    public static int getDefaultLruCacheSize()
    {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        if (BuildConfig.DEBUG)
        {
            Log.d("BitmapCacheSize==>", "Memory will use " + cacheSize);
        }
        return cacheSize;
    }

    public LruBitmapCache()
    {
        this(getDefaultLruCacheSize());
    }

    public LruBitmapCache(int sizeInKiloBytes)
    {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value)
    {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url)
    {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap)
    {
        put(url, bitmap);
    }
}