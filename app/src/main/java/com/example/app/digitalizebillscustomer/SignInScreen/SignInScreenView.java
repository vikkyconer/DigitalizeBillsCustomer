package com.example.app.digitalizebillscustomer.SignInScreen;


import com.example.app.digitalizebillscustomer.Models.User;
import com.example.app.digitalizebillscustomer.Models.Vendor;

import rx.Observable;
import java.util.Map;

/**
 * Created by vikkycorner on 09/04/16.
 */
public interface SignInScreenView {
    Observable<Map<String, String>> signIn();

    void userAuthenticated(User signInResponse);

    void vendorAuthenticated(Vendor vendorSignInResponse);
}
