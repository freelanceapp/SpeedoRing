package com.speedoring.retrofit_provider;

import com.speedoring.constant.Constant;
import com.speedoring.modal.banner_model.BannerModel;
import com.speedoring.modal.city.CityMainModal;
import com.speedoring.modal.insta_mojo.GatewayOrderStatus;
import com.speedoring.modal.insta_mojo.GetOrderIDRequest;
import com.speedoring.modal.insta_mojo.GetOrderIDResponse;
import com.speedoring.modal.search_modal.SearchMainModal;
import com.speedoring.modal.service_category.ServiceCatMainModal;
import com.speedoring.modal.user.product_category.ProductCategoryMainModal;
import com.speedoring.modal.user.product_detail.ProductDetailMainModal;
import com.speedoring.modal.user.product_list_home.HomeProductListMainModal;
import com.speedoring.modal.user.product_sub_category.ProductSubCategoryMainModal;
import com.speedoring.modal.user.service_category.ServiceCategoryMainModal;
import com.speedoring.modal.user.service_list.ServiceListMainModal;
import com.speedoring.modal.vendor.enquiry_list.VendorEnquiryMainModal;
import com.speedoring.modal.vendor.login_data.VendorLoginMainModal;
import com.speedoring.modal.vendor.vendor_product_list.VendorProductListMainModal;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitApiClient {

    @GET(Constant.BANNER)
    Call<BannerModel> getBanner();

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
    
    @FormUrlEncoded
    @POST(Constant.SEARCH_DATA)
    Call<SearchMainModal> searchData(@Field("city") String city, @Field("category_id") String category_id,
                                     @Field("productname") String productname, @Field("page_number") String page_number);

    @GET(Constant.CITY_LIST)
    Call<CityMainModal> cityList();

    @GET(Constant.SERVICE_CAT)
    Call<ServiceCatMainModal> serviceCatList();

    /***************************************************************************************************/
    /*********************Vendor api******************/
    @FormUrlEncoded
    @POST(Constant.VENDOR_LOGIN)
    Call<VendorLoginMainModal> vendorLogin(@Field("mobile_number") String mobile_number, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.VENDOR_SIGN_UP)
    Call<ResponseBody> vendorSignUp(@Field("first_name") String first_name, @Field("last_name") String last_name,
                                    @Field("mobile_number") String mobile_number, @Field("password") String password,
                                    @Field("address") String address, @Field("city") String city,
                                    @Field("state") String state, @Field("pincode") String pincode,
                                    @Field("about") String about);

    @FormUrlEncoded
    @POST(Constant.OTP_VERIFY)
    Call<ResponseBody> otpVerification(@Field("mobile_no") String mobile_no, @Field("otp_no") String otp_no);

    @FormUrlEncoded
    @POST(Constant.FORGOT_PASSWORD)
    Call<ResponseBody> forgotPassword(@Field("mobile_no") String mobile_no);

    @FormUrlEncoded
    @POST(Constant.CHANGE_PASSWORD)
    Call<ResponseBody> changePassword(@Field("mobile_no") String mobile_no, @Field("password") String password);

    @Multipart
    @POST(Constant.UPLOAD_PROFILE_IMAGE)
    Call<ResponseBody> uploadProfile(@Part("user_id") RequestBody userid, @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST(Constant.PROFILE_DATA)
    Call<VendorLoginMainModal> vendorProfileData(@Field("user_id") String userid);

    @FormUrlEncoded
    @POST(Constant.UPDATE_PERSONAL_INFO)
    Call<ResponseBody> updatePersonalInfo(@Field("user_id") String user_id, @Field("first_name") String first_name,
                                          @Field("last_name") String last_name, @Field("country") String country,
                                          @Field("address") String address, @Field("city") String city,
                                          @Field("state") String state, @Field("pincode") String pincode);

    @FormUrlEncoded
    @POST(Constant.UPDATE_BUSINESS_INFO)
    Call<ResponseBody> updateBusinessInfo(@Field("user_id") String user_id, @Field("business_name") String business_name,
                                          @Field("business_nature") String business_nature, @Field("business_category") String business_category,
                                          @Field("business_tin") String business_tin, @Field("business_address") String business_address,
                                          @Field("business_city") String business_city, @Field("business_pincode") String business_pincode,
                                          @Field("business_area") String business_area, @Field("business_country") String business_country,
                                          @Field("business_keyword") String business_keyword);

    @FormUrlEncoded
    @POST(Constant.UPDATE_CONTACT_INFO)
    Call<ResponseBody> updateContactInfo(@Field("user_id") String user_id, @Field("landline_one") String landline_one,
                                         @Field("landline_two") String landline_two, @Field("mobile_one") String mobile_one,
                                         @Field("mobile_two") String mobile_two, @Field("fax_one") String fax_one,
                                         @Field("fax_two") String fax_two, @Field("tollfree_one") String tollfree_one,
                                         @Field("tollfree_two") String tollfree_two, @Field("website") String website,
                                         @Field("email") String email);

    @FormUrlEncoded
    @POST(Constant.VENDOR_PRODUCT_LIST)
    Call<VendorProductListMainModal> vendorProductList(@Field("vendor_id") String vendor_id, @Field("page_number") String page_number);

    @FormUrlEncoded
    @POST(Constant.VENDOR_PRODUCT_DELETE)
    Call<ResponseBody> vendorProductDelete(@Field("vendor_id") String vendor_id, @Field("listing_id") String listing_id);

    @Multipart
    @POST(Constant.VENDOR_ADD_PRODUCT)
    Call<ResponseBody> vendorAddProduct(@Part("vendor_id") RequestBody vendor_id, @Part("category_id") RequestBody category_id,
                                        @Part("sub_category_id") RequestBody sub_category_id, @Part("listing_name") RequestBody listing_name,
                                        @Part("description") RequestBody description, @Part MultipartBody.Part images[]);

    @FormUrlEncoded
    @POST(Constant.VENDOR_ENQUIRY_LIST)
    Call<VendorEnquiryMainModal> vendorEnquiryList(@Field("user_id") String user_id, @Field("page_number") String page_number);

    /******************************************************************************/
    /*******************************Insta mojo*************************/
    @POST("/order")
    Call<GetOrderIDResponse> createOrder(@Body GetOrderIDRequest request);

    @GET("/status")
    Call<GatewayOrderStatus> orderStatus(@Query("env") String env, @Query("order_id") String orderID,
                                         @Query("transaction_id") String transactionID);

    @POST("/refund")
    Call<ResponseBody> refundAmount(@Query("env") String env,
                                    @Query("transaction_id") String transaction_id,
                                    @Query("amount") String amount);
}