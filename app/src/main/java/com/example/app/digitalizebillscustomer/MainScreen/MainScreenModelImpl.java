package com.example.app.digitalizebillscustomer.MainScreen;

import com.example.app.digitalizebillscustomer.AppService;

/**
 * Created by vikkycorner on 27/05/16.
 */
public class MainScreenModelImpl implements MainScreenModel {
    AppService service;

    public MainScreenModelImpl(AppService service) {
        this.service = service;
    }
}
