package com.speedoring.modal;

import com.speedoring.modal.vendor.login_data.Vendorinfo;

public class User {

    public static Vendorinfo user;

    public static Vendorinfo getUser() {
        return user;
    }

    public static void setUser(Vendorinfo user) {
        User.user = user;
    }
}
