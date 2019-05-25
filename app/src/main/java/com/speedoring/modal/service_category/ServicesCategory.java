package com.speedoring.modal.service_category;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesCategory implements Parcelable {

    @SerializedName("service_cat_id")
    @Expose
    private String serviceCatId;
    @SerializedName("service_cate_name")
    @Expose
    private String serviceCateName;
    public final static Parcelable.Creator<ServicesCategory> CREATOR = new Creator<ServicesCategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServicesCategory createFromParcel(Parcel in) {
            return new ServicesCategory(in);
        }

        public ServicesCategory[] newArray(int size) {
            return (new ServicesCategory[size]);
        }

    };

    protected ServicesCategory(Parcel in) {
        this.serviceCatId = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceCateName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ServicesCategory() {
    }

    public String getServiceCatId() {
        return serviceCatId;
    }

    public void setServiceCatId(String serviceCatId) {
        this.serviceCatId = serviceCatId;
    }

    public String getServiceCateName() {
        return serviceCateName;
    }

    public void setServiceCateName(String serviceCateName) {
        this.serviceCateName = serviceCateName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(serviceCatId);
        dest.writeValue(serviceCateName);
    }

    public int describeContents() {
        return 0;
    }

}
