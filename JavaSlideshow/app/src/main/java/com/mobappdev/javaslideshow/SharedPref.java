package com.mobappdev.javaslideshow;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private SharedPreferences sharedPref;
    private String prefName = "SlideshowPref";

    public void save (Context context, String key_name, int value){
        sharedPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key_name, value);
        editor.commit();
    }

    public int getValueInt(Context context, String key){
        sharedPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return sharedPref.getInt(key,0);
    }


}
