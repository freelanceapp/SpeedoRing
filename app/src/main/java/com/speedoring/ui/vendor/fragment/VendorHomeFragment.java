package com.speedoring.ui.vendor.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedoring.R;
import com.speedoring.adapter.BannerPagerAdapter;
import com.speedoring.adapter.TodaysOfferAdapter;
import com.speedoring.modal.banner_model.BannerDatum;
import com.speedoring.modal.banner_model.BannerModel;
import com.speedoring.modal.coupon_model.CouponDatum;
import com.speedoring.modal.coupon_model.CouponModel;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class VendorHomeFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    private Handler imageHandler;
    private Runnable imageRunnable;
    private ViewPager pagerSuccess;
    private BannerPagerAdapter adapter;
    private List<BannerDatum> successImagesList = new ArrayList<>();

    private TodaysOfferAdapter todaysOfferAdapter;
    private List<CouponDatum> stylesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.vendor_fragment_home, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        initPager();
        init();
        return rootView;
    }

    private void initPager() {
        pagerSuccess = rootView.findViewById(R.id.pagerSuccess);
        adapter = new BannerPagerAdapter(mContext, successImagesList, this);
        imagesApi();
        imageHandler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                marriageSlide();
            }
        };
        imageHandler.postDelayed(imageRunnable, 3000);
    }

    public void marriageSlide() {
        if (pagerSuccess == null)
            return;
        int successPos = pagerSuccess.getCurrentItem();
        successPos++;
        if (successPos != successImagesList.size()) {
            pagerSuccess.setCurrentItem(successPos);
            imageHandler.postDelayed(imageRunnable, 3000);
        } else {
            pagerSuccess.setCurrentItem(0);
            imageHandler.postDelayed(imageRunnable, 3000);
        }
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

    private void imagesApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getBanner(new Dialog(mContext), retrofitApiClient.getBanner(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    BannerModel imagesModal = (BannerModel) result.body();
                    successImagesList.clear();
                    if (imagesModal == null)
                        return;
                    successImagesList.addAll(imagesModal.getData());
                    pagerSuccess.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void getCoupon1() {
        if (cd.isNetworkAvailable()) {
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
        } else {
            cd.show(mContext);
        }
    }

    @Override
    public void onClick(View v) {

    }
}