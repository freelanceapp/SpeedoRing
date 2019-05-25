package com.speedoring.ui.vendor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.speedoring.R;
import com.speedoring.ui.user.activity.UserHomeActivity;
import com.speedoring.utils.BaseActivity;

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
                startActivity(new Intent(mContext, UserHomeActivity.class));
                break;
            case R.id.txtSignIn:
                finish();
                break;
        }
    }
}
