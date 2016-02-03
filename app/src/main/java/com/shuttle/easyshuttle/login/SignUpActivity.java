package com.shuttle.easyshuttle.login;

import android.os.Bundle;
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
import com.shuttle.easyshuttle.login.model.SignupResponseVo;
import com.shuttle.network.GsonPostRequest;
import com.shuttle.network.NetworkApiRequest;
import com.shuttle.network.NetworkStatus;
import com.shuttle.utility.ToastUtility;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Deepak Pawar on 1/23/2016.
 */
public class SignUpActivity extends BaseLoginSignUpActivity implements View.OnClickListener
{
    private EditText firstNameETxt, lastNameETxt, emailIdETxt, nickNameETxt, mobileNoETxt, companyNameETxt, otpETxt, passwordETxt;

    @Override
    protected int getContentViewID()
    {
        return R.layout.activity_signup;
    }

    @Override
    protected int getActivityContainerViewGroupId()
    {
        return R.id.signupFormLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHeaderTitle(getResources().getString(R.string.signup));
        initViews();
    }

    private void initViews()
    {
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        TextView alreadyMemberTxt = (TextView) findViewById(R.id.alreadyMemberTxt);
        firstNameETxt = (EditText) findViewById(R.id.firstNameETxt);
        lastNameETxt = (EditText) findViewById(R.id.lastNameETxt);
        emailIdETxt = (EditText) findViewById(R.id.emailIdETxt);
        nickNameETxt = (EditText) findViewById(R.id.nickNameETxt);
        mobileNoETxt = (EditText) findViewById(R.id.mobileNoETxt);
        companyNameETxt = (EditText) findViewById(R.id.companyNameETxt);
        passwordETxt = (EditText) findViewById(R.id.passwordETxt);
        otpETxt = (EditText) findViewById(R.id.otpETxt);

        signUpBtn.setOnClickListener(this);
        alreadyMemberTxt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.signUpBtn:

                if (!isNetworkConnected())
                {
                    showStatus(NetworkStatus.STATUS_ERROR, 0);
                    return;
                }
                makeSignUpNetworkRequest();
                break;

            case R.id.alreadyMemberTxt:

                break;
        }
    }

    private void makeSignUpNetworkRequest()
    {
        JSONObject signUpJson = new JSONObject();
        try
        {
            signUpJson.put("firstName", firstNameETxt.getText().toString());
            signUpJson.put("lastName", lastNameETxt.getText().toString());
            signUpJson.put("email", emailIdETxt.getText().toString());
            signUpJson.put("mobile", mobileNoETxt.getText().toString());
            signUpJson.put("password", passwordETxt.getText().toString());
            signUpJson.put("userType", "P");
            signUpJson.put("active", false);

            showStatus(NetworkStatus.STATUS_LOADING, 0);

            GsonPostRequest<SignupResponseVo> gsonSignUpPostRequest = NetworkApiRequest.getSignUpResponse
                    (ApplicationUrls.getSignUpUrl(),
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
                                    // ToastUtility.showToast(SignUpActivity.this, error.getCause().getMessage(), Toast.LENGTH_SHORT).show();
                                    showStatus(NetworkStatus.STATUS_SUCCESS, 0);
                                    handleResponseError(error);
                                }
                            }, signUpJson
                    );

            AppController.getInstance().addToRequestQueue(gsonSignUpPostRequest);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void onNetworkObjectResponse(WebResponseVo responseVo)
    {
        if (responseVo instanceof SignupResponseVo)
        {
            validateSignUpResponse((SignupResponseVo) responseVo);
        }
    }

    private void validateSignUpResponse(SignupResponseVo loginResponse)
    {
        if (loginResponse.isValidJson())
        {

        }
        else
        {
            ToastUtility.showToast(this, "Incorrect Response", Toast.LENGTH_SHORT);
        }
    }
}
