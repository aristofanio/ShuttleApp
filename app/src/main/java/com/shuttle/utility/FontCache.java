package com.shuttle.utility;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

/**
 * Created by Deepak Pawar on 1/25/2016.
 */
public class FontCache
{
    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getFontTypeFace(Context context, String fontName)
    {
        if (fontCache.containsKey(fontName))
        {
            return fontCache.get(fontName);
        }
        else
        {
            Typeface tf = Typeface.createFromAsset(context.getAssets(), fontName);
            fontCache.put(fontName, tf);
            return tf;
        }
    }
}
