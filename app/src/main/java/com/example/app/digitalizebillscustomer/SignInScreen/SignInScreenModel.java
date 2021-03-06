package com.example.app.digitalizebillscustomer.SignInScreen;

import com.example.app.digitalizebillscustomer.Models.User;
import com.example.app.digitalizebillscustomer.Models.Vendor;

import java.util.Map;

import rx.Observable;

/**
 * Created by vikkycorner on 09/04/16.
 */
public interface SignInScreenModel {
    Observable<User> signIn(Map<String, String> signInRequestMap);

    Observable<Vendor> vendorSignIn(Map<String, String> map);
}
