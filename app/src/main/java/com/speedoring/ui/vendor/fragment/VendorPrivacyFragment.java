package com.speedoring.ui.vendor.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

import retrofit2.Response;

public class VendorPrivacyFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.vendor_fragment_privacy, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init();
        return rootView;
    }

    private void init() {
        Bundle bundle = getArguments();
        String strType = bundle.getString("type");
        ((TextView) rootView.findViewById(R.id.txtData)).setText(strType);
    }

    private void getEnquiryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getVendorEnquiryList(new Dialog(mContext), retrofitApiClient.vendorEnquiryList("",
                    "1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {

                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    @Override
    public void onClick(View v) {

    }
}