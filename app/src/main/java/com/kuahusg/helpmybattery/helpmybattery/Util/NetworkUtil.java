package com.kuahusg.helpmybattery.helpmybattery.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kuahusg on 16-8-1.
 */
public class NetworkUtil {
    public static final int WIFI_ACTIVE = 1;
    public static final int MOBILE_ACTIVE = 2;
    private static ConnectivityManager connectivityManager;
    private Context mContext;
    private static NetworkUtil networkUtil;
    public static final String TAG = "NETWORKUTIL";

    private NetworkUtil(Context context) {
        this.mContext = context;
    }

    public static NetworkUtil getNetWorkUtil(Context context) {
        if (networkUtil == null) {
            networkUtil = new NetworkUtil(context);
        }
        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return networkUtil;
    }

    public boolean isNetworkActive() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    public int whichType() {
        if (!isNetworkActive())
            return 0;
        switch (connectivityManager.getActiveNetworkInfo().getType()) {
            case ConnectivityManager.TYPE_WIFI:
                return WIFI_ACTIVE;
            case ConnectivityManager.TYPE_MOBILE:
                return MOBILE_ACTIVE;
            default:
                return 0;
        }
    }

    public void toggleWifi(boolean enable) {
        WifiManager wifiManager = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
        if (isNetworkActive()) {
            wifiManager.setWifiEnabled(enable);
        }
    }

    public void toggleMobileData(boolean enable) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Log.v(TAG, "not support reflect!");
            return;
        }
        if (isNetworkActive()) {
            Class<?> cmClass = connectivityManager.getClass();
            Class<?>[] argClass = new Class[1];
            argClass[0] = boolean.class;

            Method method;
            try {
                method = cmClass.getMethod("setMobileDataEnabled", argClass);
                method.invoke(connectivityManager, enable);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
