package com.example.app.digitalizebillscustomer.SignUpScreen;

import android.widget.Toast;

import com.example.app.digitalizebillscustomer.SignInScreen.SignInScreenModel;
import com.example.app.digitalizebillscustomer.SignInScreen.SignInScreenView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vikkycorner on 09/04/16.
 */
public class SignUpPresenter {
    public SignUpPresenter(SignUpScreenView view, SignUpScreenModel model) {
        view.signUp().subscribe(map -> model.signUp(map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(signUpResponse -> view.userCreated(signUpResponse)));

    }
}
