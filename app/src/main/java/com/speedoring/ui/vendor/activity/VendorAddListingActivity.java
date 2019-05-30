package com.speedoring.ui.vendor.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.speedoring.R;
import com.speedoring.adapter.SpinnerCategoryAdapter;
import com.speedoring.adapter.SpinnerSubCategoryAdapter;
import com.speedoring.adapter.multi_image_picker.ImageListRecyclerAdapter;
import com.speedoring.constant.Constant;
import com.speedoring.modal.ImageList;
import com.speedoring.modal.User;
import com.speedoring.modal.user.product_category.ProductCategoryList;
import com.speedoring.modal.user.product_category.ProductCategoryMainModal;
import com.speedoring.modal.user.product_sub_category.ProductSubCategory;
import com.speedoring.modal.user.product_sub_category.ProductSubCategoryMainModal;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.upload_with_progress.ProgressRequestBody;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.AppPreference;
import com.speedoring.utils.BaseActivity;
import com.speedoring.utils.multi_image_picker.Action;
import com.speedoring.utils.multi_image_picker.CustomGallery;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static android.R.layout.simple_spinner_dropdown_item;

public class VendorAddListingActivity extends BaseActivity implements View.OnClickListener, ProgressRequestBody.UploadCallbacks {


    private List<ImageList> filePaths = new ArrayList<>();
    private ImageListRecyclerAdapter imageListRecyclerAdapter;
    private List<ProductCategoryList> productCategoryLists = new ArrayList<>();
    private List<ProductSubCategory> subCategoryLists = new ArrayList<>();
    private SpinnerCategoryAdapter categoryAdapter;
    private SpinnerSubCategoryAdapter subCategoryAdapter;
    private String strCategoryId = "", strSubCategoryId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_activity_add_listing);

        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.btnCreateListing).setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        imageListRecyclerAdapter = new ImageListRecyclerAdapter(getApplicationContext());
        imageListRecyclerAdapter.setMultiplePick(false);
        recyclerView.setAdapter(imageListRecyclerAdapter);
        findViewById(R.id.txtAddImages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectImage();
            }
        });

        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        categoryAdapter = new SpinnerCategoryAdapter(mContext, R.layout.spinner_category_list, productCategoryLists);
        categoryAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (productCategoryLists.size() > 0) {
                    strCategoryId = productCategoryLists.get(position).getCategoryId();
                    if (!strCategoryId.isEmpty()) {
                        productSubCategoryApi();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerSubCategory = findViewById(R.id.spinnerSubCategory);
        subCategoryAdapter = new SpinnerSubCategoryAdapter(mContext, R.layout.spinner_category_list, subCategoryLists);
        subCategoryAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
        spinnerSubCategory.setAdapter(subCategoryAdapter);

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
                    if (mainModal.getCategory().size() > 0) {
                        productCategoryLists.addAll(mainModal.getCategory());
                    }
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

    private void productSubCategoryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getProductSubCategoryList(new Dialog(mContext), retrofitApiClient.productSubCategory(
                    strCategoryId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ProductSubCategoryMainModal mainModal = (ProductSubCategoryMainModal) result.body();
                    subCategoryLists.clear();
                    findViewById(R.id.llSubCat).setVisibility(View.GONE);
                    if (mainModal == null)
                        return;
                    if (!mainModal.getError()) {
                        if (mainModal.getSubCategory().size() > 0) {
                            findViewById(R.id.llSubCat).setVisibility(View.VISIBLE);
                            subCategoryLists.addAll(mainModal.getSubCategory());
                        } else {
                            findViewById(R.id.llSubCat).setVisibility(View.GONE);
                        }
                    } else {
                        findViewById(R.id.llSubCat).setVisibility(View.GONE);
                    }
                    subCategoryAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                    findViewById(R.id.llSubCat).setVisibility(View.GONE);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    private void onSelectImage() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
                            startActivityForResult(i, 200);
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                VendorAddListingActivity.this.openSettings();
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String[] all_path = data.getStringArrayExtra("all_path");
            ArrayList<CustomGallery> dataT = new ArrayList<>();
            for (String string : all_path) {
                File imgFile = new File(string);
                filePaths.add(new ImageList(imgFile, "image/*"));
                CustomGallery item = new CustomGallery();
                item.sdcardPath = string;
                dataT.add(item);
            }
            imageListRecyclerAdapter.addAll(dataT);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnCreateListing:
                addListingApi();
                break;
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /********************************************************************************************************************/
    /*******************************Add Listing Api***********************************************/
    private void addListingApi() {
        String vendorId = User.getUser().getVendorId();
        String strListingName = ((EditText) findViewById(R.id.edtListingName)).getText().toString();
        String strListingDescription = ((EditText) findViewById(R.id.edtDescription)).getText().toString();

        if (strListingName.isEmpty()) {
            Alerts.show(mContext, "Enter listing name...!!!");
        } else if (strListingDescription.isEmpty()) {
            Alerts.show(mContext, "Enter listing description...!!!");
        } else if (filePaths.size() == 0) {
            Alerts.show(mContext, "Select at least one image...!!!");
        } else {
            RequestBody _vendorId = RequestBody.create(MediaType.parse("text/plain"), vendorId);
            RequestBody _categoryId = RequestBody.create(MediaType.parse("text/plain"), strCategoryId);
            RequestBody _subCategoryId = RequestBody.create(MediaType.parse("text/plain"), strSubCategoryId);
            RequestBody _name = RequestBody.create(MediaType.parse("text/plain"), strListingName);
            RequestBody _description = RequestBody.create(MediaType.parse("text/plain"), strListingDescription);

            MultipartBody.Part[] imageFile = new MultipartBody.Part[filePaths.size()];
            for (int i = 0; i < filePaths.size(); i++) {
                ImageList imageVideo = filePaths.get(i);
                ProgressRequestBody fileBody = new ProgressRequestBody(imageVideo.getFile(), imageVideo.getType(), this);
                imageFile[i] = MultipartBody.Part.createFormData("images[]", imageVideo.getFile().getName(), fileBody);
            }

            if (cd.isNetworkAvailable()) {
                RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.vendorAddProduct(_vendorId,
                        _categoryId, _subCategoryId, _name, _description, imageFile), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (jsonObject.getBoolean("error")) {
                                Alerts.show(mContext, jsonObject.getString("message"));
                                AppPreference.setBooleanPreference(mContext, Constant.IS_ITEM_ADDED, true);
                                finish();
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
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }

    @Override
    public void onProgressUpdate(int percentage) {
        ((Button) findViewById(R.id.btnCreateListing)).setText(percentage + "");
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFinish() {

    }
}
