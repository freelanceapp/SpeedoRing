package com.speedoring.ui.vendor.fragment.profile;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.speedoring.R;
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

public class VendorPersonalInfoFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private UpdateInterface updateInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.vendor_fragment_personal_info, container, false);
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

    private void setData() {
        ((EditText) rootView.findViewById(R.id.edtFirstName)).setText(User.getUser().getFirstName());
        ((EditText) rootView.findViewById(R.id.edtLastName)).setText(User.getUser().getLastName());
        ((EditText) rootView.findViewById(R.id.edtAddress)).setText(User.getUser().getAddress());
        ((EditText) rootView.findViewById(R.id.edtCity)).setText(User.getUser().getCity());
        ((EditText) rootView.findViewById(R.id.edtPincode)).setText(User.getUser().getPincode());
        ((EditText) rootView.findViewById(R.id.edtState)).setText(User.getUser().getState());
        ((EditText) rootView.findViewById(R.id.edtCountry)).setText(User.getUser().getCountry());
    }

    public void setUpdateInterface(UpdateInterface updateInterface) {
        this.updateInterface = updateInterface;
    }

    @Override
    public void onClick(View v) {
        businessUpdateApi();
    }

    private void businessUpdateApi() {
        String vendorId = User.getUser().getVendorId();
        String strFirstName = ((EditText) rootView.findViewById(R.id.edtFirstName)).getText().toString();
        String strLastName = ((EditText) rootView.findViewById(R.id.edtLastName)).getText().toString();
        String strAddress = ((EditText) rootView.findViewById(R.id.edtAddress)).getText().toString();
        String strCity = ((EditText) rootView.findViewById(R.id.edtCity)).getText().toString();
        String strPincode = ((EditText) rootView.findViewById(R.id.edtPincode)).getText().toString();
        String strState = ((EditText) rootView.findViewById(R.id.edtState)).getText().toString();
        String strCountry = ((EditText) rootView.findViewById(R.id.edtCountry)).getText().toString();

        if (strFirstName.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter first name...!!!");
        } else if (strLastName.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter last name...!!!");
        } else if (strAddress.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter address...!!!");
        } else if (strCity.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter city...!!!");
        } else if (strPincode.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter pin code...!!!");
        } else if (strState.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter state...!!!");
        } else if (strCountry.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter country...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.updatePersonalInfo(vendorId,
                        strFirstName, strLastName, strCountry, strAddress, strCity,
                        strState, strPincode), new WebResponse() {
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