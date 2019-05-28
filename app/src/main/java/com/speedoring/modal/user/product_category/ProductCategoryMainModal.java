package com.speedoring.modal.user.product_category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("category")
    @Expose
    private List<ProductCategoryList> category = new ArrayList<ProductCategoryList>();
    public final static Parcelable.Creator<ProductCategoryMainModal> CREATOR = new Creator<ProductCategoryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductCategoryMainModal createFromParcel(Parcel in) {
            return new ProductCategoryMainModal(in);
        }

        public ProductCategoryMainModal[] newArray(int size) {
            return (new ProductCategoryMainModal[size]);
        }

    };

    protected ProductCategoryMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.category, (ProductCategoryList.class.getClassLoader()));
    }

    public ProductCategoryMainModal() {
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

    public List<ProductCategoryList> getCategory() {
        return category;
    }

    public void setCategory(List<ProductCategoryList> category) {
        this.category = category;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(category);
    }

    public int describeContents() {
        return 0;
    }

}