package com.speedoring.ui.user.fragment;

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
import com.speedoring.adapter.HomeProductListAdapter;
import com.speedoring.adapter.ServiceCategoryAdapter;
import com.speedoring.modal.banner_model.BannerDatum;
import com.speedoring.modal.banner_model.BannerModel;
import com.speedoring.modal.product_list_home.HomeProductListMainModal;
import com.speedoring.modal.product_list_home.HomeProductListing;
import com.speedoring.modal.service_category.ServiceCategoryMainModal;
import com.speedoring.modal.service_category.ServicesCategory;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.ui.banner_fragment.CommonFragment;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    private Handler imageHandler;
    private Runnable imageRunnable;
    private ViewPager pagerSuccess;
    private List<CommonFragment> fragments = new ArrayList<>();
    private BannerPagerAdapter adapter;
    private List<BannerDatum> successImagesList = new ArrayList<>();
    private ServiceCategoryAdapter serviceCategoryAdapter;
    private List<ServicesCategory> servicesCategoryList = new ArrayList<>();

    private HomeProductListAdapter homeProductListAdapter;
    private List<HomeProductListing> homeProductListings = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.user_fragment_home, container, false);
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
        //imagesApi();
       /* imageHandler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                marriageSlide();
            }
        };
        imageHandler.postDelayed(imageRunnable, 3000);*/
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
        RecyclerView rvPopularVendor = rootView.findViewById(R.id.rvPopularVendor);
        serviceCategoryAdapter = new ServiceCategoryAdapter(servicesCategoryList, mContext, this);
        rvPopularVendor.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvPopularVendor.setItemAnimator(new DefaultItemAnimator());
        rvPopularVendor.setAdapter(serviceCategoryAdapter);
        serviceCategoryAdapter.notifyDataSetChanged();

        RecyclerView recyclerViewTopOffer = rootView.findViewById(R.id.recyclerViewTopOffer);
        homeProductListAdapter = new HomeProductListAdapter(homeProductListings, mContext, this);
        recyclerViewTopOffer.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerViewTopOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopOffer.setAdapter(homeProductListAdapter);
        homeProductListAdapter.notifyDataSetChanged();
        servicesCategoryApi();
        getHomeProductApi();
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

    private void servicesCategoryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getServiceCategory(new Dialog(mContext), retrofitApiClient.serviceCategory(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ServiceCategoryMainModal storeMainModel = (ServiceCategoryMainModal) result.body();
                    assert storeMainModel != null;
                    servicesCategoryList.clear();
                    if (storeMainModel.getServicesCategory().size() > 0) {
                        servicesCategoryList.addAll(storeMainModel.getServicesCategory());
                    }
                    serviceCategoryAdapter.notifyDataSetChanged();
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

    private void getHomeProductApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getHomeProductList(new Dialog(mContext), retrofitApiClient.productListHome(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    HomeProductListMainModal mainModal = (HomeProductListMainModal) result.body();
                    if (mainModal == null)
                        return;

                    homeProductListings.addAll(mainModal.getProductListing());
                    homeProductListAdapter.notifyDataSetChanged();
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