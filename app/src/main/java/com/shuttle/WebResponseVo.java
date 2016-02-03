package com.shuttle;

import com.google.gson.JsonObject;

/**
 * Created by Deepak Pawar on 1/26/2016.
 */
public abstract class WebResponseVo
{

    protected String getStringValueFromKey(JsonObject responseJson, String key)
    {
        return responseJson.get(key).getAsString();
    }
}
