package com.speedoring.ui.vendor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.speedoring.R;
import com.speedoring.utils.BaseActivity;

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
                finish();
                break;
            case R.id.imgLogin:
                Intent intent = new Intent(mContext, VendorHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.txtSignUp:
                startActivity(new Intent(mContext, SignUpActivity.class));
                break;
        }
    }
}
