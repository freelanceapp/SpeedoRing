package com.speedoring.modal.vendor.login_data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorLoginMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vendorinfo")
    @Expose
    private Vendorinfo vendorinfo;
    public final static Parcelable.Creator<VendorLoginMainModal> CREATOR = new Creator<VendorLoginMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorLoginMainModal createFromParcel(Parcel in) {
            return new VendorLoginMainModal(in);
        }

        public VendorLoginMainModal[] newArray(int size) {
            return (new VendorLoginMainModal[size]);
        }

    };

    protected VendorLoginMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorinfo = ((Vendorinfo) in.readValue((Vendorinfo.class.getClassLoader())));
    }

    public VendorLoginMainModal() {
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

    public Vendorinfo getVendorinfo() {
        return vendorinfo;
    }

    public void setVendorinfo(Vendorinfo vendorinfo) {
        this.vendorinfo = vendorinfo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(vendorinfo);
    }

    public int describeContents() {
        return 0;
    }

}