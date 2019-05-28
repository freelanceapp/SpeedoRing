package com.speedoring.ui.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.speedoring.R;
import com.speedoring.constant.Constant;
import com.speedoring.modal.User;
import com.speedoring.modal.vendor.login_data.Vendorinfo;
import com.speedoring.ui.vendor.activity.VendorHomeActivity;
import com.speedoring.utils.AppPreference;
import com.speedoring.utils.BaseActivity;

import io.fabric.sdk.android.Fabric;

public class SplashScreen extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handlerTimer();
    }

    private void handlerTimer() {
        Fabric.with(mContext, new Crashlytics());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppPreference.getBooleanPreference(mContext, Constant.IS_LOGIN)) {
                    Gson gson = new Gson();
                    String json = AppPreference.getStringPreference(mContext, Constant.VENDOR_DATA);
                    Vendorinfo loginUserModel = gson.fromJson(json, Vendorinfo.class);
                    User.setUser(loginUserModel);

                    Intent intent = new Intent(mContext, VendorHomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(mContext, UserHomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }

    @Override
    public void onClick(View v) {

    }
}
