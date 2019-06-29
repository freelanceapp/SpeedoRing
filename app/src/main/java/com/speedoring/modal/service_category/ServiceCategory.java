package com.speedoring.modal.service_category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceCategory implements Parcelable {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    public final static Parcelable.Creator<ServiceCategory> CREATOR = new Creator<ServiceCategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceCategory createFromParcel(Parcel in) {
            return new ServiceCategory(in);
        }

        public ServiceCategory[] newArray(int size) {
            return (new ServiceCategory[size]);
        }

    };

    protected ServiceCategory(Parcel in) {
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.catName = ((String) in.readValue((String.class.getClassLoader())));
        this.catImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ServiceCategory() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(categoryId);
        dest.writeValue(catName);
        dest.writeValue(catImage);
    }

    public int describeContents() {
        return 0;
    }

}