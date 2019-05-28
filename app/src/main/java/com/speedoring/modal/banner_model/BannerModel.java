
package com.speedoring.modal.banner_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BannerModel implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("banner")
    @Expose
    private List<BannerDatum> data = new ArrayList<BannerDatum>();
    public final static Creator<BannerModel> CREATOR = new Creator<BannerModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BannerModel createFromParcel(Parcel in) {
            return new BannerModel(in);
        }

        public BannerModel[] newArray(int size) {
            return (new BannerModel[size]);
        }

    };

    protected BannerModel(Parcel in) {
        this.error = ((Boolean) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (BannerDatum.class.getClassLoader()));
    }

    public BannerModel() {
    }

    public List<BannerDatum> getData() {
        return data;
    }

    public void setData(List<BannerDatum> data) {
        this.data = data;
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

    public BannerModel withData(List<BannerDatum> data) {
        this.data = data;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
