package com.speedoring.ui.user.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    private GridLayoutManager gridLayoutManager;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private static int TOTAL_PAGES;
    private boolean isLoading = false;
    private boolean isLastPage = false;

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
        gridLayoutManager = new GridLayoutManager(mContext, 2);
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
        }
    }
}
