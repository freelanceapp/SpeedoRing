package com.speedoring.ui.vendor.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.speedoring.R;
import com.speedoring.constant.Constant;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.ui.user.activity.UserHomeActivity;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.AppPreference;
import com.speedoring.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    private String strPhone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.imgLogin).setOnClickListener(this);
        findViewById(R.id.imgCreatePassword).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                Intent intent = new Intent(mContext, UserHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.imgLogin:
                confirmMobile();
                break;
            case R.id.imgCreatePassword:
                changePasswordApi();
                break;
        }
    }

    private void confirmMobile() {
        strPhone = ((EditText) findViewById(R.id.edtPhone)).getText().toString();

        if (strPhone.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "Phone No. field can't be empty...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.forgotPassword(strPhone), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                Alerts.show(mContext, jsonObject.getString("message"));
                                AppPreference.setBooleanPreference(mContext, Constant.IS_FORGOT_PASSWORD, true);
                                Intent intent = new Intent(mContext, OtpVerificationActivity.class);
                                intent.putExtra("phone", strPhone);
                                startActivityForResult(intent, 1);
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
                        Alerts.show(findViewById(R.id.rlContainer), error);
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                AppPreference.setBooleanPreference(mContext, Constant.IS_FORGOT_PASSWORD, false);
                findViewById(R.id.rlMobile).setVisibility(View.GONE);
                findViewById(R.id.rlPassword).setVisibility(View.VISIBLE);
            }
            /*if (resultCode == Activity.RESULT_CANCELED) {

            }*/
        }
    }

    private void changePasswordApi() {
        String strNewPass = ((EditText) findViewById(R.id.edtNewPassword)).getText().toString();
        String strConfPass = ((EditText) findViewById(R.id.edtConfirmPassword)).getText().toString();

        if (strNewPass.isEmpty()) {
            Alerts.show(mContext, "Enter new password...!!!");
        } else if (strNewPass.length() < 6) {
            Alerts.show(mContext, "Password length must be greater than 5...!!!");
        } else if (!strConfPass.equals(strNewPass)) {
            Alerts.show(mContext, "Password does not match...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.changePassword(
                        strPhone, strNewPass), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                Alerts.show(mContext, jsonObject.getString("message"));
                                Alerts.show(mContext, "Login to continue...!!!");
                                finish();
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
                        Alerts.show(mContext, "Server error...!!!");
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }
}
