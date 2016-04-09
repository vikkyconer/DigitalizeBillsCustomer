package com.example.app.digitalizebillscustomer.SignInScreen;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.app.digitalizebillscustomer.AppService;
import com.example.app.digitalizebillscustomer.Constants;
import com.example.app.digitalizebillscustomer.R;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by vikkycorner on 09/04/16.
 */
public class SignInActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().hide();

        new SignInScreenPresenter(loginScreenView(), loginScreenModel());
    }

    private SignInScreenView loginScreenView() {
        return (SignInScreenFragment) getSupportFragmentManager().findFragmentById(R.id.login_fragment);
    }


    private SignInScreenModel loginScreenModel() {
        return  new SignInScreenModelImpl(service());
    }

    private AppService service() {
        AppService service = restAdapterBuilder().setEndpoint(Constants.BASE_URL).setLogLevel(RestAdapter.LogLevel.FULL).build().create(
                AppService.class);
        return service;
    }

    private RestAdapter.Builder restAdapterBuilder() {
        return new RestAdapter.Builder().setConverter(new GsonConverter(new GsonBuilder().create()));
    }

}

