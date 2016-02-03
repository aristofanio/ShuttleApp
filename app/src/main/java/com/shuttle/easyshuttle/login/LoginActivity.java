package com.shuttle.easyshuttle.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.shuttle.AppController;
import com.shuttle.ApplicationUrls;
import com.shuttle.WebResponseVo;
import com.shuttle.easyshuttle.R;
import com.shuttle.easyshuttle.login.model.LoginResponseVo;
import com.shuttle.network.GsonPostRequest;
import com.shuttle.network.NetworkApiRequest;
import com.shuttle.network.NetworkStatus;
import com.shuttle.utility.ToastUtility;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Deepak Pawar on 1/23/2016.
 */
public class LoginActivity extends BaseLoginSignUpActivity implements View.OnClickListener
{
    private EditText _mobileNoETxt, _passwordETxt;

    @Override
    protected int getContentViewID()
    {
        return R.layout.activity_login;
    }

    @Override
    protected int getActivityContainerViewGroupId()
    {
        return R.id.loginFormLayout;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHeaderTitle(getResources().getString(R.string.login));
        initView();
    }

    private void initView()
    {
        _mobileNoETxt = (EditText) findViewById(R.id.mobileNoETxt);
        _passwordETxt = (EditText) findViewById(R.id.passwordETxt);
        TextView forgotPasswordTxt = (TextView) findViewById(R.id.forgotPasswordTxt);
        TextView signUpTxt = (TextView) findViewById(R.id.signUpTxt);
        Button submittLoginDetails = (Button) findViewById(R.id.signInBtn);
        submittLoginDetails.setOnClickListener(this);
        forgotPasswordTxt.setOnClickListener(this);
        signUpTxt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.signInBtn:

                if (!isNetworkConnected())
                {
                    ToastUtility.showToast(this, R.string.networkUnavailable, Toast.LENGTH_SHORT);
                    return;
                }
                loginServiceCall();
                break;

            case R.id.signUpTxt:

                /*******************Move to signup Screen*****/
                Intent signupIntent = new Intent(this, SignUpActivity.class);
                signupIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signupIntent);

                break;

            case R.id.forgotPasswordTxt:

                break;
        }
    }

    private void loginServiceCall()
    {
        JSONObject loginJson = new JSONObject();
        try
        {
            loginJson.put("userName", _mobileNoETxt.getText().toString());
            loginJson.put("password", _passwordETxt.getText().toString());
            showStatus(NetworkStatus.STATUS_LOADING, 0);

            GsonPostRequest<LoginResponseVo> gsonLoginPostRequest = NetworkApiRequest.getLoginResponse
                    (ApplicationUrls.getLoginUrl(),
                            new Response.Listener<WebResponseVo>()
                            {
                                @Override
                                public void onResponse(WebResponseVo responseVo)
                                {
                                    showStatus(NetworkStatus.STATUS_SUCCESS, 0);
                                    onNetworkObjectResponse(responseVo);
                                }
                            }
                            ,
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error)
                                {
                                    // @Todo Deal with the error here
                                    showStatus(NetworkStatus.STATUS_SUCCESS, 0);
                                    handleResponseError(error);
                                }
                            }, loginJson
                    );

            AppController.getInstance().addToRequestQueue(gsonLoginPostRequest);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void onNetworkObjectResponse(WebResponseVo responseVo)
    {
        if (responseVo instanceof LoginResponseVo)
        {
            validateLoginResponse((LoginResponseVo) responseVo);
        }
    }

    private void validateLoginResponse(LoginResponseVo loginResponse)
    {
        if (loginResponse.isValidJson())
        {

        }
        else
        {
           ToastUtility.showToast(this,"Incorrect Response",Toast.LENGTH_SHORT);
        }
    }


}
