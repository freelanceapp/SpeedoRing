package com.speedoring.ui;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.speedoring.R;
import com.speedoring.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;

public class InstaMojoActivity extends BaseActivity implements View.OnClickListener {

    private InstapayListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.pay).setOnClickListener(this);
    }

    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG).show();
            }
        };
    }

    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = this;
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    @Override
    public void onClick(View v) {
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String amount = ((EditText) findViewById(R.id.amount)).getText().toString();
        String purpose = ((EditText) findViewById(R.id.description)).getText().toString();
        String buyername = ((EditText) findViewById(R.id.name)).getText().toString();

        callInstamojoPay(email, phone, amount, purpose, buyername);
    }
}
