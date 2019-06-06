package com.speedoring.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.speedoring.R;
import com.speedoring.retrofit_provider.RetrofitApiClient;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.RetrofitServiceInstaMojo;

public class BaseActivity extends AppCompatActivity {

    public RetrofitApiClient retrofitApiClient, retrofitApiClientInstaMojo;
    public ConnectionDetector cd;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        retrofitApiClientInstaMojo = RetrofitServiceInstaMojo.getRetrofit();
    }
}