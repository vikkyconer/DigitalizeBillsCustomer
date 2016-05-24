package com.example.app.digitalizebillscustomer.SignUpScreen;

import com.example.app.digitalizebillscustomer.Models.User;

import java.util.Map;

import rx.Observable;

/**
 * Created by vikkycorner on 09/04/16.
 */
public interface SignUpScreenView {
    Observable<Map<String, String>> signUp();

    void userCreated(User signUpResponse);
}
