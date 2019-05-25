package com.speedoring.modal.product_sub_category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductSubCategoryMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sub_category")
    @Expose
    private List<ProductSubCategory> subCategory = new ArrayList<ProductSubCategory>();
    public final static Parcelable.Creator<ProductSubCategoryMainModal> CREATOR = new Creator<ProductSubCategoryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductSubCategoryMainModal createFromParcel(Parcel in) {
            return new ProductSubCategoryMainModal(in);
        }

        public ProductSubCategoryMainModal[] newArray(int size) {
            return (new ProductSubCategoryMainModal[size]);
        }

    };

    protected ProductSubCategoryMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.subCategory, (ProductSubCategory.class.getClassLoader()));
    }

    public ProductSubCategoryMainModal() {
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

    public List<ProductSubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<ProductSubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(subCategory);
    }

    public int describeContents() {
        return 0;
    }

}