package com.speedoring.modal.vendor.login_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vendorinfo implements Parcelable {

    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("business_name")
    @Expose
    private String businessName;
    @SerializedName("business_nature")
    @Expose
    private String businessNature;
    @SerializedName("business_tin")
    @Expose
    private String businessTin;
    @SerializedName("check_same")
    @Expose
    private String checkSame;
    @SerializedName("business_address")
    @Expose
    private String businessAddress;
    @SerializedName("business_city")
    @Expose
    private String businessCity;
    @SerializedName("business_area")
    @Expose
    private String businessArea;
    @SerializedName("business_country")
    @Expose
    private String businessCountry;
    @SerializedName("landline_one")
    @Expose
    private String landlineOne;
    @SerializedName("landline_two")
    @Expose
    private String landlineTwo;
    @SerializedName("mobile_one")
    @Expose
    private String mobileOne;
    @SerializedName("mobile_two")
    @Expose
    private String mobileTwo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("business_keyword")
    @Expose
    private String businessKeyword;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("vendor_type")
    @Expose
    private String vendorType;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<Vendorinfo> CREATOR = new Creator<Vendorinfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Vendorinfo createFromParcel(Parcel in) {
            return new Vendorinfo(in);
        }

        public Vendorinfo[] newArray(int size) {
            return (new Vendorinfo[size]);
        }

    };

    protected Vendorinfo(Parcel in) {
        this.vendorId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
        this.about = ((String) in.readValue((String.class.getClassLoader())));
        this.businessName = ((String) in.readValue((String.class.getClassLoader())));
        this.businessNature = ((String) in.readValue((String.class.getClassLoader())));
        this.businessTin = ((String) in.readValue((String.class.getClassLoader())));
        this.checkSame = ((String) in.readValue((String.class.getClassLoader())));
        this.businessAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.businessCity = ((String) in.readValue((String.class.getClassLoader())));
        this.businessArea = ((String) in.readValue((String.class.getClassLoader())));
        this.businessCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.landlineOne = ((String) in.readValue((String.class.getClassLoader())));
        this.landlineTwo = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileOne = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileTwo = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.website = ((String) in.readValue((String.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.businessKeyword = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorType = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Vendorinfo() {
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessNature() {
        return businessNature;
    }

    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }

    public String getBusinessTin() {
        return businessTin;
    }

    public void setBusinessTin(String businessTin) {
        this.businessTin = businessTin;
    }

    public String getCheckSame() {
        return checkSame;
    }

    public void setCheckSame(String checkSame) {
        this.checkSame = checkSame;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getBusinessCountry() {
        return businessCountry;
    }

    public void setBusinessCountry(String businessCountry) {
        this.businessCountry = businessCountry;
    }

    public String getLandlineOne() {
        return landlineOne;
    }

    public void setLandlineOne(String landlineOne) {
        this.landlineOne = landlineOne;
    }

    public String getLandlineTwo() {
        return landlineTwo;
    }

    public void setLandlineTwo(String landlineTwo) {
        this.landlineTwo = landlineTwo;
    }

    public String getMobileOne() {
        return mobileOne;
    }

    public void setMobileOne(String mobileOne) {
        this.mobileOne = mobileOne;
    }

    public String getMobileTwo() {
        return mobileTwo;
    }

    public void setMobileTwo(String mobileTwo) {
        this.mobileTwo = mobileTwo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBusinessKeyword() {
        return businessKeyword;
    }

    public void setBusinessKeyword(String businessKeyword) {
        this.businessKeyword = businessKeyword;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(vendorId);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(mobileNumber);
        dest.writeValue(address);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(country);
        dest.writeValue(pincode);
        dest.writeValue(about);
        dest.writeValue(businessName);
        dest.writeValue(businessNature);
        dest.writeValue(businessTin);
        dest.writeValue(checkSame);
        dest.writeValue(businessAddress);
        dest.writeValue(businessCity);
        dest.writeValue(businessArea);
        dest.writeValue(businessCountry);
        dest.writeValue(landlineOne);
        dest.writeValue(landlineTwo);
        dest.writeValue(mobileOne);
        dest.writeValue(mobileTwo);
        dest.writeValue(email);
        dest.writeValue(website);
        dest.writeValue(category);
        dest.writeValue(businessKeyword);
        dest.writeValue(image);
        dest.writeValue(vendorType);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
