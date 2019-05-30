package com.speedoring.modal.vendor.vendor_product_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorProductImage implements Parcelable {

    @SerializedName("listing_img_id")
    @Expose
    private String listingImgId;
    @SerializedName("prodcut_image")
    @Expose
    private String prodcutImage;
    public final static Parcelable.Creator<VendorProductImage> CREATOR = new Creator<VendorProductImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorProductImage createFromParcel(Parcel in) {
            return new VendorProductImage(in);
        }

        public VendorProductImage[] newArray(int size) {
            return (new VendorProductImage[size]);
        }

    };

    protected VendorProductImage(Parcel in) {
        this.listingImgId = ((String) in.readValue((String.class.getClassLoader())));
        this.prodcutImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorProductImage() {
    }

    public String getListingImgId() {
        return listingImgId;
    }

    public void setListingImgId(String listingImgId) {
        this.listingImgId = listingImgId;
    }

    public String getProdcutImage() {
        return prodcutImage;
    }

    public void setProdcutImage(String prodcutImage) {
        this.prodcutImage = prodcutImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(listingImgId);
        dest.writeValue(prodcutImage);
    }

    public int describeContents() {
        return 0;
    }

}