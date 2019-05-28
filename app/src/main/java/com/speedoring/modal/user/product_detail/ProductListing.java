package com.speedoring.modal.user.product_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductListing implements Parcelable {

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
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("vednor_first_name")
    @Expose
    private String vednorFirstName;
    @SerializedName("vednor_last_name")
    @Expose
    private String vednorLastName;
    @SerializedName("vednor_mobile_number")
    @Expose
    private String vednorMobileNumber;
    @SerializedName("vednor_address")
    @Expose
    private String vednorAddress;
    @SerializedName("vednor_city")
    @Expose
    private String vednorCity;
    @SerializedName("vednor_state")
    @Expose
    private String vednorState;
    @SerializedName("vednor_country")
    @Expose
    private String vednorCountry;
    @SerializedName("vednor_pincode")
    @Expose
    private String vednorPincode;
    @SerializedName("vednor_about")
    @Expose
    private String vednorAbout;
    @SerializedName("vednor_business_name")
    @Expose
    private String vednorBusinessName;
    @SerializedName("vednor_business_nature")
    @Expose
    private String vednorBusinessNature;
    @SerializedName("vednor_business_tin")
    @Expose
    private String vednorBusinessTin;
    @SerializedName("vednor_check_same")
    @Expose
    private String vednorCheckSame;
    @SerializedName("vednor_business_address")
    @Expose
    private String vednorBusinessAddress;
    @SerializedName("vednor_business_city")
    @Expose
    private String vednorBusinessCity;
    @SerializedName("vednor_business_area")
    @Expose
    private String vednorBusinessArea;
    @SerializedName("vednor_business_country")
    @Expose
    private String vednorBusinessCountry;
    @SerializedName("vednor_landline_one")
    @Expose
    private String vednorLandlineOne;
    @SerializedName("vednor_landline_two")
    @Expose
    private String vednorLandlineTwo;
    @SerializedName("vednor_mobile_one")
    @Expose
    private String vednorMobileOne;
    @SerializedName("vednor_mobile_two")
    @Expose
    private String vednorMobileTwo;
    @SerializedName("vednor_email")
    @Expose
    private String vednorEmail;
    @SerializedName("vednor_website")
    @Expose
    private String vednorWebsite;
    @SerializedName("vednor_category")
    @Expose
    private String vednorCategory;
    @SerializedName("vednor_business_keyword")
    @Expose
    private String vednorBusinessKeyword;
    @SerializedName("vednor_image")
    @Expose
    private String vednorImage;
    @SerializedName("vendor_type")
    @Expose
    private String vendorType;
    @SerializedName("product_images")
    @Expose
    private List<ProductImage> productImages = new ArrayList<ProductImage>();
    public final static Parcelable.Creator<ProductListing> CREATOR = new Creator<ProductListing>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductListing createFromParcel(Parcel in) {
            return new ProductListing(in);
        }

        public ProductListing[] newArray(int size) {
            return (new ProductListing[size]);
        }

    };

    protected ProductListing(Parcel in) {
        this.listingId = ((String) in.readValue((String.class.getClassLoader())));
        this.listingName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.catName = ((String) in.readValue((String.class.getClassLoader())));
        this.catImage = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.subCatName = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryImages = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorId = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorFirstName = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorLastName = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorMobileNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorCity = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorState = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorPincode = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorAbout = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorBusinessName = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorBusinessNature = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorBusinessTin = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorCheckSame = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorBusinessAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorBusinessCity = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorBusinessArea = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorBusinessCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorLandlineOne = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorLandlineTwo = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorMobileOne = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorMobileTwo = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorWebsite = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorBusinessKeyword = ((String) in.readValue((String.class.getClassLoader())));
        this.vednorImage = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productImages, (com.speedoring.modal.user.product_detail.ProductImage.class.getClassLoader()));
    }

    public ProductListing() {
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVednorFirstName() {
        return vednorFirstName;
    }

    public void setVednorFirstName(String vednorFirstName) {
        this.vednorFirstName = vednorFirstName;
    }

    public String getVednorLastName() {
        return vednorLastName;
    }

    public void setVednorLastName(String vednorLastName) {
        this.vednorLastName = vednorLastName;
    }

    public String getVednorMobileNumber() {
        return vednorMobileNumber;
    }

    public void setVednorMobileNumber(String vednorMobileNumber) {
        this.vednorMobileNumber = vednorMobileNumber;
    }

    public String getVednorAddress() {
        return vednorAddress;
    }

    public void setVednorAddress(String vednorAddress) {
        this.vednorAddress = vednorAddress;
    }

    public String getVednorCity() {
        return vednorCity;
    }

    public void setVednorCity(String vednorCity) {
        this.vednorCity = vednorCity;
    }

    public String getVednorState() {
        return vednorState;
    }

    public void setVednorState(String vednorState) {
        this.vednorState = vednorState;
    }

    public String getVednorCountry() {
        return vednorCountry;
    }

    public void setVednorCountry(String vednorCountry) {
        this.vednorCountry = vednorCountry;
    }

    public String getVednorPincode() {
        return vednorPincode;
    }

    public void setVednorPincode(String vednorPincode) {
        this.vednorPincode = vednorPincode;
    }

    public String getVednorAbout() {
        return vednorAbout;
    }

    public void setVednorAbout(String vednorAbout) {
        this.vednorAbout = vednorAbout;
    }

    public String getVednorBusinessName() {
        return vednorBusinessName;
    }

    public void setVednorBusinessName(String vednorBusinessName) {
        this.vednorBusinessName = vednorBusinessName;
    }

    public String getVednorBusinessNature() {
        return vednorBusinessNature;
    }

    public void setVednorBusinessNature(String vednorBusinessNature) {
        this.vednorBusinessNature = vednorBusinessNature;
    }

    public String getVednorBusinessTin() {
        return vednorBusinessTin;
    }

    public void setVednorBusinessTin(String vednorBusinessTin) {
        this.vednorBusinessTin = vednorBusinessTin;
    }

    public String getVednorCheckSame() {
        return vednorCheckSame;
    }

    public void setVednorCheckSame(String vednorCheckSame) {
        this.vednorCheckSame = vednorCheckSame;
    }

    public String getVednorBusinessAddress() {
        return vednorBusinessAddress;
    }

    public void setVednorBusinessAddress(String vednorBusinessAddress) {
        this.vednorBusinessAddress = vednorBusinessAddress;
    }

    public String getVednorBusinessCity() {
        return vednorBusinessCity;
    }

    public void setVednorBusinessCity(String vednorBusinessCity) {
        this.vednorBusinessCity = vednorBusinessCity;
    }

    public String getVednorBusinessArea() {
        return vednorBusinessArea;
    }

    public void setVednorBusinessArea(String vednorBusinessArea) {
        this.vednorBusinessArea = vednorBusinessArea;
    }

    public String getVednorBusinessCountry() {
        return vednorBusinessCountry;
    }

    public void setVednorBusinessCountry(String vednorBusinessCountry) {
        this.vednorBusinessCountry = vednorBusinessCountry;
    }

    public String getVednorLandlineOne() {
        return vednorLandlineOne;
    }

    public void setVednorLandlineOne(String vednorLandlineOne) {
        this.vednorLandlineOne = vednorLandlineOne;
    }

    public String getVednorLandlineTwo() {
        return vednorLandlineTwo;
    }

    public void setVednorLandlineTwo(String vednorLandlineTwo) {
        this.vednorLandlineTwo = vednorLandlineTwo;
    }

    public String getVednorMobileOne() {
        return vednorMobileOne;
    }

    public void setVednorMobileOne(String vednorMobileOne) {
        this.vednorMobileOne = vednorMobileOne;
    }

    public String getVednorMobileTwo() {
        return vednorMobileTwo;
    }

    public void setVednorMobileTwo(String vednorMobileTwo) {
        this.vednorMobileTwo = vednorMobileTwo;
    }

    public String getVednorEmail() {
        return vednorEmail;
    }

    public void setVednorEmail(String vednorEmail) {
        this.vednorEmail = vednorEmail;
    }

    public String getVednorWebsite() {
        return vednorWebsite;
    }

    public void setVednorWebsite(String vednorWebsite) {
        this.vednorWebsite = vednorWebsite;
    }

    public String getVednorCategory() {
        return vednorCategory;
    }

    public void setVednorCategory(String vednorCategory) {
        this.vednorCategory = vednorCategory;
    }

    public String getVednorBusinessKeyword() {
        return vednorBusinessKeyword;
    }

    public void setVednorBusinessKeyword(String vednorBusinessKeyword) {
        this.vednorBusinessKeyword = vednorBusinessKeyword;
    }

    public String getVednorImage() {
        return vednorImage;
    }

    public void setVednorImage(String vednorImage) {
        this.vednorImage = vednorImage;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
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
        dest.writeValue(mobileNumber);
        dest.writeValue(vendorId);
        dest.writeValue(vednorFirstName);
        dest.writeValue(vednorLastName);
        dest.writeValue(vednorMobileNumber);
        dest.writeValue(vednorAddress);
        dest.writeValue(vednorCity);
        dest.writeValue(vednorState);
        dest.writeValue(vednorCountry);
        dest.writeValue(vednorPincode);
        dest.writeValue(vednorAbout);
        dest.writeValue(vednorBusinessName);
        dest.writeValue(vednorBusinessNature);
        dest.writeValue(vednorBusinessTin);
        dest.writeValue(vednorCheckSame);
        dest.writeValue(vednorBusinessAddress);
        dest.writeValue(vednorBusinessCity);
        dest.writeValue(vednorBusinessArea);
        dest.writeValue(vednorBusinessCountry);
        dest.writeValue(vednorLandlineOne);
        dest.writeValue(vednorLandlineTwo);
        dest.writeValue(vednorMobileOne);
        dest.writeValue(vednorMobileTwo);
        dest.writeValue(vednorEmail);
        dest.writeValue(vednorWebsite);
        dest.writeValue(vednorCategory);
        dest.writeValue(vednorBusinessKeyword);
        dest.writeValue(vednorImage);
        dest.writeValue(vendorType);
        dest.writeList(productImages);
    }

    public int describeContents() {
        return 0;
    }

}