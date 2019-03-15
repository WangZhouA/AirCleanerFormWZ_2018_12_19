package com.lib.fast.common.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * @2015-11-15
 * @下午1:41:38 sp的工具类封装
 */
public class SPUtils {


    /**
     * @param context
     * @param key     关键字
     * @param value   值
     */

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        //添加保存数据
        sp.edit().putBoolean(key, value).apply();

    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);

    }

    public static String putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        //添加保存数据
        sp.edit().putString(key, value).apply();

        return key;
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        //添加保存数据
        sp.edit().putInt(key, value).apply();

    }

    public static int getInt(Context context, String key,  int defValue) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);

    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        //添加保存数据
        sp.edit().putLong(key, value).apply();

    }

    public static long getLong(Context context, String key, Long defValue) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);

    }

    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sp.edit();
        mEditor.remove(key);
        mEditor.commit();
    }
    // 清空记录
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    //存数组
    public void setSharedPreference(Context context,String key, String[] values) {
        String regularEx = "#";
        String str = "";
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        if (values != null && values.length > 0) {
            for (String value : values) {
                str += value;
                str += regularEx;
            }
            SharedPreferences.Editor et = sp.edit();
            et.putString(key, str);
            et.commit();
        }
    }


    public String[] getSharedPreference(Context context,String key) {
        String regularEx = "#";
        String[] str = null;
        SharedPreferences sp = context.getSharedPreferences(MyConstaints.ALL_SPFILE_NAME, Context.MODE_PRIVATE);
        String values;
        values = sp.getString(key, "");
        str = values.split(regularEx);

        return str;
    }


}
