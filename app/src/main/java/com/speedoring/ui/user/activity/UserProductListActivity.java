package com.speedoring.ui.user.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.speedoring.R;
import com.speedoring.adapter.ServiceListAdapter;
import com.speedoring.modal.service_list.ServiceList;
import com.speedoring.modal.service_list.ServiceListMainModal;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class UserProductListActivity extends BaseActivity implements View.OnClickListener {

    private ServiceListAdapter serviceListAdapter;
    private List<ServiceList> serviceLists = new ArrayList<>();
    private String productCategoryId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_product_list);

        init();
    }

    private void init() {
        productCategoryId = getIntent().getStringExtra("category_id");
        findViewById(R.id.imgBack).setOnClickListener(this);

        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        serviceListAdapter = new ServiceListAdapter(serviceLists, mContext, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerViewProducts.setLayoutManager(gridLayoutManager);
        recyclerViewProducts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducts.setAdapter(serviceListAdapter);
        serviceListAdapter.notifyDataSetChanged();

        serviceListApi();
    }

    private void serviceListApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getServiceList(new Dialog(mContext), retrofitApiClient.serviceList(productCategoryId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ServiceListMainModal listMainModal = (ServiceListMainModal) result.body();
                    if (listMainModal != null) {
                        serviceLists.clear();
                        if (!listMainModal.getResult()) {
                            if (listMainModal.getServices().size() > 0) {
                                serviceLists.addAll(listMainModal.getServices());
                            }
                        } else {
                            Alerts.show(mContext, listMainModal.getMessage());
                        }
                    }
                    serviceListAdapter.notifyDataSetChanged();
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
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }
}
