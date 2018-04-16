package com.example.pankaj.introslider;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pankaj on 14/4/18.
 */

public class SharedPrefs {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;


    // Shared preferences file name
    private static final String PREF_NAME = "first-time";

    private static final String FIRST_TIME = "firstTimeLaunch";

    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void saveFirstTime(boolean isFirstTime) {
        editor.putBoolean(FIRST_TIME, isFirstTime);
        editor.commit();
    }

    public boolean checkFirstTime() {
        return pref.getBoolean(FIRST_TIME, true);
    }

}