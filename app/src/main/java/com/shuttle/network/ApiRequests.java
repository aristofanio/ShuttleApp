//package com.shuttle.network;
//
//import android.support.annotation.NonNull;
//
//import com.android.volley.Response;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.reflect.TypeToken;
//import com.shuttle.ApplicationUrls;
//import com.shuttle.easyshuttle.login.model.LoginRequestDeserializer;
//import com.shuttle.easyshuttle.login.model.LoginResponseVo;
//
//import java.util.ArrayList;
//
///**
// * Api requests
// */
//public class ApiRequests
//{
//    private static final Gson gson = new GsonBuilder()
//            .registerTypeAdapter(LoginResponseVo.class, new LoginRequestDeserializer())
//            .create();
//    /**
//     * Returns a dummy object
//     *
//     * @param listener is the listener for the correct answer
//     * @param errorListener is the listener for the error response
//     *
//     * @return {@link GsonGetRequest}
//     */
//    public static GsonGetRequest<LoginResponseVo> getLoginRequest
//    (
//            @NonNull final Response.Listener<LoginResponseVo> listener,
//            @NonNull final Response.ErrorListener errorListener
//    )
//    {
//        final String url = ApplicationUrls.LOGIN_URL + "/v2/55973508b0e9e4a71a02f05f";
//
//        return new GsonGetRequest<>
//                (
//                        url,
//                        new TypeToken<LoginResponseVo>() {}.getType(),
//                        gson,
//                        listener,
//                        errorListener
//                );
//    }
//
//    /**
//     * Returns a dummy object's array
//     *
//     * @param listener is the listener for the correct answer
//     * @param errorListener is the listener for the error response
//     *
//     * @return {@link GsonGetRequest}
//     */
//    public static GsonGetRequest<ArrayList<LoginResponseVo>> getLoginResponseVoArray
//    (
//            @NonNull final Response.Listener<ArrayList<LoginResponseVo>> listener,
//            @NonNull final Response.ErrorListener errorListener
//    )
//    {
//        final String url = ApplicationUrls.APP_BASE_URL + "/v2/5597d86a6344715505576725";
//
//        return new GsonGetRequest<>
//                (
//                        url,
//                        new TypeToken<ArrayList<LoginResponseVo>>() {}.getType(),
//                        gson,
//                        listener,
//                        errorListener
//                );
//    }
//
//
//    /**
//     * An example call (not used in this app example) to demonstrate how to do a Volley POST call
//     * and parse the response with Gson.
//     *
//     * @param listener is the listener for the success response
//     * @param errorListener is the listener for the error response
//     *
//     * @return {@link GsonPostRequest}
//     */
//    public static GsonPostRequest getLoginResponseVoArrayWithPost
//    (
//            @NonNull final Response.Listener<LoginResponseVo> listener,
//            @NonNull final Response.ErrorListener errorListener
//    )
//    {
//        final String url = ApplicationUrls.getLoginUrl();
//
//        final JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("name", "Ficus");
//        jsonObject.addProperty("surname", "Kirkpatrick");
//
//        final JsonArray squareGuys = new JsonArray();
//        final JsonObject dev1 = new JsonObject();
//        final JsonObject dev2 = new JsonObject();
//        dev1.addProperty("name", "Jake Wharton");
//        dev2.addProperty("name", "Jesse Wilson");
//        squareGuys.add(dev1);
//        squareGuys.add(dev2);
//
//        jsonObject.add("squareGuys", squareGuys);
//
//        final GsonPostRequest gsonPostRequest = new GsonPostRequest<>
//                (
//                        url,
//                        jsonObject.toString(),
//                        new TypeToken<LoginResponseVo>() {}.getType(),
//                        gson,
//                        listener,
//                        errorListener
//                );
//
//        gsonPostRequest.setShouldCache(false);
//
//        return gsonPostRequest;
//    }
//}
