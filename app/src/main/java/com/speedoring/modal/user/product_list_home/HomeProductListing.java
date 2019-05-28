package com.speedoring.modal.user.product_list_home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeProductListing implements Parcelable {

    @SerializedName("listing_id")
    @Expose
    private String listingId;
    @SerializedName("listing_name")
    @Expose
    private String listingName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;
    @SerializedName("sub_cat_name")
    @Expose
    private String subCatName;
    @SerializedName("sub_category_images")
    @Expose
    private String subCategoryImages;
    @SerializedName("cendor_mobile_number")
    @Expose
    private String cendorMobileNumber;
    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("vendor_address")
    @Expose
    private String vendorAddress;
    @SerializedName("vendor_city")
    @Expose
    private String vendorCity;
    @SerializedName("vendor_state")
    @Expose
    private String vendorState;
    @SerializedName("vendor_country")
    @Expose
    private String vendorCountry;
    @SerializedName("vendor_pincode")
    @Expose
    private String vendorPincode;
    @SerializedName("vendor_about")
    @Expose
    private String vendorAbout;
    @SerializedName("vendor_business_name")
    @Expose
    private String vendorBusinessName;
    @SerializedName("vendor_business_nature")
    @Expose
    private String vendorBusinessNature;
    @SerializedName("vendor_business_tin")
    @Expose
    private String vendorBusinessTin;
    @SerializedName("vendor_check_same")
    @Expose
    private String vendorCheckSame;
    @SerializedName("vendor_business_address")
    @Expose
    private String vendorBusinessAddress;
    @SerializedName("vendor_business_city")
    @Expose
    private String vendorBusinessCity;
    @SerializedName("vendor_business_area")
    @Expose
    private String vendorBusinessArea;
    @SerializedName("vendor_business_country")
    @Expose
    private String vendorBusinessCountry;
    @SerializedName("vendor_landline_one")
    @Expose
    private String vendorLandlineOne;
    @SerializedName("vendor_landline_two")
    @Expose
    private String vendorLandlineTwo;
    @SerializedName("vendor_mobile_one")
    @Expose
    private String vendorMobileOne;
    @SerializedName("vendor_mobile_two")
    @Expose
    private String vendorMobileTwo;
    @SerializedName("vendor_email")
    @Expose
    private String vendorEmail;
    @SerializedName("vendor_website")
    @Expose
    private String vendorWebsite;
    @SerializedName("product_images")
    @Expose
    private String productImages;
    public final static Parcelable.Creator<HomeProductListing> CREATOR = new Creator<HomeProductListing>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HomeProductListing createFromParcel(Parcel in) {
            return new HomeProductListing(in);
        }

        public HomeProductListing[] newArray(int size) {
            return (new HomeProductListing[size]);
        }

    };

    protected HomeProductListing(Parcel in) {
        this.listingId = ((String) in.readValue((String.class.getClassLoader())));
        this.listingName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.catName = ((String) in.readValue((String.class.getClassLoader())));
        this.catImage = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.subCatName = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryImages = ((String) in.readValue((String.class.getClassLoader())));
        this.cendorMobileNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorId = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorCity = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorState = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorPincode = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorAbout = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorBusinessName = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorBusinessNature = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorBusinessTin = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorCheckSame = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorBusinessAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorBusinessCity = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorBusinessArea = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorBusinessCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorLandlineOne = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorLandlineTwo = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorMobileOne = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorMobileTwo = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorWebsite = ((String) in.readValue((String.class.getClassLoader())));
        this.productImages = ((String) in.readValue((String.class.getClassLoader())));
    }

    public HomeProductListing() {
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getListingName() {
        return listingName;
    }

    public void setListingName(String listingName) {
        this.listingName = listingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getSubCategoryImages() {
        return subCategoryImages;
    }

    public void setSubCategoryImages(String subCategoryImages) {
        this.subCategoryImages = subCategoryImages;
    }

    public String getCendorMobileNumber() {
        return cendorMobileNumber;
    }

    public void setCendorMobileNumber(String cendorMobileNumber) {
        this.cendorMobileNumber = cendorMobileNumber;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorCity() {
        return vendorCity;
    }

    public void setVendorCity(String vendorCity) {
        this.vendorCity = vendorCity;
    }

    public String getVendorState() {
        return vendorState;
    }

    public void setVendorState(String vendorState) {
        this.vendorState = vendorState;
    }

    public String getVendorCountry() {
        return vendorCountry;
    }

    public void setVendorCountry(String vendorCountry) {
        this.vendorCountry = vendorCountry;
    }

    public String getVendorPincode() {
        return vendorPincode;
    }

    public void setVendorPincode(String vendorPincode) {
        this.vendorPincode = vendorPincode;
    }

    public String getVendorAbout() {
        return vendorAbout;
    }

    public void setVendorAbout(String vendorAbout) {
        this.vendorAbout = vendorAbout;
    }

    public String getVendorBusinessName() {
        return vendorBusinessName;
    }

    public void setVendorBusinessName(String vendorBusinessName) {
        this.vendorBusinessName = vendorBusinessName;
    }

    public String getVendorBusinessNature() {
        return vendorBusinessNature;
    }

    public void setVendorBusinessNature(String vendorBusinessNature) {
        this.vendorBusinessNature = vendorBusinessNature;
    }

    public String getVendorBusinessTin() {
        return vendorBusinessTin;
    }

    public void setVendorBusinessTin(String vendorBusinessTin) {
        this.vendorBusinessTin = vendorBusinessTin;
    }

    public String getVendorCheckSame() {
        return vendorCheckSame;
    }

    public void setVendorCheckSame(String vendorCheckSame) {
        this.vendorCheckSame = vendorCheckSame;
    }

    public String getVendorBusinessAddress() {
        return vendorBusinessAddress;
    }

    public void setVendorBusinessAddress(String vendorBusinessAddress) {
        this.vendorBusinessAddress = vendorBusinessAddress;
    }

    public String getVendorBusinessCity() {
        return vendorBusinessCity;
    }

    public void setVendorBusinessCity(String vendorBusinessCity) {
        this.vendorBusinessCity = vendorBusinessCity;
    }

    public String getVendorBusinessArea() {
        return vendorBusinessArea;
    }

    public void setVendorBusinessArea(String vendorBusinessArea) {
        this.vendorBusinessArea = vendorBusinessArea;
    }

    public String getVendorBusinessCountry() {
        return vendorBusinessCountry;
    }

    public void setVendorBusinessCountry(String vendorBusinessCountry) {
        this.vendorBusinessCountry = vendorBusinessCountry;
    }

    public String getVendorLandlineOne() {
        return vendorLandlineOne;
    }

    public void setVendorLandlineOne(String vendorLandlineOne) {
        this.vendorLandlineOne = vendorLandlineOne;
    }

    public String getVendorLandlineTwo() {
        return vendorLandlineTwo;
    }

    public void setVendorLandlineTwo(String vendorLandlineTwo) {
        this.vendorLandlineTwo = vendorLandlineTwo;
    }

    public String getVendorMobileOne() {
        return vendorMobileOne;
    }

    public void setVendorMobileOne(String vendorMobileOne) {
        this.vendorMobileOne = vendorMobileOne;
    }

    public String getVendorMobileTwo() {
        return vendorMobileTwo;
    }

    public void setVendorMobileTwo(String vendorMobileTwo) {
        this.vendorMobileTwo = vendorMobileTwo;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorWebsite() {
        return vendorWebsite;
    }

    public void setVendorWebsite(String vendorWebsite) {
        this.vendorWebsite = vendorWebsite;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(listingId);
        dest.writeValue(listingName);
        dest.writeValue(description);
        dest.writeValue(categoryId);
        dest.writeValue(catName);
        dest.writeValue(catImage);
        dest.writeValue(subCategoryId);
        dest.writeValue(subCatName);
        dest.writeValue(subCategoryImages);
        dest.writeValue(cendorMobileNumber);
        dest.writeValue(vendorId);
        dest.writeValue(vendorAddress);
        dest.writeValue(vendorCity);
        dest.writeValue(vendorState);
        dest.writeValue(vendorCountry);
        dest.writeValue(vendorPincode);
        dest.writeValue(vendorAbout);
        dest.writeValue(vendorBusinessName);
        dest.writeValue(vendorBusinessNature);
        dest.writeValue(vendorBusinessTin);
        dest.writeValue(vendorCheckSame);
        dest.writeValue(vendorBusinessAddress);
        dest.writeValue(vendorBusinessCity);
        dest.writeValue(vendorBusinessArea);
        dest.writeValue(vendorBusinessCountry);
        dest.writeValue(vendorLandlineOne);
        dest.writeValue(vendorLandlineTwo);
        dest.writeValue(vendorMobileOne);
        dest.writeValue(vendorMobileTwo);
        dest.writeValue(vendorEmail);
        dest.writeValue(vendorWebsite);
        dest.writeValue(productImages);
    }

    public int describeContents() {
        return 0;
    }

}
