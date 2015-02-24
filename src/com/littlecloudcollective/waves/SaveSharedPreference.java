package com.littlecloudcollective.waves;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
	
	static final String PREF_USER_NAME= "username";
	static final String PREF_PASSWORD = "password";
	static final String PREF_API_KEY = "apiKey";
	static final String PREF_USER_ID = "user_id";

	
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

  //Setter - save the user name in SharedPreferences
    public static void setUsername(Context ctx, String username) 
    {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, username);
        editor.commit();
    }
    
  //Setter - save the password in SharedPreferences (maybe remove this method?? Not secure)
    public static void setPassword(Context ctx, String password) 
    {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PASSWORD, password);
        editor.commit();
    }

    //Getter methods
    public static String getUsername(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
    
    public static String getPassword(Context ctx) 
    {
    	return getSharedPreferences(ctx).getString(PREF_PASSWORD, "");
    }
    
}
