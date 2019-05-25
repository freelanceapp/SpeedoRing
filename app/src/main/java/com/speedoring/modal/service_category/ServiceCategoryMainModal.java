package com.speedoring.modal.service_category;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceCategoryMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("services_category")
    @Expose
    private List<ServicesCategory> servicesCategory = new ArrayList<ServicesCategory>();
    public final static Parcelable.Creator<ServiceCategoryMainModal> CREATOR = new Creator<ServiceCategoryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceCategoryMainModal createFromParcel(Parcel in) {
            return new ServiceCategoryMainModal(in);
        }

        public ServiceCategoryMainModal[] newArray(int size) {
            return (new ServiceCategoryMainModal[size]);
        }

    };

    protected ServiceCategoryMainModal(Parcel in) {
        this.result = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.servicesCategory, (com.speedoring.modal.service_category.ServicesCategory.class.getClassLoader()));
    }

    public ServiceCategoryMainModal() {
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ServicesCategory> getServicesCategory() {
        return servicesCategory;
    }

    public void setServicesCategory(List<ServicesCategory> servicesCategory) {
        this.servicesCategory = servicesCategory;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(result);
        dest.writeValue(message);
        dest.writeList(servicesCategory);
    }

    public int describeContents() {
        return 0;
    }

}