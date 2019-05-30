package com.speedoring.ui.vendor.fragment.profile;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.speedoring.R;
import com.speedoring.constant.Constant;
import com.speedoring.interface_update_data.UpdateInterface;
import com.speedoring.modal.User;
import com.speedoring.retrofit_provider.RetrofitService;
import com.speedoring.retrofit_provider.WebResponse;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseFragment;
import com.speedoring.utils.ConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class VendorContactInfoFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private UpdateInterface updateInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.vendor_fragment_contact_info, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init();
        return rootView;
    }

    private void init() {
        rootView.findViewById(R.id.btnUpdate).setOnClickListener(this);
        setData();
    }

    public void setUpdateInterface(UpdateInterface updateInterface) {
        this.updateInterface = updateInterface;
    }

    private void setData() {
        ((EditText) rootView.findViewById(R.id.edtLLA)).setText(User.getUser().getLandlineOne());
        ((EditText) rootView.findViewById(R.id.edtLLB)).setText(User.getUser().getLandlineTwo());
        ((EditText) rootView.findViewById(R.id.edtMobileA)).setText(User.getUser().getMobileOne());
        ((EditText) rootView.findViewById(R.id.edtMobileB)).setText(User.getUser().getMobileTwo());
        ((EditText) rootView.findViewById(R.id.edtFaxA)).setText(User.getUser().getFaxOne());
        ((EditText) rootView.findViewById(R.id.edtFaxB)).setText(User.getUser().getFaxTwo());
        ((EditText) rootView.findViewById(R.id.edtTollFreeA)).setText(User.getUser().getTollfreeOne());
        ((EditText) rootView.findViewById(R.id.edtTollFreeB)).setText(User.getUser().getTollfreeTwo());
        ((EditText) rootView.findViewById(R.id.edtEmail)).setText(User.getUser().getEmail());
        ((EditText) rootView.findViewById(R.id.edtWebsite)).setText(User.getUser().getWebsite());
    }

    @Override
    public void onClick(View v) {
        businessUpdateApi();
    }

    private void businessUpdateApi() {
        String vendorId = User.getUser().getVendorId();
        String strLLA = ((EditText) rootView.findViewById(R.id.edtLLA)).getText().toString();
        String strLLB = ((EditText) rootView.findViewById(R.id.edtLLB)).getText().toString();
        String strMobileA = ((EditText) rootView.findViewById(R.id.edtMobileA)).getText().toString();
        String strMobileB = ((EditText) rootView.findViewById(R.id.edtMobileB)).getText().toString();
        String strFaxA = ((EditText) rootView.findViewById(R.id.edtFaxA)).getText().toString();
        String strFaxB = ((EditText) rootView.findViewById(R.id.edtFaxB)).getText().toString();
        String strTollFreeA = ((EditText) rootView.findViewById(R.id.edtTollFreeA)).getText().toString();
        String strTollFreeB = ((EditText) rootView.findViewById(R.id.edtTollFreeB)).getText().toString();
        String strEmail = ((EditText) rootView.findViewById(R.id.edtEmail)).getText().toString();
        String strWebsite = ((EditText) rootView.findViewById(R.id.edtWebsite)).getText().toString();

        if (strLLA.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter land line 1...!!!");
        } else if (strLLB.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter land line 2...!!!");
        } else if (strMobileA.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter mobile 1...!!!");
        } else if (strMobileB.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter mobile 2...!!!");
        } else if (strFaxA.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter fax 1...!!!");
        } else if (strFaxB.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter fax 2...!!!");
        } else if (strTollFreeA.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter toll free 1...!!!");
        } else if (strTollFreeB.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter toll free 2...!!!");
        } else if (strEmail.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter email...!!!");
        } else if (Constant.isValidEmailId(strEmail)) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter valid email...!!!");
        } else if (strWebsite.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter website...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.updateContactInfo(vendorId,
                        strLLA, strLLB, strMobileA, strMobileB, strFaxA, strFaxB, strTollFreeA, strTollFreeB,
                        strWebsite, strEmail), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                updateInterface.isUpdateData(true);
                                Alerts.show(rootView.findViewById(R.id.llContainer), jsonObject.getString("message"));
                            } else {
                                Alerts.show(rootView.findViewById(R.id.llContainer), jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(rootView.findViewById(R.id.llContainer), error);
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }

}