package com.kuahusg.helpmybattery.helpmybattery.Service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.kuahusg.helpmybattery.helpmybattery.Interface.OnFinishListener;
import com.kuahusg.helpmybattery.helpmybattery.R;
import com.kuahusg.helpmybattery.helpmybattery.Receiver.BatteryLowReceiver;

/**
 * Created by kuahusg on 16-8-1.
 */
public class BatteryLowListenerService extends Service {
    private BatteryLowReceiver receiver;

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
            receiver = new BatteryLowReceiver(toggleLevel, 30, autoDiscount, new OnFinishListener() {
                @Override
                public void finish() {
                    stopSelf();
                }
            });
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(receiver, filter);
    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onDestroy() {
//        Notification notification = new Notification.Builder(this).setContentTitle("service onDestroy()").setSmallIcon(R.mipmap.ic_launcher).build();
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(1, notification);
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }
}
