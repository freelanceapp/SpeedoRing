package com.speedoring.modal.product_sub_category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductSubCategory implements Parcelable {

    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;
    @SerializedName("sub_cat_name")
    @Expose
    private String subCatName;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    public final static Parcelable.Creator<ProductSubCategory> CREATOR = new Creator<ProductSubCategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductSubCategory createFromParcel(Parcel in) {
            return new ProductSubCategory(in);
        }

        public ProductSubCategory[] newArray(int size) {
            return (new ProductSubCategory[size]);
        }

    };

    protected ProductSubCategory(Parcel in) {
        this.subCategoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.subCatName = ((String) in.readValue((String.class.getClassLoader())));
        this.images = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.catName = ((String) in.readValue((String.class.getClassLoader())));
        this.catImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductSubCategory() {
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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
        dest.writeValue(subCategoryId);
        dest.writeValue(subCatName);
        dest.writeValue(images);
        dest.writeValue(categoryId);
        dest.writeValue(catName);
        dest.writeValue(catImage);
    }

    public int describeContents() {
        return 0;
    }

}
