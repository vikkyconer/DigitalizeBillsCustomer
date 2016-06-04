package com.example.app.digitalizebillscustomer;

import android.content.Context;
import android.content.Intent;

import com.example.app.digitalizebillscustomer.MainScreen.MainActivity;
import com.example.app.digitalizebillscustomer.MainScreen.VendorMainActivity;
import com.example.app.digitalizebillscustomer.SignInScreen.SignInActivity;
import com.example.app.digitalizebillscustomer.SignUpScreen.SignUpActivity;

/**
 * Created by vikkycorner on 08/04/16.
 */
public class Navigator {

    public static void toMainScreen(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

//    public static void toLoginScreen(Context context) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        context.startActivity(intent);
//    }

    public static void toBillDetailsActivity(Context context, int billId) {
        Intent intent = new Intent(context, BillDetailsActivity.class);
        intent.putExtra("billId", billId);
        context.startActivity(intent);
    }

    public static void toSignInScreen(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    public static void toSingUpScreen(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public static void toVendorMainScreen(Context context) {
        Intent intent = new Intent(context, VendorMainActivity.class);
        context.startActivity(intent);
    }
}
