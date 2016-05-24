package com.example.app.digitalizebillscustomer;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigator.toMainScreen(SplashActivity.this);
//                Navigator.toSignInScreen(SplashActivity.this);
                SplashActivity.this.finish();
            }
        }, Constants.SPLASHTIME);
    }
}
