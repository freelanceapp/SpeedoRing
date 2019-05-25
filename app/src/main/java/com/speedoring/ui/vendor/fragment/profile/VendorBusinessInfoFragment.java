package com.speedoring.ui.vendor.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedoring.R;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

public class VendorBusinessInfoFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.vendor_fragment_business_info, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        return rootView;
    }

    private void initPager() {

    }

    @Override
    public void onClick(View v) {

    }
}