package com.example.app.digitalizebillscustomer.MainScreen;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vikkycorner on 27/05/16.
 */
public class MainScreenPresenter {
    public MainScreenPresenter(MainScreenModel model, MainScreenView view) {
//        view.Initialized().subscribe(bool -> model.bills().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(bills -> view.showBills(bills)));
    }
}
