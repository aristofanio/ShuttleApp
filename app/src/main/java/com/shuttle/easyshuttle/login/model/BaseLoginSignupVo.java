package com.shuttle.easyshuttle.login.model;

import com.google.gson.JsonObject;
import com.shuttle.WebResponseVo;

/**
 * Created by Deepak Pawar on 1/26/2016.
 */
public class BaseLoginSignupVo extends WebResponseVo
{
    protected String firstName, lastName, email, mobile, newUser, password, active, userId, userType, validLogin, accountActive;
    protected boolean isValidJson;

    protected void parseCommonResponse(JsonObject loginJsonObject)
    {
        firstName = getStringValueFromKey(loginJsonObject, "firstName");
        lastName = getStringValueFromKey(loginJsonObject, "lastName");
        email = getStringValueFromKey(loginJsonObject, "email");
        mobile = getStringValueFromKey(loginJsonObject, "mobile");
        password = getStringValueFromKey(loginJsonObject, "password");
    }
}
