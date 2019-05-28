package com.speedoring.ui.user.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.speedoring.R;
import com.speedoring.adapter.ProductImagesAdapter;
import com.speedoring.constant.Constant;
import com.speedoring.modal.user.product_detail.ProductDetailMainModal;
import com.speedoring.modal.user.product_detail.ProductImage;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class UserProductDetailActivity extends BaseActivity implements View.OnClickListener {

    private String productId = "";
    private List<ProductImage> productImages = new ArrayList<>();
    private ProductImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_product_detail);

        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.btnEnquiry).setOnClickListener(this);

        productId = getIntent().getStringExtra("product_id");

        imagesAdapter = new ProductImagesAdapter(productImages, mContext, this);
        RecyclerView rvProductImage = findViewById(R.id.rvProductImage);
        rvProductImage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvProductImage.setItemAnimator(new DefaultItemAnimator());
        rvProductImage.setAdapter(imagesAdapter);
        imagesAdapter.notifyDataSetChanged();
        productDetailApi();
    }

    private void productDetailApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getProductDetail(new Dialog(mContext), retrofitApiClient.productDetail(productId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ProductDetailMainModal detailMainModal = (ProductDetailMainModal) result.body();
                    productImages.clear();
                    if (detailMainModal == null)
                        return;

                    if (!detailMainModal.getError()) {
                        ((TextView) findViewById(R.id.txtTitle)).setText(detailMainModal.getProductListing().getListingName());
                        ((TextView) findViewById(R.id.txtServiceTitle)).setText(detailMainModal.getProductListing().getListingName());
                        ((TextView) findViewById(R.id.txtServiceDes)).setText(detailMainModal.getProductListing().getDescription());

                        if (detailMainModal.getProductListing().getProductImages().size() > 0) {
                            productImages.addAll(detailMainModal.getProductListing().getProductImages());
                            String productImage = detailMainModal.getProductListing().getProductImages().get(0).getProdcutImage();
                            Glide.with(mContext)
                                    .load(productImage)
                                    .placeholder(R.drawable.ic_default_photo)
                                    .into((ImageView) findViewById(R.id.imgService));
                            findViewById(R.id.rlImg).setVisibility(View.VISIBLE);
                        } else {
                            findViewById(R.id.rlImg).setVisibility(View.GONE);
                        }
                    } else {
                        Alerts.show(mContext, detailMainModal.getMessage());
                    }
                    imagesAdapter.notifyDataSetChanged();
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
            case R.id.btnEnquiry:
                openPopup();
                break;
            case R.id.cardViewProduct:
                int position = Integer.parseInt(v.getTag().toString());
                String productImage = productImages.get(position).getProdcutImage();
                Glide.with(mContext)
                        .load(productImage)
                        .placeholder(R.drawable.ic_default_photo)
                        .into((ImageView) findViewById(R.id.imgService));
                break;
        }
    }

    private void openPopup() {
        final Dialog dialogEnquiry = new Dialog(mContext);
        dialogEnquiry.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnquiry.setContentView(R.layout.user_dialog_enquiry);

        dialogEnquiry.setCanceledOnTouchOutside(true);
        dialogEnquiry.setCancelable(true);
        if (dialogEnquiry.getWindow() != null)
            dialogEnquiry.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialogEnquiry.findViewById(R.id.tvSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) dialogEnquiry.findViewById(R.id.edtName)).getText().toString();
                String contact = ((EditText) dialogEnquiry.findViewById(R.id.edtContactNo)).getText().toString();
                String email = ((EditText) dialogEnquiry.findViewById(R.id.edtEmail)).getText().toString();
                String comment = ((EditText) dialogEnquiry.findViewById(R.id.edtComment)).getText().toString();

                if (name.isEmpty()) {
                    Alerts.show(mContext, "Enter name...!!!");
                } else if (contact.isEmpty()) {
                    Alerts.show(mContext, "Enter contact no...!!!");
                } else if (contact.length() < 10) {
                    Alerts.show(mContext, "Enter valid contact no...!!!");
                } else if (email.isEmpty()) {
                    Alerts.show(mContext, "Enter email address...!!!");
                } else if (Constant.isValidEmailId(email)) {
                    Alerts.show(mContext, "Enter valid email address...!!!");
                } else if (comment.isEmpty()) {
                    Alerts.show(mContext, "Enter comments...!!!");
                } else {
                    if (cd.isNetworkAvailable()) {
                        RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.serviceEnquiry(productId,
                                name, email, comment, comment), new WebResponse() {
                            @Override
                            public void onResponseSuccess(Response<?> result) {
                                ResponseBody responseBody = (ResponseBody) result.body();
                                try {
                                    JSONObject jsonObject = new JSONObject(responseBody.string());
                                    if (!jsonObject.getBoolean("error")) {
                                        Alerts.show(mContext, jsonObject.getString("message"));
                                        dialogEnquiry.dismiss();
                                    } else {
                                        Alerts.show(mContext, jsonObject.getString("message"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onResponseFailed(String error) {
                                Alerts.show(mContext, error);
                                dialogEnquiry.dismiss();
                            }
                        });
                    } else {
                        cd.show(mContext);
                    }
                }
            }
        });

        dialogEnquiry.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEnquiry.dismiss();
            }
        });

        Window window = dialogEnquiry.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialogEnquiry.show();
    }
}
