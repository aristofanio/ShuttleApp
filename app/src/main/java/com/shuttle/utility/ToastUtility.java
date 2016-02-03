package com.shuttle.utility;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.shuttle.easyshuttle.R;

/**
 * Created by Deepak Pawar on 1/12/2016.
 */
public class ToastUtility
{
    private static Toast makeToast(Context context, CharSequence text, int duration)
    {
        Toast result = new Toast(context);

        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.custom_toast, null);
        TextView tv = (TextView) v.findViewById(R.id.toastText);
        tv.setText(text);
        result.setGravity(Gravity.BOTTOM, 0, 50);
        result.setDuration(duration);
        result.setView(v);
        return result;

    }

    private static Toast makeToast(Context context, int resId, int duration)
            throws Resources.NotFoundException
    {
        return makeToast(context, context.getResources().getText(resId), duration);
    }

    public static final void showToast(Context context, String text, int delay)
    {
        makeToast(context, text, delay).show();
    }

    public static final void showToast(Context context, int textResource, int delay)
    {
        makeToast(context, textResource, delay).show();

    }
}
