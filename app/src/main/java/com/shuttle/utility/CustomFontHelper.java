package com.shuttle.utility;

/**
 * Created by Deepak Pawar on 1/25/2016.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.shuttle.easyshuttle.R;

/**
 * Taken from: http://stackoverflow.com/a/16648457/75579
 */
public class CustomFontHelper
{
    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    /**
     * Sets a font on a textview based on the custom com.my.package:font attribute
     * If the custom font attribute isn't found in the attributes nothing happens
     *
     * @param textview
     * @param context
     * @param attrs
     */
    public static void setCustomFont(TextView textview, Context context, AttributeSet attrs)
    {
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomFont);
        String fontName = attributeArray.getString(R.styleable.CustomFont_font);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        setCustomFont(context, textview, fontName, textStyle);

        attributeArray.recycle();
    }

    /**
     * Sets a font on a textview
     *
     * @param textview
     * @param fontName
     * @param context
     */
    public static void setCustomFont(Context context, TextView textview, String fontName, int textStyle)
    {
        if (fontName == null)
        {
            return;
        }
        Typeface customFontTypeface = selectTypeface(context, fontName, textStyle);
        if (customFontTypeface != null)
        {
            textview.setTypeface(customFontTypeface);
        }
    }

    /****************************/

    private static Typeface selectTypeface(Context context, String fontName, int textStyle)
    {
        if (fontName.contentEquals(context.getString(R.string.helvetica_neue_75_bold)))
        {
            return FontCache.getFontTypeFace(context, "fonts/helvetica_neue_75_bold.ttf");

        }
        else if (fontName.contentEquals(context.getString(R.string.helvetica_neue_65_medium)))
        {
            return FontCache.getFontTypeFace(context, "fonts/helvetica_neue_65_medium.ttf");
        }
        else if (fontName.contentEquals(context.getString(R.string.feedback)))
        {
          /*
          information about the TextView textStyle:
          http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
          */
            switch (textStyle)
            {
                case Typeface.BOLD: // bold
                    return FontCache.getFontTypeFace(context, "SourceSansPro-Bold.ttf");

                case Typeface.ITALIC: // italic
                    return FontCache.getFontTypeFace(context, "SourceSansPro-Italic.ttf");

                case Typeface.BOLD_ITALIC: // bold italic
                    return FontCache.getFontTypeFace(context, "SourceSansPro-BoldItalic.ttf");

                case Typeface.NORMAL: // regular
                default:
                    return FontCache.getFontTypeFace(context, "SourceSansPro-Regular.ttf");
            }
        }
        else
        {
            // no matching font found
            // return null so Android just uses the standard font (Roboto)
            return null;
        }
    }
}