package com.speedoring.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constant {

    public static final String BASE_URL = "http://brotherforlife.in/speedoring/api/";

    public static final String BANNER = "banner.php";
    /*USER*/
    public static final String SERVICE_CATEGORY = "select-services-category.php";
    public static final String SERVICE_LIST = "select-services.php";
    public static final String SERVICE_ENQUIRY = "submit-enquiry.php";
    public static final String PRODUCT_CATEGORY = "select-product-category.php";
    public static final String PRODUCT_SUB_CATEGORY = "select-product-sub-category.php";
    public static final String PRODUCT_LIST_HOME = "select-product-home.php";
    public static final String PRODUCT_LIST = "select-product.php";
    public static final String PRODUCT_DETAIL = "select-product-details.php";
    /*VENDOR*/
    public static final String VENDOR_LOGIN = "vendor-login.php";
    public static final String VENDOR_SIGN_UP = "vendor-signup.php";
    public static final String FORGOT_PASSWORD = "vendor-reset-password.php";
    public static final String OTP_VERIFY = "vendor-submit-otp.php";
    public static final String PROFILE_DATA = "vendor-profile.php";
    public static final String UPLOAD_PROFILE_IMAGE = "vendor-profile-image-update.php";
    public static final String UPDATE_PERSONAL_INFO = "vendor-profile-update.php";
    public static final String UPDATE_BUSINESS_INFO = "vendor-profile-business-update.php";
    public static final String UPDATE_CONTACT_INFO = "vendor-profile-contact.php";
    public static final String VENDOR_PRODUCT_LIST = "select-vendor-product.php";
    public static final String VENDOR_PRODUCT_DELETE = "vendor-listing-delete.php";
    public static final String VENDOR_ADD_PRODUCT = "vendor-listing-insert.php";
    public static final String VENDOR_ENQUIRY_LIST = "vendor-enquiry-listing.php";

    /* Fragment tag */
    public static final String HomeFragment = "HomeFragment";
    public static final String UserAllCategoryFragment = "UserAllCategoryFragment";
    public static final String VendorHomeFragment = "VendorHomeFragment";
    public static final String VendorListingFragment = "VendorListingFragment";
    public static final String VendorMyEnquiryFragment = "VendorMyEnquiryFragment";
    public static final String VendorNotificationFragment = "VendorNotificationFragment";
    public static final String VendorPrivacyFragment = "VendorPrivacyFragment";

    /* Preference */
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String IS_ITEM_ADDED = "IS_ITEM_ADDED";
    public static final String IS_VENDOR_DATA_UPDATE = "IS_VENDOR_DATA_UPDATE";
    public static final String VENDOR_DATA = "VENDOR_DATA";
    public static final String TOKEN = "TOKEN";

    /************************************************************************/
    // public static final String IMAGE = "https://www.grabbuddy.in/admin/images/company_picture/";
    public static final String IMAGE5 = "https://www.grabbuddy.in/admin/images/bannar_picture/";

    public static boolean isValidEmailId(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

/**/