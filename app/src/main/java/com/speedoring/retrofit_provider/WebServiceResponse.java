package com.speedoring.retrofit_provider;

import com.speedoring.modal.insta_mojo.GetOrderIDResponse;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Response;

public class WebServiceResponse {

    public static void handleResponse(Response<?> response, WebResponse webResponse) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                webResponse.onResponseSuccess(response);
            } else {
                webResponse.onResponseFailed(response.message());
            }
        } else {
            try {
                if (response.errorBody() != null) {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    webResponse.onResponseFailed(jObjError.getString("error"));
                } else {
                    webResponse.onResponseFailed(response.message());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void handleResponse(Response<GetOrderIDResponse> response, WebResponseInstaMojo webResponse) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                webResponse.onResponseSuccess(response);
            } else {
                webResponse.onResponseFailed(response.message());
            }
        } else {
            try {
                if (response.errorBody() != null) {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    webResponse.onResponseFailed(jObjError.getString("error"));
                } else {
                    webResponse.onResponseFailed(response.message());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}