package com.speedoring.retrofit_provider;

import com.speedoring.constant.Constant;
import com.speedoring.modal.banner_model.BannerModel;
import com.speedoring.modal.coupon_model.CouponModel;
import com.speedoring.modal.popular_vendor.StoreMainModel;
import com.speedoring.modal.product_category.ProductCategoryMainModal;
import com.speedoring.modal.product_detail.ProductDetailMainModal;
import com.speedoring.modal.product_list_home.HomeProductListMainModal;
import com.speedoring.modal.product_sub_category.ProductSubCategoryMainModal;
import com.speedoring.modal.service_category.ServiceCategoryMainModal;
import com.speedoring.modal.service_list.ServiceListMainModal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApiClient {

    @GET(Constant.BANNER)
    Call<BannerModel> getBanner();

    @GET(Constant.ALL_STORE)
    Call<StoreMainModel> getStore();

    @GET(Constant.COUPON)
    Call<CouponModel> getCoupon();

    @GET(Constant.SERVICE_CATEGORY)
    Call<ServiceCategoryMainModal> serviceCategory();

    @GET(Constant.PRODUCT_LIST_HOME)
    Call<HomeProductListMainModal> productListHome();

    @FormUrlEncoded
    @POST(Constant.SERVICE_LIST)
    Call<ServiceListMainModal> serviceList(@Field("service_cat_id") String service_cat_id);

    @FormUrlEncoded
    @POST(Constant.SERVICE_ENQUIRY)
    Call<ResponseBody> serviceEnquiry(@Field("service_cat_id") String service_cat_id, @Field("user_name") String user_name,
                                      @Field("user_email") String user_email, @Field("user_phone_no") String user_phone_no,
                                      @Field("user_comment") String user_comment);

    @FormUrlEncoded
    @POST(Constant.PRODUCT_CATEGORY)
    Call<ProductCategoryMainModal> productCategory(@Field("category_call") String category_call);

    @FormUrlEncoded
    @POST(Constant.PRODUCT_SUB_CATEGORY)
    Call<ProductSubCategoryMainModal> productSubCategory(@Field("category_id") String category_id);

    @FormUrlEncoded
    @POST(Constant.PRODUCT_LIST)
    Call<HomeProductListMainModal> productList(@Field("category_id") String category_id, @Field("sub_category_id") String sub_category_id,
                                               @Field("page_number") String page_number);

    @FormUrlEncoded
    @POST(Constant.PRODUCT_DETAIL)
    Call<ProductDetailMainModal> productDetail(@Field("listing_id") String product_id);

}