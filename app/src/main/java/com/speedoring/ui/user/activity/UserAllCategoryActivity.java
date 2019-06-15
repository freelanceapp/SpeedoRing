package com.speedoring.ui.user.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.speedoring.R;
import com.speedoring.adapter.ProductCategoryAdapter;
import com.speedoring.adapter.ProductSubCategoryAdapter;
import com.speedoring.modal.user.product_category.ProductCategoryList;
import com.speedoring.modal.user.product_category.ProductCategoryMainModal;
import com.speedoring.modal.user.product_sub_category.ProductSubCategory;
import com.speedoring.modal.user.product_sub_category.ProductSubCategoryMainModal;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class UserAllCategoryActivity extends BaseActivity implements View.OnClickListener {

    private Dialog dialogSubCategory;
    private String categoryId = "", categoryName = "";

    private ProductCategoryAdapter categoryAdapter;
    private List<ProductCategoryList> productCategoryLists = new ArrayList<>();

    private ProductSubCategoryAdapter subCategoryAdapter;
    private List<ProductSubCategory> subCategoryLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_all_category);
        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);

        RecyclerView recyclerViewTopOffer = findViewById(R.id.recyclerViewTopOffer);
        categoryAdapter = new ProductCategoryAdapter(productCategoryLists, mContext, this, 2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
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
            case R.id.imgBack:
                finish();
                break;
            case R.id.cardViewPopular:
                int pos = Integer.parseInt(v.getTag().toString());
                categoryId = productCategoryLists.get(pos).getCategoryId();
                categoryName = productCategoryLists.get(pos).getCatName();
                productSubCategoryApi();
                break;
            case R.id.cardViewSubCategory:
                int posSubCat = Integer.parseInt(v.getTag().toString());
                String subCategoryId = subCategoryLists.get(posSubCat).getSubCategoryId();
                categoryName = subCategoryLists.get(posSubCat).getSubCatName();
                Intent intent = new Intent(mContext, UserProductListActivity.class);
                intent.putExtra("category_id", categoryId);
                intent.putExtra("sub_category_id", subCategoryId);
                intent.putExtra("category_name", categoryName);
                dialogSubCategory.dismiss();
                startActivity(intent);
                break;
        }
    }

    private void productSubCategoryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getProductSubCategoryList(new Dialog(mContext), retrofitApiClient.productSubCategory(categoryId), new WebResponse() {
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
                        intent.putExtra("category_name", categoryName);
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

        RecyclerView recyclerViewSubCategory = dialogSubCategory.findViewById(R.id.recyclerViewSubCategory);
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