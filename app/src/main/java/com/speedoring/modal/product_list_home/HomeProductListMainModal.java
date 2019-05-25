package com.speedoring.modal.product_list_home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HomeProductListMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product_listing")
    @Expose
    private List<HomeProductListing> productListing = new ArrayList<HomeProductListing>();
    public final static Parcelable.Creator<HomeProductListMainModal> CREATOR = new Creator<HomeProductListMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HomeProductListMainModal createFromParcel(Parcel in) {
            return new HomeProductListMainModal(in);
        }

        public HomeProductListMainModal[] newArray(int size) {
            return (new HomeProductListMainModal[size]);
        }

    };

    protected HomeProductListMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productListing, (HomeProductListing.class.getClassLoader()));
    }

    public HomeProductListMainModal() {
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

    public List<HomeProductListing> getProductListing() {
        return productListing;
    }

    public void setProductListing(List<HomeProductListing> productListing) {
        this.productListing = productListing;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(productListing);
    }

    public int describeContents() {
        return 0;
    }

}