package com.example.app.digitalizebillscustomer.SignInScreen;


import com.example.app.digitalizebillscustomer.User;

import rx.Observable;
import java.util.Map;

/**
 * Created by vikkycorner on 09/04/16.
 */
public interface SignInScreenView {
    Observable<Map<String, String>> signIn();

    void userAuthenticated(User signInResponse);
}
