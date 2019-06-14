package com.speedoring.ui.vendor.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedoring.R;
import com.speedoring.adapter.VendorListingPaginationAdapter;
import com.speedoring.modal.User;
import com.speedoring.modal.vendor.vendor_product_list.VendorProductListMainModal;
import com.speedoring.pagination_listener.PaginationScrollListener;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

import retrofit2.Response;

public class VendorListingFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private String vendorId = User.getUser().getVendorId();

    private VendorListingPaginationAdapter adapter;
    private LinearLayoutManager gridLayoutManager;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private static int TOTAL_PAGES;
    private boolean isLoading = false;
    private boolean isLastPage = false;

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
        adapter = new VendorListingPaginationAdapter(mContext, this);
        adapter.getProductList().clear();
        gridLayoutManager = new LinearLayoutManager(mContext);
        recyclerViewEnquiry.setLayoutManager(gridLayoutManager);
        recyclerViewEnquiry.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEnquiry.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerViewEnquiry.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
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
            RetrofitService.getVendorProductList(new Dialog(mContext), retrofitApiClient.vendorProductList(vendorId, "1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VendorProductListMainModal mainModal = (VendorProductListMainModal) result.body();
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
            RetrofitService.getVendorProductList(new Dialog(mContext), retrofitApiClient.vendorProductList("17",
                    currentPage + ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VendorProductListMainModal mainModal = (VendorProductListMainModal) result.body();
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

    }
}