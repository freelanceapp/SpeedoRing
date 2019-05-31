package com.speedoring.ui.vendor.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedoring.R;
import com.speedoring.adapter.EnquiryListPaginationAdapter;
import com.speedoring.modal.User;
import com.speedoring.modal.vendor.enquiry_list.VendorEnquiryMainModal;
import com.speedoring.pagination_listener.PaginationScrollListener;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

import retrofit2.Response;

public class VendorNotificationFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private String vendorId = User.getUser().getVendorId();
    private EnquiryListPaginationAdapter adapter;
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

        rootView.findViewById(R.id.txtTitle).setVisibility(View.GONE);

        RecyclerView recyclerViewEnquiry = rootView.findViewById(R.id.recyclerViewEnquiry);
        adapter = new EnquiryListPaginationAdapter(mContext, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerViewEnquiry.setLayoutManager(linearLayoutManager);
        recyclerViewEnquiry.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEnquiry.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerViewEnquiry.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
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

        getEnquiryApi();
    }

    private void getEnquiryApi() {
        currentPage = PAGE_START;
        if (cd.isNetworkAvailable()) {
            RetrofitService.getVendorEnquiryList(new Dialog(mContext), retrofitApiClient.vendorEnquiryList(vendorId,
                    "1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VendorEnquiryMainModal mainModal = (VendorEnquiryMainModal) result.body();
                    if (mainModal == null)
                        return;
                    if (!mainModal.getError()) {
                        TOTAL_PAGES = mainModal.getPageCount();
                        adapter.addAll(mainModal.getEnquiry());
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
            RetrofitService.getVendorEnquiryList(new Dialog(mContext), retrofitApiClient.vendorEnquiryList(vendorId,
                    currentPage + ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VendorEnquiryMainModal mainModal = (VendorEnquiryMainModal) result.body();
                    if (mainModal == null)
                        return;
                    if (!mainModal.getError()) {
                        TOTAL_PAGES = mainModal.getPageCount();
                        adapter.removeLoadingFooter();
                        isLoading = false;
                        adapter.addAll(mainModal.getEnquiry());
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