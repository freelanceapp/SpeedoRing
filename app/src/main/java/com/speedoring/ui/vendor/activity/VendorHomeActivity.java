package com.speedoring.ui.vendor.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.speedoring.R;
import com.speedoring.constant.Constant;
import com.speedoring.modal.User;
import com.speedoring.ui.user.activity.UserHomeActivity;
import com.speedoring.ui.vendor.fragment.VendorHomeFragment;
import com.speedoring.ui.vendor.fragment.VendorListingFragment;
import com.speedoring.ui.vendor.fragment.VendorMyEnquiryFragment;
import com.speedoring.ui.vendor.fragment.VendorNotificationFragment;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.AppPreference;
import com.speedoring.utils.BaseActivity;
import com.speedoring.utils.FragmentUtils;
import com.speedoring.utils.ImageUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorHomeActivity extends BaseActivity implements View.OnClickListener {

    boolean doubleBackToExitPressedOnce = false;

    private View contentView;
    private DrawerLayout drawerLayout;
    public static Toolbar toolbar;

    private TextView txtTitle;
    public static FragmentUtils fragmentUtils;
    private FragmentManager fragmentManager;
    private ImageView imgDropDown;
    private boolean isRotate = false;
    private LinearLayout llMenu;
    private ExpandingList mExpandingList;

    /******************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_activity_home);

        initNavigation();
    }

    private void initNavigation() {
        mExpandingList = findViewById(R.id.expanding_list_main);
        addItem("Listing", new String[]{"View Listing", "Add Listing"}, R.color.gray_h, R.drawable.ic_service);
        imgDropDown = findViewById(R.id.imgDropDown);
        llMenu = findViewById(R.id.llMenu);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Home");

        findViewById(R.id.tvProfile).setOnClickListener(this);
        findViewById(R.id.llHome).setOnClickListener(this);
        findViewById(R.id.rlListing).setOnClickListener(this);
        findViewById(R.id.tvAddListing).setOnClickListener(this);
        findViewById(R.id.tvListing).setOnClickListener(this);
        findViewById(R.id.txtMyEnquiries).setOnClickListener(this);
        findViewById(R.id.llBuyLeads).setOnClickListener(this);
        findViewById(R.id.txtNotification).setOnClickListener(this);
        findViewById(R.id.txtLogout).setOnClickListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        contentView = findViewById(R.id.container);

        ImageUtil.Glide(mContext, ((CircleImageView) findViewById(R.id.imgVendorProfile)), User.getUser().getImage());
        ((TextView) findViewById(R.id.txtVendorName)).setText(User.getUser().getFirstName() + " " + User.getUser().getLastName());
        ((TextView) findViewById(R.id.txtContact)).setText(User.getUser().getMobileNumber());

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

        initFragment();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);
        txtTitle.setText("Home");
        fragmentUtils.replaceFragment(new VendorHomeFragment(), Constant.VendorHomeFragment, R.id.frameLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAddListing:
                drawerLayout.closeDrawer(GravityCompat.START);
                openPopup();
                break;
            case R.id.llHome:
                drawerLayout.closeDrawer(GravityCompat.START);
                txtTitle.setText("Home");
                fragmentUtils.replaceFragment(new VendorHomeFragment(), Constant.VendorHomeFragment, R.id.frameLayout);
                break;
            case R.id.tvListing:
                drawerLayout.closeDrawer(GravityCompat.START);
                txtTitle.setText("Listing");
                fragmentUtils.replaceFragment(new VendorListingFragment(), Constant.VendorListingFragment, R.id.frameLayout);
                break;
            case R.id.txtMyEnquiries:
                drawerLayout.closeDrawer(GravityCompat.START);
                txtTitle.setText("My VendorEnquiryList");
                fragmentUtils.replaceFragment(new VendorMyEnquiryFragment(), Constant.VendorMyEnquiryFragment, R.id.frameLayout);
                break;
            case R.id.txtNotification:
                drawerLayout.closeDrawer(GravityCompat.START);
                txtTitle.setText("Notification");
                fragmentUtils.replaceFragment(new VendorNotificationFragment(), Constant.VendorNotificationFragment, R.id.frameLayout);
                break;
            case R.id.llBuyLeads:
                drawerLayout.closeDrawer(GravityCompat.START);
                Alerts.show(mContext, "Under development...!!!");
                /*txtTitle.setText("Notification");
                fragmentUtils.replaceFragment(new VendorMyEnquiryFragment(), Constant.VendorMyEnquiryFragment, R.id.frameLayout);*/
                break;
            case R.id.tvProfile:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(mContext, VendorProfileActivity.class));
                break;
            case R.id.txtLogout:
                logout();
                break;
            case R.id.rlListing:
                if (isRotate) {
                    imgDropDown.animate().rotation(0).setDuration(100);
                    isRotate = false;
                    llMenu.setVisibility(View.GONE);
                } else {
                    llMenu.setVisibility(View.VISIBLE);
                    imgDropDown.animate().rotation(90).setDuration(100);
                    isRotate = true;
                }
                break;
        }
    }

    private void logout() {
        new AlertDialog.Builder(mContext)
                .setTitle("Logout")
                .setMessage("Are you sure want to logout ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppPreference.clearAllPreferences(mContext);
                        Intent intent = new Intent(mContext, UserHomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .create()
                .show();
    }

    private void openPopup() {
        final Dialog dialogAddListing = new Dialog(mContext);
        dialogAddListing.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddListing.setContentView(R.layout.dialog_add_listing);

        dialogAddListing.setCanceledOnTouchOutside(true);
        dialogAddListing.setCancelable(true);
        if (dialogAddListing.getWindow() != null)
            dialogAddListing.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialogAddListing.findViewById(R.id.btnCreateListing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddListing.dismiss();
                txtTitle.setText("Listing");
                fragmentUtils.replaceFragment(new VendorListingFragment(), Constant.VendorListingFragment, R.id.frameLayout);
            }
        });

        Window window = dialogAddListing.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialogAddListing.show();
    }

    /***********************************************************************************************/
    /* Expandable Layout */
    private void addItem(String title, String[] subItems, int colorRes, int iconRes) {
        final ExpandingItem item = mExpandingList.createNewItem(R.layout.expanding_layout);
        if (item != null) {
            item.setIndicatorColorRes(colorRes);
            item.setIndicatorIconRes(iconRes);
            ((TextView) item.findViewById(R.id.title)).setText(title);
            item.createSubItems(subItems.length);
            for (int i = 0; i < item.getSubItemsCount(); i++) {
                final View view = item.getSubItemView(i);
                configureSubItem(view, subItems[i]);
            }
        }
    }

    private void configureSubItem(final View view, String subTitle) {
        ((TextView) view.findViewById(R.id.sub_title)).setText(subTitle);
        view.findViewById(R.id.sub_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = ((TextView) view.findViewById(R.id.sub_title)).getText().toString();
                switch (txt) {
                    case "Add Listing":
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(mContext, VendorAddListingActivity.class);
                        startActivity(intent);
                        break;
                    case "View Listing":
                        drawerLayout.closeDrawer(GravityCompat.START);
                        txtTitle.setText("Listing");
                        fragmentUtils.replaceFragment(new VendorListingFragment(), Constant.VendorListingFragment, R.id.frameLayout);
                        break;
                }
            }
        });
    }

    /**********************************************************************************************/
    @Override
    protected void onResume() {
        super.onResume();
        if (AppPreference.getBooleanPreference(mContext, Constant.IS_ITEM_ADDED)) {
            AppPreference.setBooleanPreference(mContext, Constant.IS_ITEM_ADDED, false);
            txtTitle.setText("Listing");
            fragmentUtils.replaceFragment(new VendorListingFragment(), Constant.VendorListingFragment, R.id.frameLayout);
        }

        if (AppPreference.getBooleanPreference(mContext, Constant.IS_VENDOR_DATA_UPDATE)) {
            AppPreference.setBooleanPreference(mContext, Constant.IS_VENDOR_DATA_UPDATE, false);
            ImageUtil.Glide(mContext, ((CircleImageView) findViewById(R.id.imgVendorProfile)), User.getUser().getImage());
            ((TextView) findViewById(R.id.txtVendorName)).setText(User.getUser().getFirstName() + " " + User.getUser().getLastName());
            ((TextView) findViewById(R.id.txtContact)).setText(User.getUser().getMobileNumber());
        }
    }

    @Override
    public void onBackPressed() {
        Fragment HomeFragment = fragmentManager.findFragmentByTag(Constant.HomeFragment);
        Fragment VendorListingFragmentTag = fragmentManager.findFragmentByTag(Constant.VendorListingFragment);
        Fragment VendorMyEnquiryFragmentTag = fragmentManager.findFragmentByTag(Constant.VendorMyEnquiryFragment);
        Fragment VendorNotificationFragmentTag = fragmentManager.findFragmentByTag(Constant.VendorNotificationFragment);

        if (VendorListingFragmentTag != null) {
            txtTitle.setText("Home");
            fragmentUtils.replaceFragment(new VendorHomeFragment(), Constant.VendorHomeFragment, R.id.frameLayout);
        } else if (VendorMyEnquiryFragmentTag != null) {
            txtTitle.setText("Home");
            fragmentUtils.replaceFragment(new VendorHomeFragment(), Constant.VendorHomeFragment, R.id.frameLayout);
        } else if (VendorNotificationFragmentTag != null) {
            txtTitle.setText("Home");
            fragmentUtils.replaceFragment(new VendorHomeFragment(), Constant.VendorHomeFragment, R.id.frameLayout);
        } else if (HomeFragment != null) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

}
