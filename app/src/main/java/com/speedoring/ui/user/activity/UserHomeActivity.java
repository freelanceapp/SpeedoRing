package com.speedoring.ui.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.constant.Constant;
import com.speedoring.interface_update_data.ViewMoreInterface;
import com.speedoring.ui.user.fragment.HomeFragment;
import com.speedoring.ui.user.fragment.UserAllCategoryFragment;
import com.speedoring.ui.vendor.activity.SignInActivity;
import com.speedoring.utils.BaseActivity;
import com.speedoring.utils.FragmentUtils;

public class UserHomeActivity extends BaseActivity implements View.OnClickListener {

    private TextView txtTitle;
    public static FragmentUtils fragmentUtils;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_home);

        initNavigation();
    }

    private void initNavigation() {

        txtTitle = findViewById(R.id.txtTitle);
        findViewById(R.id.llHome).setOnClickListener(this);
        findViewById(R.id.llCategory).setOnClickListener(this);
        findViewById(R.id.llLogin).setOnClickListener(this);
        initFragment();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);
        txtTitle.setText("Home");

        homeFragment = new HomeFragment();
        homeFragment.clickedInterface(new ViewMoreInterface() {
            @Override
            public void isClicked(Boolean isClick) {
                if (isClick) {
                    findViewById(R.id.llCategory).performClick();
                }
            }
        });
        fragmentUtils.replaceFragment(homeFragment, Constant.HomeFragment, R.id.frameLayout);
        findViewById(R.id.llHome).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        findViewById(R.id.llCategory).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        findViewById(R.id.llLogin).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llHome:
                txtTitle.setText("Home");
                fragmentUtils.replaceFragment(homeFragment, Constant.HomeFragment, R.id.frameLayout);
                findViewById(R.id.llHome).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                findViewById(R.id.llCategory).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                findViewById(R.id.llLogin).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.llCategory:
                txtTitle.setText("All ProductCategoryList");
                fragmentUtils.replaceFragment(new UserAllCategoryFragment(), Constant.UserAllCategoryFragment, R.id.frameLayout);
                findViewById(R.id.llCategory).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                findViewById(R.id.llHome).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                findViewById(R.id.llLogin).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.llLogin:
                startActivity(new Intent(mContext, SignInActivity.class));
                break;
        }
    }
}
