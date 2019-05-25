package com.speedoring.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constant {

    public static final String BASE_URL = "http://brotherforlife.in/speedoring/api/";

    public static final String BANNER = "androidapi/banner.php";
    public static final String SERVICE_CATEGORY = "select-services-category.php";
    public static final String SERVICE_LIST = "select-services.php";
    public static final String SERVICE_ENQUIRY = "submit-enquiry.php";
    public static final String PRODUCT_CATEGORY = "select-product-category.php";
    public static final String PRODUCT_SUB_CATEGORY = "select-product-sub-category.php";
    public static final String PRODUCT_LIST_HOME = "select-product-home.php";

    public static final String ALL_STORE = "androidapi/all-store.php";
    public static final String LOGIN_API = "user-login.php";
    public static final String COUPON = "androidapi/coupon.php";
    public static final String NOTIFICATION_LIST = "notification.php";

    /* Fragment tag */
    public static final String HomeFragment = "HomeFragment";
    public static final String UserAllCategoryFragment = "UserAllCategoryFragment";
    public static final String VendorHomeFragment = "VendorHomeFragment";
    public static final String VendorListingFragment = "VendorListingFragment";
    public static final String VendorMyEnquiryFragment = "VendorMyEnquiryFragment";

    /* Preference */
    public static final String TOKEN = "TOKEN";

    /************************************************************************/
    public static final String IMAGE = "https://www.grabbuddy.in/admin/images/company_picture/";
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