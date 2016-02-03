package com.shuttle.network;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shuttle.WebResponseVo;
import com.shuttle.easyshuttle.login.model.LoginResponseVo;
import com.shuttle.easyshuttle.login.model.SignupResponseVo;

import org.json.JSONObject;

/**
 * Created by Deepak Pawar on 1/26/2016.
 */
public class NetworkApiRequest
{
    private static Gson loginGson = new GsonBuilder()
            .registerTypeAdapter(LoginResponseVo.class, new LoginResponseVo())
            .create();

    public static GsonPostRequest getLoginResponse(String url, Response.Listener<WebResponseVo> listener, Response.ErrorListener errorListener, JSONObject postJson)
    {
        GsonPostRequest gsonPostRequest = new GsonPostRequest<>
                (
                        url,
                        postJson.toString(),
                        new TypeToken<LoginResponseVo>()
                        {
                        }.getType(),
                        loginGson,
                        listener,
                        errorListener
                );

        gsonPostRequest.setShouldCache(false);

        return gsonPostRequest;
    }

    /***************
     * SignUp request
     ************/
    private static Gson signUpGson = new GsonBuilder()
            .registerTypeAdapter(SignupResponseVo.class, new SignupResponseVo())
            .create();

    public static GsonPostRequest getSignUpResponse(String url, Response.Listener<WebResponseVo> listener, Response.ErrorListener errorListener, JSONObject postJson)
    {
        GsonPostRequest gsonPostRequest = new GsonPostRequest<>
                (
                        url,
                        postJson.toString(),
                        new TypeToken<SignupResponseVo>()
                        {
                        }.getType(),
                        signUpGson,
                        listener,
                        errorListener
                );

        gsonPostRequest.setShouldCache(false);
        return gsonPostRequest;
    }

}
