package com.example.productexpo.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.productexpo.application.ProductExpoApp;
import com.example.productexpo.entities.ProductResponse;
import com.google.gson.Gson;

/**
 * Persist data in Shared Preference.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Preferences {

    private static final String SESSION_NAME = Preferences.class.getSimpleName();
    private static Preferences preferences;

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPref;

    /**
     * private constructor, so as  to make it singleton
     *
     * @param context - current application context
     */
    @SuppressLint("CommitPrefEdits")
    private Preferences(Context context) {
        sharedPref = context.getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public static Preferences getInstance() {
        if (preferences == null) {
            preferences = new Preferences(ProductExpoApp.getAppContext());
        }
        return preferences;
    }

    public void removeAll() {
        editor.clear();
        editor.commit();
    }

    public void removeKey(String preKey) {
        editor.remove(preKey);
        editor.commit();
    }

    public void storeData(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void storeData(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void storeData(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPref.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return sharedPref.getBoolean(key, false);
    }

    public int getInt(String key) {
        return sharedPref.getInt(key, 0);
    }

    /**
     * This is used to set object as string into session
     *
     * @param key   object's key
     * @param value object's value
     */
    public void setObjectAsString(String key, String value) {
        Log.i(key, value);
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * This is used to get object string from session
     *
     * @param key to return object
     * @return String as json
     */
    public String getObjectAsString(String key) {
        return sharedPref.getString(key, null);
    }

    /**
     * This is used to check key is present in session or not
     *
     * @param key check if this key is present in the storage
     * @return true/false based on key present or not
     */
    public boolean isKeyPresent(String key) {
        return sharedPref.contains(key);
    }

    /**
     * This is used to delete object from session
     *
     * @param key to remove from the preference
     */
    private void removeObject(String key) {
        editor.remove(key);
        editor.commit();
    }

    public ProductResponse getProductResponseFromKey(String key) {
        ProductResponse response;
        String objectAsString = getObjectAsString(key);
        try {
            response = new Gson().fromJson(objectAsString, ProductResponse.class);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Class for keeping all the keys used for shared preferences in one place.
     */
    @SuppressWarnings("unused")
    public interface Key {
        /* Recommended naming convention:
         * ints, floats, doubles, longs:
         * SAMPLE_INT, SAMPLE_LONG etc.
         *
         * boolean: IS_SAMPLE, HAS_SAMPLE, CONTAINS_SAMPLE
         *
         * String: SAMPLE_KEY, SAMPLE_STR or just SAMPLE
         */
        String KEY_CART_LIST = "CART_SAVED_LIST";
        String KEY_PRODUCT_LIST = "PRODUCT_LIST";
    }
}
