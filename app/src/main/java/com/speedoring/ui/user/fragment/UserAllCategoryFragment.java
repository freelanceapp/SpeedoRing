package com.speedoring.ui.user.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.speedoring.R;
import com.speedoring.adapter.ProductCategoryAdapter;
import com.speedoring.adapter.ProductSubCategoryAdapter;
import com.speedoring.modal.product_category.ProductCategoryList;
import com.speedoring.modal.product_category.ProductCategoryMainModal;
import com.speedoring.modal.product_sub_category.ProductSubCategory;
import com.speedoring.modal.product_sub_category.ProductSubCategoryMainModal;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.ui.user.activity.UserProductListActivity;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class UserAllCategoryFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private Dialog dialogSubCategory;
    private String categoryId = "";

    private ProductCategoryAdapter categoryAdapter;
    private List<ProductCategoryList> productCategoryLists = new ArrayList<>();

    private ProductSubCategoryAdapter subCategoryAdapter;
    private List<ProductSubCategory> subCategoryLists = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.user_fragment_all_category, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init();
        return rootView;
    }

    private void init() {
        RecyclerView recyclerViewTopOffer = rootView.findViewById(R.id.recyclerViewTopOffer);
        categoryAdapter = new ProductCategoryAdapter(productCategoryLists, mContext, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerViewTopOffer.setLayoutManager(gridLayoutManager);
        recyclerViewTopOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopOffer.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        productCategoryApi();
    }

    private void productCategoryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getProductCategoryList(new Dialog(mContext), retrofitApiClient.productCategory("0"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ProductCategoryMainModal mainModal = (ProductCategoryMainModal) result.body();
                    productCategoryLists.clear();
                    if (mainModal == null)
                        return;

                    productCategoryLists.addAll(mainModal.getCategory());
                    categoryAdapter.notifyDataSetChanged();
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
            case R.id.cardViewPopular:
                int pos = Integer.parseInt(v.getTag().toString());
                categoryId = productCategoryLists.get(pos).getCategoryId();
                productSubCategoryApi();
                break;
            case R.id.cardViewSubCategory:
                int posSubCat = Integer.parseInt(v.getTag().toString());
                String subCategoryId = subCategoryLists.get(posSubCat).getSubCategoryId();
                Intent intent = new Intent(mContext, UserProductListActivity.class);
                intent.putExtra("category_id", categoryId);
                intent.putExtra("sub_category_id", subCategoryId);
                dialogSubCategory.dismiss();
                startActivity(intent);
                break;
        }
    }

    private void productSubCategoryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getProductSubCategoryList(new Dialog(mContext), retrofitApiClient.productSubCategory("0"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ProductSubCategoryMainModal mainModal = (ProductSubCategoryMainModal) result.body();
                    subCategoryLists.clear();
                    if (mainModal == null)
                        return;
                    if (!mainModal.getError()) {
                        subCategoryLists.addAll(mainModal.getSubCategory());
                        openPopup();
                    } else {
                        Intent intent = new Intent(mContext, UserProductListActivity.class);
                        intent.putExtra("category_id", categoryId);
                        intent.putExtra("sub_category_id", "0");
                        startActivity(intent);
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

    private void openPopup() {
        dialogSubCategory = new Dialog(mContext);
        dialogSubCategory.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSubCategory.setContentView(R.layout.dialog_sub_category);

        dialogSubCategory.setCanceledOnTouchOutside(true);
        dialogSubCategory.setCancelable(true);
        if (dialogSubCategory.getWindow() != null)
            dialogSubCategory.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        RecyclerView recyclerViewSubCategory = rootView.findViewById(R.id.recyclerViewSubCategory);
        subCategoryAdapter = new ProductSubCategoryAdapter(subCategoryLists, mContext, this);
        recyclerViewSubCategory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerViewSubCategory.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSubCategory.setAdapter(subCategoryAdapter);
        subCategoryAdapter.notifyDataSetChanged();

        dialogSubCategory.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSubCategory.dismiss();
            }
        });

        Window window = dialogSubCategory.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialogSubCategory.show();
    }

}