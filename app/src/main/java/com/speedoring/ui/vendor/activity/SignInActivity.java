package com.speedoring.ui.vendor.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.speedoring.R;
import com.speedoring.constant.Constant;
import com.speedoring.modal.User;
import com.speedoring.modal.vendor.login_data.VendorLoginMainModal;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.ui.user.activity.UserHomeActivity;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.AppPreference;
import com.speedoring.utils.BaseActivity;

import retrofit2.Response;

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.imgLogin).setOnClickListener(this);
        findViewById(R.id.txtSignUp).setOnClickListener(this);
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
                loginApi();
                break;
            case R.id.txtSignUp:
                startActivity(new Intent(mContext, SignUpActivity.class));
                break;
        }
    }

    private void loginApi() {
        final String strPhone = ((EditText) findViewById(R.id.edtPhone)).getText().toString();
        String strPassword = ((EditText) findViewById(R.id.edtPassword)).getText().toString();

        if (strPhone.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "Phone No. field can't be empty...!!!");
        } else if (strPhone.length() < 10) {
            Alerts.show(findViewById(R.id.rlContainer), "Enter valid Phone No...!!!");
        } else if (strPassword.isEmpty()) {
            Alerts.show(findViewById(R.id.rlContainer), "Password can't be empty...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getVendorLoginData(new Dialog(mContext), retrofitApiClient.vendorLogin(strPhone, strPassword), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        VendorLoginMainModal loginMainModal = (VendorLoginMainModal) result.body();
                        if (loginMainModal == null)
                            return;
                        if (!loginMainModal.getError()) {
                            Gson gson = new GsonBuilder().setLenient().create();
                            String data = gson.toJson(loginMainModal.getVendorinfo());
                            AppPreference.setBooleanPreference(mContext, Constant.IS_LOGIN, true);
                            AppPreference.setStringPreference(mContext, Constant.VENDOR_DATA, data);
                            User.setUser(loginMainModal.getVendorinfo());
                            Intent intent = new Intent(mContext, VendorHomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            if (loginMainModal.getMessage().equalsIgnoreCase("User is not verified")) {
                                Alerts.show(mContext, loginMainModal.getMessage());
                                Alerts.show(mContext, "Please verify your phone number");
                                Intent intent = new Intent(mContext, OtpVerificationActivity.class);
                                intent.putExtra("phone", strPhone);
                                startActivity(intent);
                            }else {
                                Alerts.show(findViewById(R.id.rlContainer), loginMainModal.getMessage());
                            }
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
