package com.speedoring.modal.product_detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product_listing")
    @Expose
    private ProductListing productListing;
    public final static Parcelable.Creator<ProductDetailMainModal> CREATOR = new Creator<ProductDetailMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductDetailMainModal createFromParcel(Parcel in) {
            return new ProductDetailMainModal(in);
        }

        public ProductDetailMainModal[] newArray(int size) {
            return (new ProductDetailMainModal[size]);
        }

    };

    protected ProductDetailMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.productListing = ((ProductListing) in.readValue((ProductListing.class.getClassLoader())));
    }

    public ProductDetailMainModal() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductListing getProductListing() {
        return productListing;
    }

    public void setProductListing(ProductListing productListing) {
        this.productListing = productListing;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(productListing);
    }

    public int describeContents() {
        return 0;
    }

}