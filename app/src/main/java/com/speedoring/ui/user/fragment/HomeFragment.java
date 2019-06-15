package com.speedoring.ui.user.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.speedoring.R;
import com.speedoring.adapter.BannerPagerAdapter;
import com.speedoring.adapter.HomeProductListAdapter;
import com.speedoring.adapter.ProductCategoryAdapter;
import com.speedoring.adapter.ProductSubCategoryAdapter;
import com.speedoring.adapter.ServiceCategoryAdapter;
import com.speedoring.interface_update_data.CategoryServiceInterface;
import com.speedoring.modal.banner_model.BannerDatum;
import com.speedoring.modal.banner_model.BannerModel;
import com.speedoring.modal.user.product_category.ProductCategoryList;
import com.speedoring.modal.user.product_category.ProductCategoryMainModal;
import com.speedoring.modal.user.product_list_home.HomeProductListMainModal;
import com.speedoring.modal.user.product_list_home.HomeProductListing;
import com.speedoring.modal.user.product_sub_category.ProductSubCategory;
import com.speedoring.modal.user.product_sub_category.ProductSubCategoryMainModal;
import com.speedoring.modal.user.service_category.ServiceCategoryMainModal;
import com.speedoring.modal.user.service_category.ServicesCategory;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.ui.user.activity.UserAllCategoryActivity;
import com.speedoring.ui.user.activity.UserProductListActivity;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private Dialog dialogCall, dialogAddress;

    private String categoryId = "", categoryName = "";
    private List<ProductSubCategory> subCategoryLists = new ArrayList<>();
    private ProductSubCategoryAdapter subCategoryAdapter;
    private Dialog dialogSubCategory;

    private CirclePageIndicator indicator;
    private int successPos = 0;
    private Handler imageHandler;
    private Runnable imageRunnable;
    private ViewPager pagerSuccess;
    private BannerPagerAdapter adapter;
    private List<BannerDatum> successImagesList = new ArrayList<>();

    private HomeProductListAdapter homeProductListAdapter;
    private List<HomeProductListing> homeProductListings = new ArrayList<>();

    private ServiceCategoryAdapter serviceCategoryAdapter;
    private List<ServicesCategory> servicesCategoryList = new ArrayList<>();
    private ProductCategoryAdapter categoryAdapter;
    private List<ProductCategoryList> productCategoryLists = new ArrayList<>();

    private CategoryServiceInterface anInterface;

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

    public void initInterface(CategoryServiceInterface anInterface) {
        this.anInterface = anInterface;
    }

    private void initPager() {
        pagerSuccess = rootView.findViewById(R.id.pagerSuccess);
        indicator = rootView.findViewById(R.id.indicator);

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
        successPos = pagerSuccess.getCurrentItem();
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
        rootView.findViewById(R.id.txtViewMore).setOnClickListener(this);

        RecyclerView rvPopularVendor = rootView.findViewById(R.id.rvPopularVendor);
        serviceCategoryAdapter = new ServiceCategoryAdapter(servicesCategoryList, mContext, this, 1);
        rvPopularVendor.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvPopularVendor.setItemAnimator(new DefaultItemAnimator());
        rvPopularVendor.setAdapter(serviceCategoryAdapter);
        serviceCategoryAdapter.notifyDataSetChanged();

        RecyclerView recyclerViewTopOffer = rootView.findViewById(R.id.recyclerViewTopOffer);
        homeProductListAdapter = new HomeProductListAdapter(homeProductListings, mContext, this);
        recyclerViewTopOffer.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewTopOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopOffer.setAdapter(homeProductListAdapter);
        homeProductListAdapter.notifyDataSetChanged();

        RecyclerView rvProductCategory = rootView.findViewById(R.id.rvProductCategory);
        categoryAdapter = new ProductCategoryAdapter(productCategoryLists, mContext, this, 3);
        rvProductCategory.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvProductCategory.setItemAnimator(new DefaultItemAnimator());
        rvProductCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        getHomeProductApi();
        productCategoryApi();
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

                    indicator.setViewPager(pagerSuccess);
                    final float density = getResources().getDisplayMetrics().density;
                    indicator.setRadius(5 * density);
                    indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageSelected(int position) {
                            successPos = position;
                        }

                        @Override
                        public void onPageScrolled(int pos, float arg1, int arg2) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int pos) {

                        }
                    });

                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void productCategoryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getProductCategoryList(new Dialog(mContext), retrofitApiClient.productCategory("1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ProductCategoryMainModal mainModal = (ProductCategoryMainModal) result.body();
                    productCategoryLists.clear();
                    if (mainModal == null)
                        return;

                    productCategoryLists.addAll(mainModal.getCategory());
                    categoryAdapter.notifyDataSetChanged();

                    servicesCategoryApi();
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
                    anInterface.getCategoryList(servicesCategoryList, productCategoryLists);
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
                    homeProductListings.clear();
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
        switch (v.getId()) {
            case R.id.txtViewMore:
                startActivity(new Intent(mContext, UserAllCategoryActivity.class));
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

            case R.id.imgCall:
                int posC = (int) v.getTag();
                String mobileA = homeProductListings.get(posC).getVendorMobileOne();
                String mobileB = homeProductListings.get(posC).getVendorMobileTwo();
                String LandlineA = homeProductListings.get(posC).getVendorMobileOne();
                String LandlineB = homeProductListings.get(posC).getVendorMobileTwo();
                String email = homeProductListings.get(posC).getVendorEmail();
                callDialog(mobileA, mobileB, LandlineA, LandlineB, email);
                break;
            case R.id.imgAddress:
                int posA = (int) v.getTag();
                String state = homeProductListings.get(posA).getVendorState();
                String city = homeProductListings.get(posA).getVendorCity();
                String address = homeProductListings.get(posA).getVendorAddress();
                addressDialog(state, city, address);
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