package com.speedoring.modal.city;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("banner")
    @Expose
    private List<CityList> banner = new ArrayList<CityList>();
    public final static Parcelable.Creator<CityMainModal> CREATOR = new Creator<CityMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CityMainModal createFromParcel(Parcel in) {
            return new CityMainModal(in);
        }

        public CityMainModal[] newArray(int size) {
            return (new CityMainModal[size]);
        }

    };

    protected CityMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.banner, (CityList.class.getClassLoader()));
    }

    public CityMainModal() {
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

    public List<CityList> getBanner() {
        return banner;
    }

    public void setBanner(List<CityList> banner) {
        this.banner = banner;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(banner);
    }

    public int describeContents() {
        return 0;
    }

}
