package com.speedoring.ui.vendor.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.speedoring.R;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.imgSignUp).setOnClickListener(this);
        findViewById(R.id.txtSignIn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgSignUp:
                signUpApi();
                break;
            case R.id.txtSignIn:
                finish();
                break;
        }
    }

    private void signUpApi() {
        String strFirstName = ((EditText) findViewById(R.id.edtFirstName)).getText().toString();
        String strLastName = ((EditText) findViewById(R.id.edtLastName)).getText().toString();
        String strAddress = ((EditText) findViewById(R.id.edtAddress)).getText().toString();
        final String strPhone = ((EditText) findViewById(R.id.edtPhone)).getText().toString();
        String strCity = ((EditText) findViewById(R.id.edtCity)).getText().toString();
        String strPincode = ((EditText) findViewById(R.id.edtPincode)).getText().toString();
        String strState = ((EditText) findViewById(R.id.edtState)).getText().toString();
        String strAbout = ((EditText) findViewById(R.id.edtAbout)).getText().toString();
        String strPassword = ((EditText) findViewById(R.id.edtPassword)).getText().toString();

        if (strFirstName.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "First name can't be empty...!!!");
        } else if (strLastName.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "Last name can't be empty...!!!");
        } else if (strAddress.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "Address can't be empty...!!!");
        } else if (strPhone.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "Phone no can't be empty...!!!");
        } else if (strPhone.length() < 10) {
            Alerts.show(findViewById(R.id.rlContainer), "Enter valid Phone no...!!!");
        } else if (strCity.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "City can't be empty...!!!");
        } else if (strPincode.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "Pincode can't be empty...!!!");
        } else if (strState.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "State can't be empty...!!!");
        } else if (strPassword.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "Create your password...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.vendorSignUp(strFirstName,
                        strLastName, strPhone, strPassword, strAddress, strCity, strState, strPincode,
                        strAbout), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        if (responseBody == null)
                            return;
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                Alerts.show(findViewById(R.id.rlContainer), jsonObject.getString("message"));
                                Alerts.show(mContext, jsonObject.getString("message"));
                                Intent intent = new Intent(mContext, OtpVerificationActivity.class);
                                intent.putExtra("phone", strPhone);
                                startActivity(intent);
                                //finish();
                            } else {
                                Alerts.show(findViewById(R.id.rlContainer), jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(findViewById(R.id.rlContainer), error);
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }
}
