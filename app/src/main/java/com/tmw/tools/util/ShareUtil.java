package com.tmw.tools.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 共享参数类，用来读取或存储一些信息
 */
public class ShareUtil {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static void initShared(Context context){

        preferences = context.getSharedPreferences("information",Context.MODE_PRIVATE);

        editor = preferences.edit();
    }


    public static void putInt(int x,String key){

        editor.putInt(key,x);
        editor.commit();
    }
    public static void putString(String str,String key){
        editor.putString(key,str);
        editor.commit();
    }

    public static void putBoolean(String key,boolean boo){
        editor.putBoolean(key,boo);
        editor.commit();
    }

    public static int getInt(String key){
        return preferences.getInt(key,-1);
    }
    public static String getString(String key){
        return preferences.getString(key,null);
    }
    public static boolean getBoolean(String key){
        return preferences.getBoolean(key,false);
    }

}
