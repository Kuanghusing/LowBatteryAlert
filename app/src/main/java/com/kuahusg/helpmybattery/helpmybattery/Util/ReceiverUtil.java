package com.kuahusg.helpmybattery.helpmybattery.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.kuahusg.helpmybattery.helpmybattery.Receiver.BatteryStatusReceiver;
import com.kuahusg.helpmybattery.helpmybattery.R;

/**
 * Created by kuahusg on 16-8-1.
 */
public class ReceiverUtil {
    static boolean enable = false;
    static String toggleLevel;
    static boolean autoDiscount;
    Context context;
    SharedPreferences preferences;
    public static final int SECOND = 30;

    private static BatteryStatusReceiver receiver;


    private ReceiverUtil(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (receiver == null) {
            initReceiver();
        }
    }

    public static ReceiverUtil getInstance(Context context) {
        return new ReceiverUtil(context);
    }

    public void registerReceiver(Context context) {
//        enable = preferences.getBoolean(context.getString(R.string.switch_open_close), true);
        initBatteryReceiver(receiver, context);
    }

    private void initBatteryReceiver(BroadcastReceiver receiver, Context context) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
//        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        context.registerReceiver(receiver, filter);
    }

    public void unregisterReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null)
            return;
        if (!enable)
            return;
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void unregisterReceiver(Context context) {

        if (receiver == null)
            return;
        if (!enable)
            return;
        try {
            context.unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initReceiver() {
        enable = preferences.getBoolean(context.getString(R.string.switch_open_close), true);
        toggleLevel = preferences.getString(context.getString(R.string.battery_level), "-1");
        autoDiscount = preferences.getBoolean(context.getString(R.string.auto_disconnect), false);
        receiver = new BatteryStatusReceiver(autoDiscount, toggleLevel, SECOND);
    }
}
