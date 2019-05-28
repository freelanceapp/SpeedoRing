package com.speedoring.modal.product_detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductImage implements Parcelable {

    @SerializedName("listing_img_id")
    @Expose
    private String listingImgId;
    @SerializedName("prodcut_image")
    @Expose
    private String prodcutImage;
    public final static Parcelable.Creator<ProductImage> CREATOR = new Creator<ProductImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductImage createFromParcel(Parcel in) {
            return new ProductImage(in);
        }

        public ProductImage[] newArray(int size) {
            return (new ProductImage[size]);
        }

    };

    protected ProductImage(Parcel in) {
        this.listingImgId = ((String) in.readValue((String.class.getClassLoader())));
        this.prodcutImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductImage() {
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