package com.example.muchikapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * By: El Bryant
 */

public class PrefUtil {
    private Context context;
    private static final String VERSION_CODE = "version_code", NAME_PREFERENCE = "version_code", PREFIJO = "Muchik_";
    public static final String LOGIN_STATUS = "login_status";

    public PrefUtil(Context ctx) {
        this.context = ctx;
    }

    public void saveGenericValue(String campo, String valor) {
        SharedPreferences prefs = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFIJO + campo, valor);
        editor.apply();
    }

    public void clearAll() {
        SharedPreferences prefs = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public String getStringValue(String campo){
        SharedPreferences prefs = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE);
        return prefs.getString(PREFIJO + campo,"");
    }
}