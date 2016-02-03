package com.shuttle;

/**
 * Created by Deepak Pawar on 1/19/2016.
 */
public class ApplicationUrls
{
    public static String APP_BASE_URL = "http://162.251.83.28:8080/pooleveryday/rest/";
    public static String LOGIN_URL = "l/login";
    public static String SIGNUP_URL = "shuttleuser/savenewuser";

    public static String getLoginUrl()
    {
        return String.format("%s%s", APP_BASE_URL, LOGIN_URL);
    }

    public static String getSignUpUrl()
    {
        return String.format("%s%s", APP_BASE_URL, SIGNUP_URL);
    }


}
/**************************************************************************/
//    private void applyFontToMenuItem(MenuItem mi) {
//        Typeface font = Typeface.createFromAsset(getAssets(), "ds_digi_b.TTF");
//        SpannableString mNewTitle = new SpannableString(mi.getTitle());
//        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        mi.setTitle(mNewTitle);
//    }
//
//    navView = (NavigationView) findViewById(R.id.navView);
//    Menu m = navView.getMenu();
//    for (int i=0;i<m.size();i++) {
//    MenuItem mi = m.getItem(i);
//
//    //for aapplying a font to subMenu ...
//    SubMenu subMenu = mi.getSubMenu();
//    if (subMenu!=null && subMenu.size() >0 ) {
//        for (int j=0; j <subMenu.size();j++) {
//            MenuItem subMenuItem = subMenu.getItem(j);
//            applyFontToMenuItem(subMenuItem);
//        }
//    }
//
//    //the method we have create in activity
//    applyFontToMenuItem(mi);
//}
