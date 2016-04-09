package com.example.app.digitalizebillscustomer.SignUpScreen;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.app.digitalizebillscustomer.AppService;
import com.example.app.digitalizebillscustomer.Constants;
import com.example.app.digitalizebillscustomer.R;
import com.example.app.digitalizebillscustomer.SignInScreen.SignInScreenFragment;
import com.example.app.digitalizebillscustomer.SignInScreen.SignInScreenModel;
import com.example.app.digitalizebillscustomer.SignInScreen.SignInScreenModelImpl;
import com.example.app.digitalizebillscustomer.SignInScreen.SignInScreenPresenter;
import com.example.app.digitalizebillscustomer.SignInScreen.SignInScreenView;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class SignUpActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        new SignUpPresenter(signUpScreenView(), signUpScreenModel());
    }

    private SignUpScreenView signUpScreenView() {
        return (SignUpScreenFragment) getSupportFragmentManager().findFragmentById(R.id.sign_up_fragment);
    }


    private SignUpScreenModel signUpScreenModel() {
        return new SignUpModelImpl(service());
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
