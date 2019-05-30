package com.speedoring.modal.vendor.vendor_product_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VendorProductListMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product_listing")
    @Expose
    private List<VendorProductListing> productListing = new ArrayList<VendorProductListing>();
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;
    @SerializedName("current_page")
    @Expose
    private String currentPage;
    public final static Parcelable.Creator<VendorProductListMainModal> CREATOR = new Creator<VendorProductListMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorProductListMainModal createFromParcel(Parcel in) {
            return new VendorProductListMainModal(in);
        }

        public VendorProductListMainModal[] newArray(int size) {
            return (new VendorProductListMainModal[size]);
        }

    };

    protected VendorProductListMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productListing, (VendorProductListing.class.getClassLoader()));
        this.pageCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currentPage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorProductListMainModal() {
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

    public List<VendorProductListing> getProductListing() {
        return productListing;
    }

    public void setProductListing(List<VendorProductListing> productListing) {
        this.productListing = productListing;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(productListing);
        dest.writeValue(pageCount);
        dest.writeValue(currentPage);
    }

    public int describeContents() {
        return 0;
    }

}