package com.kuahusg.helpmybattery.helpmybattery.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.kuahusg.helpmybattery.helpmybattery.R;
import com.kuahusg.helpmybattery.helpmybattery.Service.BatteryLowListenerService;

/**
 * Created by kuahusg on 16-8-1.
 */
public class BatteryStatusReceiver extends BroadcastReceiver {
    public static final String TAG = "BatteryStatusReceiver";
    private static BatteryLowReceiver receiver;
    private Intent intent1;

    public BatteryStatusReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v(TAG, "onReceive()");
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        Log.v(TAG, "status" + intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1));
        Log.v(TAG, "level" + level);
        Log.v(TAG, "scale" + scale);
        Log.v(TAG, "percent:" + level / scale * 100 + "%");


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean enable = preferences.getBoolean(context.getString(R.string.switch_open_close), false);

        if (intent1 == null) {
            intent1 = new Intent(context, BatteryLowListenerService.class);
        }
        if (enable) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
                /**
                 * if battery level is low, then start a service ,in order to start a receiver(battery_change) by service
                 */
                context.getApplicationContext().startService(intent1);
            } else if (intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)) {
                /**
                 * stop the service if battery level is ok
                 */
//                context.getApplicationContext().stopService(intent1);
                intent1 = null;
            }
        }
    }
}
