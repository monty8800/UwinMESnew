package com.rlzz.uwinmes.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.rlzz.uwinmes.App;

/**
 * PreferencesUtils, easy to get or put data
 */
public class PreferencesManager {

    public static final String PREFERENCE_NAME = "Preferences_File";

    private static SharedPreferences settings =
            App.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

    private PreferencesManager() {
    }

    /**
     * put string preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putString(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
        return true;
    }

    /**
     * get string preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     * @see #getString(String, String)
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    /**
     * get string preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     */
    public static String getString(String key, String defaultValue) {
        return settings.getString(key, defaultValue);
    }

    /**
     * put int preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
        return true;
    }

    /**
     * get int preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a int
     * @see #getInt(String, int)
     */
    public static int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * get int preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a int
     */
    public static int getInt(String key, int defaultValue) {
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putLong(String key, long value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
        return true;
    }

    /**
     * get long preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long
     * @see #getLong(String, long)
     */
    public static long getLong(String key) {
        return getLong(key, -1);
    }

    /**
     * get long preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long
     */
    public static long getLong(String key, long defaultValue) {
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putFloat(String key, float value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        editor.apply();
        return true;
    }

    /**
     * get float preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float
     * @see #getFloat(String, float)
     */
    public static float getFloat(String key) {
        return getFloat(key, -1);
    }

    /**
     * get float preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float
     */
    public static float getFloat(String key, float defaultValue) {
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new attr.xml were successfully written to persistent
     * storage.
     */
    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
        return true;
    }

    public static void putBooleanApply(String key, boolean value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean
     * @see #getBoolean(String, boolean)
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * get boolean preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return settings.getBoolean(key, defaultValue);
    }
}