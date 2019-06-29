package com.speedoring.modal.city;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityList implements Parcelable {

    @SerializedName("city")
    @Expose
    private String city;
    public final static Parcelable.Creator<CityList> CREATOR = new Creator<CityList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CityList createFromParcel(Parcel in) {
            return new CityList(in);
        }

        public CityList[] newArray(int size) {
            return (new CityList[size]);
        }

    };

    protected CityList(Parcel in) {
        this.city = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CityList() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(city);
    }

    public int describeContents() {
        return 0;
    }

}