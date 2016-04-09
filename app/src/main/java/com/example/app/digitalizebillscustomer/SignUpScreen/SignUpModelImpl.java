package com.example.app.digitalizebillscustomer.SignUpScreen;

import com.example.app.digitalizebillscustomer.AppService;
import com.example.app.digitalizebillscustomer.User;

import java.util.Map;

import rx.Observable;

/**
 * Created by vikkycorner on 09/04/16.
 */
public class SignUpModelImpl implements SignUpScreenModel {
    AppService service;

    public SignUpModelImpl(AppService service) {
        this.service = service;
    }

    @Override
    public Observable<User> signUp(Map<String, String> signUpRequestMap) {
        return service.signUp(signUpRequestMap);
    }
}
