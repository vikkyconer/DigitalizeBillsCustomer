package com.example.app.digitalizebillscustomer;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends ActionBarActivity implements View.OnClickListener {

//    private static String typeUser;

    private RelativeLayout customerLogin, vendorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        initializeViews();
        setEventsForViews();

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigator.toMainScreen(SplashActivity.this);
//                Navigator.toSignInScreen(SplashActivity.this);
                SplashActivity.this.finish();
            }
        }, Constants.SPLASHTIME);*/
    }

    private void setEventsForViews() {
        customerLogin.setOnClickListener(this);
        vendorLogin.setOnClickListener(this);
    }

    private void initializeViews() {
        customerLogin = (RelativeLayout) findViewById(R.id.customer_login);
        vendorLogin = (RelativeLayout) findViewById(R.id.vendor_login);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.customer_login) {
            Constants.typeUser = "customer";
            Navigator.toSignInScreen(this);
        } else if (id == R.id.vendor_login) {
            Constants.typeUser = "vendor";
            Navigator.toSignInScreen(this);
        }
    }
}
