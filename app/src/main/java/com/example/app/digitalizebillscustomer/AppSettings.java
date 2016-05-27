package com.example.app.digitalizebillscustomer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vikkycorner on 27/05/16.
 */
public class AppSettings {
    private static final String APP_SHARED_PREFERENCE_NAME =
            "com.ideabank.app.digitalizebillscustomer";
    private static SharedPreferences prefs = null;

    public static final String PREF_IS_USER_LOGGED_IN = "PREF_IS_USER_LOGGED_IN";
    public static final String PREF_USER_ID = "PREF_USER_ID";
    public static final String PREF_USER_FIRST_NAME = "PREF_USER_FIRST_NAME";
    public static final String PREF_USER_LAST_NAME = "PREF_USER_LAST_NAME";
    public static final String PREF_USER_IMAGE_PUBLIC_ID = "PREF_USER_IMAGE_PUBLIC_ID";
    public static final String PREF_USER_DATE_OF_BIRTH = "PREF_USER_DATE_OF_BIRTH";
    public static final String PREF_USER_GENDER = "PREF_USER_GENDER";
    public static final String PREF_GCM_REGISTRATION_ID = "PREF_GCM_REGISTRATION_ID";
    public static final String PREF_GCM_REGISTRATION_STATUS = "PREF_GCM_REGISTRATION_STATUS";
    public static final String PREF_SERVER_REGISTRATION_STATUS = "PREF_SERVER_REGISTRATION_STATUS";

    public static String getValue(Context context, String key, String defaultValue) {
        prefs = context.getSharedPreferences(APP_SHARED_PREFERENCE_NAME, 0);
        return prefs.getString(key, defaultValue);
    }

    public static void setValue(Context context, String key, String value) {
        prefs = context.getSharedPreferences(APP_SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value.toString());
        editor.commit();
    }

    public static void clearAllPrefs(Context context) {
        SharedPreferences settings =
                context.getSharedPreferences(APP_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }
}
