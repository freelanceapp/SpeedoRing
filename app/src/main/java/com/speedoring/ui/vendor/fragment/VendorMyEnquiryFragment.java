package com.speedoring.ui.vendor.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedoring.R;
import com.speedoring.adapter.TodaysOfferAdapter;
import com.speedoring.modal.coupon_model.CouponDatum;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

public class VendorMyEnquiryFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    private TodaysOfferAdapter todaysOfferAdapter;
    private List<CouponDatum> stylesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.vendor_fragment_listing, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init();
        return rootView;
    }

    private void init() {
        RecyclerView recyclerViewEnquiry = rootView.findViewById(R.id.recyclerViewEnquiry);
        todaysOfferAdapter = new TodaysOfferAdapter(stylesList, mContext, this);
        recyclerViewEnquiry.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerViewEnquiry.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEnquiry.setAdapter(todaysOfferAdapter);
        todaysOfferAdapter.notifyDataSetChanged();
        getCoupon1();
    }

    private void getCoupon1() {
        if (cd.isNetworkAvailable()) {
/*
            RetrofitService.getCoupon(new Dialog(mContext), retrofitApiClient.getCoupon(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    CouponModel mainModal = (CouponModel) result.body();
                    if (mainModal == null)
                        return;

                    stylesList.addAll(mainModal.getData());
                    todaysOfferAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
*/
        } else {
            cd.show(mContext);
        }
    }

    @Override
    public void onClick(View v) {

    }
}