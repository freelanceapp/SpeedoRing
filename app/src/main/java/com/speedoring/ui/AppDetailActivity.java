package com.speedoring.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.utils.BaseActivity;

public class AppDetailActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);

        findViewById(R.id.imgBack).setOnClickListener(this);

        String title = getIntent().getStringExtra("title");
        String data = getIntent().getStringExtra("data");

        ((TextView) findViewById(R.id.txtData)).setText(data);
        ((TextView) findViewById(R.id.txtTitleDetail)).setText(title);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
