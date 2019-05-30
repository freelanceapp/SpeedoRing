package com.speedoring.ui.vendor.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.speedoring.R;
import com.speedoring.adapter.Pager;
import com.speedoring.constant.Constant;
import com.speedoring.interface_update_data.UpdateInterface;
import com.speedoring.modal.User;
import com.speedoring.modal.vendor.login_data.VendorLoginMainModal;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.AppPreference;
import com.speedoring.utils.BaseActivity;
import com.speedoring.utils.ImageUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class VendorProfileActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static final int PICK_FROM_GALLERY = 100;
    public static final int PICK_FROM_CAMERA = 101;
    private CircleImageView imgVendorProfile;
    private File imageFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_activity_profile_toolbar);

        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.imgCamera).setOnClickListener(this);
        imgVendorProfile = findViewById(R.id.imgVendorProfile);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Personal"));
        tabLayout.addTab(tabLayout.newTab().setText("Business"));
        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ImageUtil.Glide(mContext, ((CircleImageView) findViewById(R.id.imgVendorProfile)), User.getUser().getImage());

        //Initializing viewPager
        viewPager = findViewById(R.id.pager);
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount(), new UpdateInterface() {
            @Override
            public void isUpdateData(Boolean isUpdate) {
                if (isUpdate) {
                    userProfileData();
                }
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        userProfileData();
    }

    /*****************************************************/
    /************User Profile Data Api********************/
    private void userProfileData() {
        String vendorId = User.getUser().getVendorId();
        if (cd.isNetworkAvailable()) {
            RetrofitService.getVendorLoginData(null, retrofitApiClient.vendorProfileData(vendorId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VendorLoginMainModal loginMainModal = (VendorLoginMainModal) result.body();
                    if (loginMainModal == null)
                        return;
                    if (!loginMainModal.getError()) {
                        Gson gson = new GsonBuilder().setLenient().create();
                        String data = gson.toJson(loginMainModal.getVendorinfo());
                        AppPreference.setBooleanPreference(mContext, Constant.IS_LOGIN, true);
                        AppPreference.setStringPreference(mContext, Constant.VENDOR_DATA, data);
                        User.setUser(loginMainModal.getVendorinfo());
                        AppPreference.setBooleanPreference(mContext, Constant.IS_VENDOR_DATA_UPDATE, true);
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
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgCamera:
                onSelectImage();
                break;
        }
    }

    private void onSelectImage() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
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

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(mContext, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    private void launchGalleryIntent() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                VendorProfileActivity.this.openSettings();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_FROM_GALLERY && null != data) {
                final Uri uriImage = data.getData();
                if (uriImage == null)
                    return;
                loadProfile(uriImage.toString());
                String path = getPath(uriImage);
                if (!path.isEmpty()) {
                    imageFile = new File(getPath(uriImage));
                    uploadProfileApi();
                } else {
                    Alerts.show(mContext, "Image not found...!!!");
                }
            }
        }

        /*  if (requestCode == PICK_FROM_GALLERY) {
                Uri uri = data.getParcelableExtra("path");
                String path = getPath(uri);
                loadProfile(uri.toString());
                if (!path.isEmpty()) {
                    imageFile = new File(getPath(uri));
                    loadProfile(uri.toString());
                    if (imageFile != null) {
                        uploadProfileApi();
                    }
                }
            }*/
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String strPath = cursor.getString(column_index);
            cursor.close();
            return strPath;
        } else {
            return "";
        }
    }

    private void loadProfile(String url) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.ic_default_photo)
                .into(imgVendorProfile);
    }

    /*************************************************************************************************************/
    /****************************************Upload Profile Api******************************************************/
    private void uploadProfileApi() {
        String vendorId = User.getUser().getVendorId();
        RequestBody _id = RequestBody.create(MediaType.parse("text/plain"), vendorId);
        RequestBody _image = RequestBody.create(MediaType.parse("image/*"), imageFile);

        MultipartBody.Part imageUpload = MultipartBody.Part.createFormData("image", imageFile.getName(), _image);

        if (cd.isNetworkAvailable()) {
            RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.uploadProfile(_id, imageUpload), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (!jsonObject.getBoolean("error")) {
                            Alerts.show(findViewById(R.id.llContainer), jsonObject.getString("message"));
                            userProfileData();
                        } else {
                            Alerts.show(findViewById(R.id.llContainer), jsonObject.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(findViewById(R.id.llContainer), error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

}
