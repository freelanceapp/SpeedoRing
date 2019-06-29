package com.speedoring.modal.service_category;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceCatMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("category")
    @Expose
    private List<ServiceCategory> category = new ArrayList<ServiceCategory>();
    public final static Parcelable.Creator<ServiceCatMainModal> CREATOR = new Creator<ServiceCatMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceCatMainModal createFromParcel(Parcel in) {
            return new ServiceCatMainModal(in);
        }

        public ServiceCatMainModal[] newArray(int size) {
            return (new ServiceCatMainModal[size]);
        }

    };

    protected ServiceCatMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.category, (ServiceCategory.class.getClassLoader()));
    }

    public ServiceCatMainModal() {
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

    public List<ServiceCategory> getCategory() {
        return category;
    }

    public void setCategory(List<ServiceCategory> category) {
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