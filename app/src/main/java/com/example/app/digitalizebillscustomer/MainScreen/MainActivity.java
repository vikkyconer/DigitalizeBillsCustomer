package com.example.app.digitalizebillscustomer.MainScreen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.app.digitalizebillscustomer.AppService;
import com.example.app.digitalizebillscustomer.Constants;
import com.example.app.digitalizebillscustomer.DatabaseHelper;
import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;
import com.example.app.digitalizebillscustomer.R;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerFragment;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerModel;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerModelImpl;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerPresenter;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerView;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainScreenPresenter(mainScreenModel(), mainScreenView());
        new SlidingDrawerPresenter(slidingDrawerModel(), slidingDrawerView());
    }

    private MainScreenView mainScreenView() {
        return (MainScreenFragment) getSupportFragmentManager().findFragmentById(R.id.main_screen_fragment);
    }

    private MainScreenModel mainScreenModel() {
        return new MainScreenModelImpl(service());

    }

    private SlidingDrawerView slidingDrawerView() {
        return (SlidingDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.left_drawer);
    }

    private SlidingDrawerModel slidingDrawerModel() {
        return new SlidingDrawerModelImpl(service());
    }

    private AppService service() {
        AppService appService = restAdapterBuilder().setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL).build().create(AppService.class);
        return appService;    }

    private RestAdapter.Builder restAdapterBuilder() {
        return new RestAdapter.Builder().setConverter(new GsonConverter(new GsonBuilder().create()));
    }

}
