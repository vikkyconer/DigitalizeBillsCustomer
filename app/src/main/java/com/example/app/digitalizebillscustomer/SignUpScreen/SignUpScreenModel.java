package com.example.app.digitalizebillscustomer.SignUpScreen;

import com.example.app.digitalizebillscustomer.User;

import java.util.Map;

import rx.Observable;

/**
 * Created by vikkycorner on 09/04/16.
 */
public interface SignUpScreenModel {
    Observable<User> signUp(Map<String, String> signUpRequestMap);
}
