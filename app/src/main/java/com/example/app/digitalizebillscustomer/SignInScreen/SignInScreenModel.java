package com.example.app.digitalizebillscustomer.SignInScreen;

import com.example.app.digitalizebillscustomer.Models.User;

import java.util.Map;

import rx.Observable;

/**
 * Created by vikkycorner on 09/04/16.
 */
public interface SignInScreenModel {
    Observable<User> signIn(Map<String, String> signInRequestMap);
}
