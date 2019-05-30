package com.speedoring.modal.vendor.vendor_product_list;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorProductListing implements Parcelable
{

    @SerializedName("listing_id")
    @Expose
    private String listingId;
    @SerializedName("listing_name")
    @Expose
    private String listingName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;
    @SerializedName("sub_cat_name")
    @Expose
    private String subCatName;
    @SerializedName("sub_category_images")
    @Expose
    private String subCategoryImages;
    @SerializedName("product_images")
    @Expose
    private List<VendorProductImage> productImages = new ArrayList<VendorProductImage>();
    public final static Parcelable.Creator<VendorProductListing> CREATOR = new Creator<VendorProductListing>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorProductListing createFromParcel(Parcel in) {
            return new VendorProductListing(in);
        }

        public VendorProductListing[] newArray(int size) {
            return (new VendorProductListing[size]);
        }

    }
            ;

    protected VendorProductListing(Parcel in) {
        this.listingId = ((String) in.readValue((String.class.getClassLoader())));
        this.listingName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.catName = ((String) in.readValue((String.class.getClassLoader())));
        this.catImage = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.subCatName = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryImages = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productImages, (VendorProductImage.class.getClassLoader()));
    }

    public VendorProductListing() {
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getListingName() {
        return listingName;
    }

    public void setListingName(String listingName) {
        this.listingName = listingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getSubCategoryImages() {
        return subCategoryImages;
    }

    public void setSubCategoryImages(String subCategoryImages) {
        this.subCategoryImages = subCategoryImages;
    }

    public List<VendorProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<VendorProductImage> productImages) {
        this.productImages = productImages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(listingId);
        dest.writeValue(listingName);
        dest.writeValue(description);
        dest.writeValue(categoryId);
        dest.writeValue(catName);
        dest.writeValue(catImage);
        dest.writeValue(subCategoryId);
        dest.writeValue(subCatName);
        dest.writeValue(subCategoryImages);
        dest.writeList(productImages);
    }

    public int describeContents() {
        return 0;
    }

}