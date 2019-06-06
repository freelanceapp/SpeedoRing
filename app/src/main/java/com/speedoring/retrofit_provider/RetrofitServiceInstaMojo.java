package com.speedoring.retrofit_provider;

import android.app.Dialog;
import android.os.Build;

import com.speedoring.BuildConfig;
import com.speedoring.modal.insta_mojo.GatewayOrderStatus;
import com.speedoring.modal.insta_mojo.GetOrderIDResponse;
import com.speedoring.utils.AppProgressDialog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import technology.sportsforsports.modal.post_feed.PostFeedMainModal;

public class RetrofitServiceInstaMojo {

    public static RetrofitApiClient client;

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.MINUTES)
            .addInterceptor(new DefaultHeadersInterceptor())
            .build();

    public RetrofitServiceInstaMojo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sample-sdk-server.instamojo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        client = retrofit.create(RetrofitApiClient.class);
    }

    public static RetrofitApiClient getRetrofit() {

        if (client == null)
            new RetrofitServiceInstaMojo();
        return client;
    }

    public static class DefaultHeadersInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request()
                    .newBuilder()
                    .header("User-Agent", getUserAgent())
                    .header("Referer", getReferer())
                    .build());
        }

        private static String getUserAgent() {
            return "instamojo-android-sdk-sample/" + BuildConfig.VERSION_NAME
                    + " android/" + Build.VERSION.RELEASE
                    + " " + Build.BRAND + "/" + Build.MODEL;
        }

        private static String getReferer() {
            return "android-app://" + BuildConfig.APPLICATION_ID;
        }
    }


    public static void getContentData(final Dialog dialog, final Call<GetOrderIDResponse> method, final WebResponseInstaMojo webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<GetOrderIDResponse>() {
            @Override
            public void onResponse(Call<GetOrderIDResponse> call, Response<GetOrderIDResponse> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<GetOrderIDResponse> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }
}