package com.example.app.digitalizebillscustomer.MainScreen;

import com.example.app.digitalizebillscustomer.Models.Bill;

import java.util.LinkedList;

import rx.Observable;

/**
 * Created by vikkycorner on 27/05/16.
 */
public interface MainScreenView {
    Observable<Boolean> Initialized();

    void showBills(LinkedList<Bill> bills);
}
