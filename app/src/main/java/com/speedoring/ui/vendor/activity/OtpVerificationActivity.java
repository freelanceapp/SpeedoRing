package com.speedoring.ui.vendor.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.constant.Constant;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.AppPreference;
import com.speedoring.utils.BaseActivity;
import com.speedoring.utils.pinview.Pinview;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class OtpVerificationActivity extends BaseActivity implements View.OnClickListener {

    private String strPhoneNo = "";
    private Pinview pinview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_activity_otp_verification);

        init();
    }

    private void init() {
        pinview = findViewById(R.id.pinview);
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.btnVerify).setOnClickListener(this);
        strPhoneNo = getIntent().getStringExtra("phone");
        //strPhoneNo = "9545124545";
        ((TextView) findViewById(R.id.txtPhoneNo)).setText("+91-" + strPhoneNo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnVerify:
                otpVerifyApi();
                break;
        }
    }

    private void otpVerifyApi() {
        String strOtp = pinview.getValue();
        if (strOtp.isEmpty()) {
            Alerts.show(mContext, "OTP number can't be empty...!!!");
        } else if (strOtp.length() < 6) {
            Alerts.show(mContext, "Enter valid OTP number...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.otpVerification(strPhoneNo, strOtp), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                if (AppPreference.getBooleanPreference(mContext, Constant.IS_FORGOT_PASSWORD)) {
                                    Intent returnIntent = new Intent();
                                    //returnIntent.putExtra("result", result);
                                    setResult(Activity.RESULT_OK, returnIntent);
                                    finish();
                                } else {
                                    Alerts.show(mContext, jsonObject.getString("message"));
                                    Alerts.show(mContext, "Login to continue...");
                                    Intent intent = new Intent(mContext, SignInActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                Alerts.show(mContext, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }
}
