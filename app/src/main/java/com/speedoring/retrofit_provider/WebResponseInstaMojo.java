package com.speedoring.retrofit_provider;

import com.speedoring.modal.insta_mojo.GetOrderIDResponse;

import retrofit2.Response;

/**
 * Created by pc6 on 3/20/2017.
 */

public interface WebResponseInstaMojo {

    void onResponseSuccess(Response<GetOrderIDResponse> result);

    void onResponseFailed(String error);
}