package com.kuahusg.helpmybattery.helpmybattery.Service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.kuahusg.helpmybattery.helpmybattery.R;
import com.kuahusg.helpmybattery.helpmybattery.Receiver.BatteryLowReceiver;

/**
 * Created by kuahusg on 16-8-1.
 */
public class BatteryLowListenerService extends Service {
    private static BatteryLowReceiver receiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * test notification and receiver
         */
//        NotificationTest.showNotification(2, "BatteryLowListenerService", getApplicationContext());


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String toggleLevel = preferences.getString(getString(R.string.battery_level), "-1");
        boolean autoDiscount = preferences.getBoolean(getString(R.string.auto_disconnect), false);


        if (receiver == null) {
            receiver = new BatteryLowReceiver(toggleLevel, 30, autoDiscount);
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
//        NotificationTest.showNotification(5, "Service onDestroy", this);
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }
}
