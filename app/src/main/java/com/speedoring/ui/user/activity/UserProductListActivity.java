package com.speedoring.ui.user.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.adapter.ProductListPaginationAdapter;
import com.speedoring.modal.user.product_list_home.HomeProductListMainModal;
import com.speedoring.pagination_listener.PaginationScrollListener;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseActivity;

import retrofit2.Response;

public class UserProductListActivity extends BaseActivity implements View.OnClickListener {

    private String productCategoryId = "", subCategoryId = "", categoryName = "";

    private ProductListPaginationAdapter adapter;
    private LinearLayoutManager gridLayoutManager;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private static int TOTAL_PAGES;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    private Dialog dialogCall, dialogAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_product_list);

        init();
    }

    private void init() {
        subCategoryId = getIntent().getStringExtra("sub_category_id");
        productCategoryId = getIntent().getStringExtra("category_id");
        categoryName = getIntent().getStringExtra("category_name");
        ((TextView) findViewById(R.id.txtTitle)).setText(categoryName);
        findViewById(R.id.imgBack).setOnClickListener(this);

        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        adapter = new ProductListPaginationAdapter(mContext, this);
        gridLayoutManager = new LinearLayoutManager(mContext);
        recyclerViewProducts.setLayoutManager(gridLayoutManager);
        recyclerViewProducts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerViewProducts.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        getHomeProductApi();
    }

    private void getHomeProductApi() {
        currentPage = PAGE_START;
        if (cd.isNetworkAvailable()) {
            RetrofitService.getHomeProductList(new Dialog(mContext), retrofitApiClient.productList(
                    productCategoryId, subCategoryId, "1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    HomeProductListMainModal mainModal = (HomeProductListMainModal) result.body();
                    if (mainModal == null)
                        return;
                    if (!mainModal.getError()) {
                        TOTAL_PAGES = mainModal.getPageCount();
                        adapter.addAll(mainModal.getProductListing());
                        if (currentPage < TOTAL_PAGES) {
                            adapter.addLoadingFooter();
                            isLastPage = false;
                        } else if (currentPage == TOTAL_PAGES) {
                            isLastPage = true;
                        }
                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
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

    private void loadNextPage() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getHomeProductList(new Dialog(mContext), retrofitApiClient.productList(
                    productCategoryId, subCategoryId, currentPage + ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    HomeProductListMainModal mainModal = (HomeProductListMainModal) result.body();
                    if (mainModal == null)
                        return;
                    if (!mainModal.getError()) {
                        TOTAL_PAGES = mainModal.getPageCount();
                        adapter.removeLoadingFooter();
                        isLoading = false;
                        adapter.addAll(mainModal.getProductListing());
                        if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                        else isLastPage = true;
                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
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
            case R.id.imgCall:
                int pos = (int) v.getTag();
                String mobileA = adapter.getProductList().get(pos).getVendorMobileOne();
                String mobileB = adapter.getProductList().get(pos).getVendorMobileTwo();
                String LandlineA = adapter.getProductList().get(pos).getVendorMobileOne();
                String LandlineB = adapter.getProductList().get(pos).getVendorMobileTwo();
                String email = adapter.getProductList().get(pos).getVendorEmail();
                callDialog(mobileA, mobileB, LandlineA, LandlineB, email);
                break;
            case R.id.imgAddress:
                int posA = (int) v.getTag();
                String state = adapter.getProductList().get(posA).getVendorState();
                String city = adapter.getProductList().get(posA).getVendorCity();
                String address = adapter.getProductList().get(posA).getVendorAddress();
                addressDialog(state, city, address);
                break;
        }
    }

    private void callDialog(String mobileA, String mobileB, String LandlineA,
                            String LandlineB, String email) {
        dialogCall = new Dialog(mContext);
        dialogCall.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCall.setContentView(R.layout.dialog_call);

        dialogCall.setCanceledOnTouchOutside(true);
        dialogCall.setCancelable(true);
        if (dialogCall.getWindow() != null)
            dialogCall.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ((TextView) dialogCall.findViewById(R.id.txtMobileA)).setText(mobileA);
        ((TextView) dialogCall.findViewById(R.id.txtMobileB)).setText(mobileB);
        ((TextView) dialogCall.findViewById(R.id.txtLandlineA)).setText(LandlineA);
        ((TextView) dialogCall.findViewById(R.id.txtLandlineB)).setText(LandlineB);
        ((TextView) dialogCall.findViewById(R.id.txtEmailAddress)).setText(email);

        dialogCall.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCall.dismiss();
            }
        });

        Window window = dialogCall.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialogCall.show();
    }

    private void addressDialog(String state, String city, String address) {
        dialogAddress = new Dialog(mContext);
        dialogAddress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddress.setContentView(R.layout.dialog_address);

        dialogAddress.setCanceledOnTouchOutside(true);
        dialogAddress.setCancelable(true);
        if (dialogAddress.getWindow() != null)
            dialogAddress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ((TextView) dialogAddress.findViewById(R.id.txtState)).setText(state);
        ((TextView) dialogAddress.findViewById(R.id.txtCity)).setText(city);
        ((TextView) dialogAddress.findViewById(R.id.txtAddress)).setText(address);

        dialogAddress.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddress.dismiss();
            }
        });

        Window window = dialogAddress.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialogAddress.show();
    }

}
