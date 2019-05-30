package com.speedoring.modal.vendor.enquiry_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VendorEnquiryMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("enquiry")
    @Expose
    private List<VendorEnquiryList> enquiry = new ArrayList<VendorEnquiryList>();
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;
    @SerializedName("current_page")
    @Expose
    private String currentPage;
    public final static Parcelable.Creator<VendorEnquiryMainModal> CREATOR = new Creator<VendorEnquiryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorEnquiryMainModal createFromParcel(Parcel in) {
            return new VendorEnquiryMainModal(in);
        }

        public VendorEnquiryMainModal[] newArray(int size) {
            return (new VendorEnquiryMainModal[size]);
        }

    };

    protected VendorEnquiryMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.enquiry, (VendorEnquiryList.class.getClassLoader()));
        this.pageCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currentPage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorEnquiryMainModal() {
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

    public List<VendorEnquiryList> getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(List<VendorEnquiryList> enquiry) {
        this.enquiry = enquiry;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(enquiry);
        dest.writeValue(pageCount);
        dest.writeValue(currentPage);
    }

    public int describeContents() {
        return 0;
    }

}