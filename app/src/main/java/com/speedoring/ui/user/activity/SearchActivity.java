package com.speedoring.ui.user.activity;

import android.os.Bundle;
import android.view.View;

import com.speedoring.R;
import com.speedoring.utils.BaseActivity;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_search);
        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }
}