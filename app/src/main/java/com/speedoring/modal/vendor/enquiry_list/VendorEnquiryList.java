package com.speedoring.modal.vendor.enquiry_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorEnquiryList implements Parcelable {

    @SerializedName("product_enquiry_id")
    @Expose
    private String productEnquiryId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("enquiry_about")
    @Expose
    private String enquiryAbout;
    @SerializedName("listing_id")
    @Expose
    private String listingId;
    @SerializedName("listing_name")
    @Expose
    private String listingName;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    public final static Parcelable.Creator<VendorEnquiryList> CREATOR = new Creator<VendorEnquiryList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorEnquiryList createFromParcel(Parcel in) {
            return new VendorEnquiryList(in);
        }

        public VendorEnquiryList[] newArray(int size) {
            return (new VendorEnquiryList[size]);
        }

    };

    protected VendorEnquiryList(Parcel in) {
        this.productEnquiryId = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
        this.emailId = ((String) in.readValue((String.class.getClassLoader())));
        this.enquiryAbout = ((String) in.readValue((String.class.getClassLoader())));
        this.listingId = ((String) in.readValue((String.class.getClassLoader())));
        this.listingName = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorEnquiryList() {
    }

    public String getProductEnquiryId() {
        return productEnquiryId;
    }

    public void setProductEnquiryId(String productEnquiryId) {
        this.productEnquiryId = productEnquiryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEnquiryAbout() {
        return enquiryAbout;
    }

    public void setEnquiryAbout(String enquiryAbout) {
        this.enquiryAbout = enquiryAbout;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productEnquiryId);
        dest.writeValue(userId);
        dest.writeValue(categoryId);
        dest.writeValue(name);
        dest.writeValue(mobileNo);
        dest.writeValue(emailId);
        dest.writeValue(enquiryAbout);
        dest.writeValue(listingId);
        dest.writeValue(listingName);
        dest.writeValue(createdDate);
    }

    public int describeContents() {
        return 0;
    }

}