package com.example.app.digitalizebillscustomer.SignInScreen;

import com.example.app.digitalizebillscustomer.AppService;
import com.example.app.digitalizebillscustomer.Constants;
import com.example.app.digitalizebillscustomer.Models.User;
import com.example.app.digitalizebillscustomer.Models.Vendor;

import java.util.Map;

import rx.Observable;

/**
 * Created by vikkycorner on 09/04/16.
 */
public class SignInScreenModelImpl implements SignInScreenModel {

    AppService service;

    public SignInScreenModelImpl(AppService service) {
        this.service = service;
    }

    @Override
    public Observable<User> signIn(Map<String, String> signInRequestMap) {
        return service.customerSignIn(signInRequestMap);
    }

    @Override
    public Observable<Vendor> vendorSignIn(Map<String, String> signInRequestMap) {
        return service.vendorSignIn(signInRequestMap);
    }
}
