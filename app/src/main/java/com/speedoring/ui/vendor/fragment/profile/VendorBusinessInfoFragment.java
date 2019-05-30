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

public class VendorBusinessInfoFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private UpdateInterface updateInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.vendor_fragment_business_info, container, false);
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
        ((EditText) rootView.findViewById(R.id.edtBusinessName)).setText(User.getUser().getBusinessName());
        ((EditText) rootView.findViewById(R.id.edtBusinessCategory)).setText(User.getUser().getCategory());
        ((EditText) rootView.findViewById(R.id.edtBusinessKeywords)).setText(User.getUser().getBusinessKeyword());
        ((EditText) rootView.findViewById(R.id.edtBusinessNature)).setText(User.getUser().getBusinessNature());
        ((EditText) rootView.findViewById(R.id.edtBusinessTin)).setText(User.getUser().getBusinessTin());
        ((EditText) rootView.findViewById(R.id.edtBusinessAddress)).setText(User.getUser().getBusinessAddress());
        ((EditText) rootView.findViewById(R.id.edtBusinessCity)).setText(User.getUser().getBusinessCity());
        ((EditText) rootView.findViewById(R.id.edtBusinessPinCode)).setText(User.getUser().getPincode());
        ((EditText) rootView.findViewById(R.id.edtBusinessArea)).setText(User.getUser().getBusinessArea());
        ((EditText) rootView.findViewById(R.id.edtBusinessCountry)).setText(User.getUser().getBusinessCountry());
    }

    @Override
    public void onClick(View v) {
        businessUpdateApi();
    }

    private void businessUpdateApi() {
        String vendorId = User.getUser().getVendorId();
        String strBusinessName = ((EditText) rootView.findViewById(R.id.edtBusinessName)).getText().toString();
        String strBusinessCategory = ((EditText) rootView.findViewById(R.id.edtBusinessCategory)).getText().toString();
        String strBusinessKeywords = ((EditText) rootView.findViewById(R.id.edtBusinessKeywords)).getText().toString();
        String strBusinessNature = ((EditText) rootView.findViewById(R.id.edtBusinessNature)).getText().toString();
        String strBusinessTin = ((EditText) rootView.findViewById(R.id.edtBusinessTin)).getText().toString();
        String strBusinessAddress = ((EditText) rootView.findViewById(R.id.edtBusinessAddress)).getText().toString();
        String strBusinessCity = ((EditText) rootView.findViewById(R.id.edtBusinessCity)).getText().toString();
        String strBusinessPinCode = ((EditText) rootView.findViewById(R.id.edtBusinessPinCode)).getText().toString();
        String strBusinessArea = ((EditText) rootView.findViewById(R.id.edtBusinessArea)).getText().toString();
        String strBusinessCountry = ((EditText) rootView.findViewById(R.id.edtBusinessCountry)).getText().toString();

        if (strBusinessName.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business name...!!!");
        } else if (strBusinessCategory.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business category...!!!");
        } else if (strBusinessKeywords.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business keywords...!!!");
        } else if (strBusinessNature.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business nature...!!!");
        } else if (strBusinessTin.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business tin...!!!");
        } else if (strBusinessAddress.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business address...!!!");
        } else if (strBusinessCity.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business city...!!!");
        } else if (strBusinessPinCode.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business pin code...!!!");
        } else if (strBusinessArea.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business area...!!!");
        } else if (strBusinessCountry.isEmpty()) {
            Alerts.show(rootView.findViewById(R.id.llContainer), "Enter business country...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getContentData(new Dialog(mContext), retrofitApiClient.updateBusinessInfo(vendorId,
                        strBusinessName, strBusinessNature, strBusinessCategory, strBusinessTin, strBusinessAddress, strBusinessCity,
                        strBusinessPinCode, strBusinessArea, strBusinessCountry, strBusinessKeywords), new WebResponse() {
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