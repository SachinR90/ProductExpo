package com.example.productexpo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.productexpo.BuildConfig;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * This class is used to perform some common functions that are used throughout the application.
 */
@SuppressWarnings({"unused", "WeakerAccess", "ForLoopReplaceableByForEach"})
public class AppUtils {
    /**
     * This function is used to return the current Application Build Version.
     */
    public static String getBuildVersion() {
        return "v_" + BuildConfig.VERSION_CODE + "." + BuildConfig.VERSION_NAME;
    }


    /**
     * This function is used to convert array list to json array
     *
     * @param arrlstData - The Response Array
     */
    public static JSONArray getJsonArray(ArrayList<?> arrlstData) {

        JSONArray jsonArray = new JSONArray();
        Gson gson = new Gson();
        for (Object currentObject : arrlstData) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(gson.toJson(currentObject));
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    /**
     * This function is used to format the Decimal into #.## format
     *
     * @param number -The Input number
     */
    public static String formatDecimal(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }

    /**
     * This function checks if the device is connected to internet or not.
     *
     * @return -true if connected and false vice-versa.
     */
    public static boolean checkIsInternetConnected(Context mContext) {
        boolean connected = false;
        try {
            ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            connected = conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connected;
    }

    /**
     * This is used to check if the given collection is empty or null or filled
     *
     * @param coll is class implementing collection
     * @return if collection is empty or not
     */
    public static boolean isCollectionEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }

    /**
     * Generic method to join the contents of an object array in a single line separated by a character
     *
     * @param objList ArrayList of objects to be joined
     * @param s       string used as a separator i.e. ','
     * @return contents of array in single separated by a single character
     */
    public static String join(ArrayList<Object> objList, String s) {
        if (objList == null) {
            return null;
        }
        StringBuilder stringJoined = new StringBuilder();
        ArrayList<Object> list = new ArrayList<>(objList);
        for (Iterator iterator = list.iterator(); iterator.hasNext(); stringJoined.append(iterator.next())) {
            if (stringJoined.length() != 0) {
                stringJoined.append(s);
            }
        }
        return stringJoined.toString();
    }

    /**
     * This function checks whether the textview is empty or not.
     *
     * @param txtView -The Current View
     */
    public static boolean isEmpty(TextView txtView) {
        return TextUtils.isEmpty(txtView.getText());
    }

    /**
     * This function checks whether the edittext is empty or not.
     *
     * @param edtxtView -The Current View
     */
    public static boolean isEmpty(EditText edtxtView) {
        return TextUtils.isEmpty(edtxtView.getText());
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * OPens url in a webview
     *
     * @param url      url to show in webview
     * @param mContext current context of the application
     */
    public static void showInWebView(String url, Context mContext) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        mContext.startActivity(i);
    }

    /**
     * This function fetches the Device Unique ID from the SecureAndroid Id /Telephony Service/BlueTooth Address/WIFI Address.
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceUniqueID(Context mContext) {
        String strDeviceId;

        try {

            try {
                strDeviceId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
                if (strDeviceId == null) {
                    strDeviceId = "";
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                strDeviceId = "";
            }

            try {
                if (strDeviceId.equalsIgnoreCase("")) {
                    TelephonyManager tManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                    if (tManager.getDeviceId() != null) {
                        strDeviceId = tManager.getDeviceId();
                    } else {
                        strDeviceId = "";
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                strDeviceId = "";
                e.printStackTrace();
            }
            if (strDeviceId.equalsIgnoreCase("")) {
                // Device does not support SIM Card
                // Use alternative method
                // For devices connected with ethernet
                strDeviceId = getMACAddress("eth0");
                if (strDeviceId.equalsIgnoreCase("")) {
                    // For Device having WIFI
                    strDeviceId = getMACAddress("wlan0");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            strDeviceId = "";
        }

        if (strDeviceId.equalsIgnoreCase("")) {
            // Cannot find any perfect Unique ID
            strDeviceId = new Date().toString();
        }
        return strDeviceId;
    }

    /**
     * This function fetches the device Unique ID if it does not have a Sim Slot
     *
     * @param interfaceName - The Hardware component
     */
    @SuppressLint("NewApi")
    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : interfaces) {
                if (interfaceName != null) {
                    if (!networkInterface.getName().equalsIgnoreCase(interfaceName)) {
                        continue;
                    }
                }
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac == null) {
                    return "";
                }
                StringBuilder buf = new StringBuilder();
                for (int idx = 0; idx < mac.length; idx++) {
                    buf.append(String.format("%02X:", mac[idx]));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                return buf.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }


}
