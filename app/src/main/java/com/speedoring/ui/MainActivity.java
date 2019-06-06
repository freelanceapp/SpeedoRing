package com.speedoring.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.instamojo.android.Instamojo;
import com.instamojo.android.activities.PaymentDetailsActivity;
import com.instamojo.android.callbacks.OrderRequestCallback;
import com.instamojo.android.helpers.Constants;
import com.instamojo.android.models.Errors;
import com.instamojo.android.models.Order;
import com.instamojo.android.network.Request;
import com.speedoring.R;
import com.speedoring.modal.insta_mojo.GatewayOrderStatus;
import com.speedoring.modal.insta_mojo.GetOrderIDRequest;
import com.speedoring.modal.insta_mojo.GetOrderIDResponse;
import com.speedoring.retrofit_provider.MyBackendService;
import com.speedoring.retrofit_provider.RetrofitServiceInstaMojo;
import com.speedoring.retrofit_provider.WebResponseInstaMojo;
import com.speedoring.utils.Alerts;
import com.speedoring.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final HashMap<String, String> env_options = new HashMap<>();

    static {
        //env_options.put("Test", "https://test.instamojo.com/");
        env_options.put("Production", "https://api.instamojo.com/");
    }

    private AlertDialog dialog;
    private AppCompatEditText nameBox, emailBox, phoneBox, amountBox, descriptionBox;
    private String currentEnv = null;
    private MyBackendService myBackendService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new RetrofitServiceInstaMojo.DefaultHeadersInterceptor())
            .build();

    private void init() {
        Button button = findViewById(R.id.pay);
        nameBox = findViewById(R.id.name);
        nameBox.setSelection(nameBox.getText().toString().trim().length());
        emailBox = findViewById(R.id.email);
        emailBox.setSelection(emailBox.getText().toString().trim().length());
        phoneBox = findViewById(R.id.phone);
        phoneBox.setSelection(phoneBox.getText().toString().trim().length());
        amountBox = findViewById(R.id.amount);
        amountBox.setSelection(amountBox.getText().toString().trim().length());
        descriptionBox = findViewById(R.id.description);
        descriptionBox.setSelection(descriptionBox.getText().toString().trim().length());
        AppCompatSpinner envSpinner = findViewById(R.id.env_spinner);
        final ArrayList<String> envs = new ArrayList<>(env_options.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, envs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        envSpinner.setAdapter(adapter);
        envSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentEnv = envs.get(position);
                Instamojo.setBaseUrl(env_options.get(currentEnv));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_progress_bar);
        dialog = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://sample-sdk-server.instamojo.com/speedoring/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myBackendService = retrofit.create(MyBackendService.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrderOnServer();
            }
        });
        Instamojo.setLogLevel(Log.DEBUG);
    }

    private void createOrderOnServer() {
        GetOrderIDRequest request = new GetOrderIDRequest();
        request.setEnv(currentEnv);
        request.setBuyerName(nameBox.getText().toString());
        request.setBuyerEmail(emailBox.getText().toString());
        request.setBuyerPhone(phoneBox.getText().toString());
        request.setDescription(descriptionBox.getText().toString());
        request.setAmount(amountBox.getText().toString());

        if (cd.isNetworkAvailable()) {
            RetrofitServiceInstaMojo.getContentData(new Dialog(mContext), myBackendService.createOrder(request), new WebResponseInstaMojo() {
                @Override
                public void onResponseSuccess(Response<GetOrderIDResponse> response) {
                    if (response.isSuccessful()) {
                        String orderId = response.body().getOrderID();
                        fetchOrder(orderId);
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Log.d(TAG, "Error in response" + jObjError.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    private void fetchOrder(String orderID) {
        // Good time to show dialog
        Request request = new Request(orderID, new OrderRequestCallback() {
            @Override
            public void onFinish(final Order order, final Exception error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (error != null) {
                            if (error instanceof Errors.ConnectionError) {
                                Alerts.show(mContext, "No internet connection");
                            } else if (error instanceof Errors.ServerError) {
                                Alerts.show(mContext, "Server error. Try again");
                            } else if (error instanceof Errors.AuthenticationError) {
                                Alerts.show(mContext, "Access token is invalid or expired. Please update the token");
                            } else {
                                Alerts.show(mContext, error.toString());
                            }
                            return;
                        }
                        startPreCreatedUI(order);
                    }
                });
            }
        });

        request.execute();
    }

    private void startPreCreatedUI(Order order) {
        //Using Pre created UI
        Intent intent = new Intent(getBaseContext(), PaymentDetailsActivity.class);
        intent.putExtra(Constants.ORDER, order);
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE && data != null) {
            String orderID = data.getStringExtra(Constants.ORDER_ID);
            String transactionID = data.getStringExtra(Constants.TRANSACTION_ID);
            String paymentID = data.getStringExtra(Constants.PAYMENT_ID);

            // Check transactionID, orderID, and orderID for null before using them to check the Payment status.
            if (transactionID != null || paymentID != null) {
                checkPaymentStatus(transactionID, orderID);
            } else {
                Alerts.show(mContext, "Oops!! Payment was cancelled");
            }
        }
    }

    private void checkPaymentStatus(final String transactionID, final String orderID) {
        if (transactionID == null && orderID == null) {
            return;
        }

        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }

        Alerts.show(mContext, "Checking transaction status");
        Call<GatewayOrderStatus> getOrderStatusCall = myBackendService.orderStatus(currentEnv.toLowerCase(Locale.US),
                orderID, transactionID);
        getOrderStatusCall.enqueue(new retrofit2.Callback<GatewayOrderStatus>() {
            @Override
            public void onResponse(Call<GatewayOrderStatus> call, final Response<GatewayOrderStatus> response) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (response.isSuccessful()) {
                    GatewayOrderStatus orderStatus = response.body();
                    if (orderStatus.getStatus().equalsIgnoreCase("successful")) {
                        Alerts.show(mContext, "Transaction still pending");
                        return;
                    }

                    Alerts.show(mContext, "Transaction successful for id - " + orderStatus.getPaymentID());
                    refundTheAmount(transactionID, orderStatus.getAmount());

                } else {
                    Alerts.show(mContext, "Error occurred while fetching transaction status");
                }
            }

            @Override
            public void onFailure(Call<GatewayOrderStatus> call, Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Alerts.show(mContext, "Failed to fetch the transaction status");
                    }
                });
            }
        });
    }

    private void refundTheAmount(String transactionID, String amount) {
        if (transactionID == null || amount == null) {
            return;
        }

        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }

        Alerts.show(mContext, "Initiating a refund for - " + amount);
        Call<ResponseBody> refundCall = myBackendService.refundAmount(
                currentEnv.toLowerCase(Locale.US),
                transactionID, amount);
        refundCall.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                if (response.isSuccessful()) {
                    Alerts.show(mContext, "Refund initiated successfully");

                } else {
                    Alerts.show(mContext, "Failed to initiate a refund");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                Alerts.show(mContext, "Failed to Initiate a refund");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
