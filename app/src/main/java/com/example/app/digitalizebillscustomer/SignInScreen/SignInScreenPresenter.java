package com.example.app.digitalizebillscustomer.SignInScreen;

import com.example.app.digitalizebillscustomer.Constants;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vikkycorner on 09/04/16.
 */
public class SignInScreenPresenter {
    public SignInScreenPresenter(SignInScreenView view, SignInScreenModel model) {
        if (Constants.typeUser == "customer")
            view.signIn().subscribe(map -> model.signIn(map).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(signInResponse -> view.userAuthenticated(signInResponse)));
        else if(Constants.typeUser == "vendor")
            view.signIn().subscribe(map -> model.vendorSignIn(map).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(vendorSignInResponse -> view.vendorAuthenticated(vendorSignInResponse)));
    }
}
