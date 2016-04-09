package com.example.app.digitalizebillscustomer.SignInScreen;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vikkycorner on 09/04/16.
 */
public class SignInScreenPresenter {
    public SignInScreenPresenter(SignInScreenView view, SignInScreenModel model) {
        view.signIn().subscribe(map -> model.signIn(map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(signInResponse -> view.userAuthenticated(signInResponse)));
    }
}
