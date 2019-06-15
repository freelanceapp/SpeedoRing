package com.speedoring.ui.user.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.speedoring.R;
import com.speedoring.adapter.ProductCategoryAdapter;
import com.speedoring.adapter.ProductSubCategoryAdapter;
import com.speedoring.adapter.ServiceCategoryAdapter;
import com.speedoring.constant.Constant;
import com.speedoring.interface_update_data.CategoryServiceInterface;
import com.speedoring.modal.user.product_category.ProductCategoryList;
import com.speedoring.modal.user.product_sub_category.ProductSubCategory;
import com.speedoring.modal.user.product_sub_category.ProductSubCategoryMainModal;
import com.speedoring.modal.user.service_category.ServicesCategory;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.ui.user.fragment.HomeFragment;
import com.speedoring.ui.vendor.activity.SignInActivity;
import com.speedoring.ui.vendor.activity.SignUpActivity;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseActivity;
import com.speedoring.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class UserHomeActivity extends BaseActivity implements View.OnClickListener {

    private boolean isServiceView = false;
    private boolean isCategoryView = false;

    public static FragmentUtils fragmentUtils;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;

    private Dialog dialogSubCategory;
    private List<ProductSubCategory> subCategoryLists = new ArrayList<>();
    private String categoryId = "", categoryName = "";
    private View contentView;
    private ServiceCategoryAdapter serviceCategoryAdapter;
    private List<ServicesCategory> servicesCategoryList = new ArrayList<>();
    private ProductCategoryAdapter categoryAdapter;
    private List<ProductCategoryList> productCategoryLists = new ArrayList<>();
    private RecyclerView rvPopularVendor, rvProductCategory;
    private ImageView imgServiceArrow, imgCategoryArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_navigation_home);

        initNavigation();
    }

    private void initNavigation() {
        imgServiceArrow = findViewById(R.id.imgServiceArrow);
        imgCategoryArrow = findViewById(R.id.imgCategoryArrow);
        findViewById(R.id.llHome).setOnClickListener(this);
        findViewById(R.id.llService).setOnClickListener(this);
        findViewById(R.id.llCategories).setOnClickListener(this);
        findViewById(R.id.llSignUp).setOnClickListener(this);
        findViewById(R.id.llLogin).setOnClickListener(this);
        findViewById(R.id.txtTitle).setOnClickListener(this);
        findViewById(R.id.txtSearch).setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        contentView = findViewById(R.id.llContainer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                final float xOffset = view.getWidth() * v;
                contentView.setTranslationX(xOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                contentView.animate().setDuration(300).translationX(0).translationY(0);
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        };
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        toggle.syncState();

        rvPopularVendor = findViewById(R.id.rvPopularVendor);
        serviceCategoryAdapter = new ServiceCategoryAdapter(servicesCategoryList, mContext, this, 2);
        rvPopularVendor.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPopularVendor.setItemAnimator(new DefaultItemAnimator());
        rvPopularVendor.setAdapter(serviceCategoryAdapter);
        serviceCategoryAdapter.notifyDataSetChanged();

        rvProductCategory = findViewById(R.id.rvProductCategory);
        categoryAdapter = new ProductCategoryAdapter(productCategoryLists, mContext, this, 1);
        rvProductCategory.setLayoutManager(new LinearLayoutManager(mContext));
        rvProductCategory.setItemAnimator(new DefaultItemAnimator());
        rvProductCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        initFragment();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);

        homeFragment = new HomeFragment();
        homeFragment.initInterface(new CategoryServiceInterface() {
            @Override
            public void getCategoryList(List<ServicesCategory> servicesList, List<ProductCategoryList> categoryLists) {
                servicesCategoryList.clear();
                productCategoryLists.clear();
                servicesCategoryList.addAll(servicesList);
                productCategoryLists.addAll(categoryLists);

                serviceCategoryAdapter.notifyDataSetChanged();
                categoryAdapter.notifyDataSetChanged();
            }
        });
        fragmentUtils.replaceFragment(homeFragment, Constant.HomeFragment, R.id.frameLayout);
        findViewById(R.id.llHome).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        findViewById(R.id.llSignUp).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        findViewById(R.id.llLogin).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llHome:
                fragmentUtils.replaceFragment(homeFragment, Constant.HomeFragment, R.id.frameLayout);
                findViewById(R.id.llHome).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                findViewById(R.id.llSignUp).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                findViewById(R.id.llLogin).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.llLogin:
                startActivity(new Intent(mContext, SignInActivity.class));
                break;
            case R.id.llSignUp:
                startActivity(new Intent(mContext, SignUpActivity.class));
                break;
            case R.id.txtTitle:
            case R.id.txtSearch:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.llService:
                if (isServiceView) {
                    rvPopularVendor.setVisibility(View.GONE);
                    isServiceView = false;
                    imgServiceArrow.animate().rotation(0).start();
                } else {
                    imgServiceArrow.animate().rotation(90).start();
                    rvPopularVendor.setVisibility(View.VISIBLE);
                    isServiceView = true;
                }
                if (isCategoryView) {
                    imgCategoryArrow.animate().rotation(0).start();
                }
                rvProductCategory.setVisibility(View.GONE);
                isCategoryView = false;
                break;
            case R.id.llCategories:
                if (isCategoryView) {
                    imgCategoryArrow.animate().rotation(0).start();
                    rvProductCategory.setVisibility(View.GONE);
                    isCategoryView = false;
                } else {
                    imgCategoryArrow.animate().rotation(90).start();
                    rvProductCategory.setVisibility(View.VISIBLE);
                    isCategoryView = true;
                }
                if (isServiceView) {
                    imgServiceArrow.animate().rotation(0).start();
                }
                rvPopularVendor.setVisibility(View.GONE);
                isServiceView = false;
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
        ProductSubCategoryAdapter subCategoryAdapter = new ProductSubCategoryAdapter(subCategoryLists, mContext, this);
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
