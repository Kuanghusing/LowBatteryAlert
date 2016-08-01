package com.kuahusg.helpmybattery.helpmybattery.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import com.kuahusg.helpmybattery.helpmybattery.Util.ReceiverUtil;

/**
 * Created by kuahusg on 16-8-1.
 */
public class BatteryStatusReceiver extends BroadcastReceiver {
    public static final String TAG = "BatteryStatusReceiver";
    private String level;
    private boolean autoDiscount;
    private int second;

    public BatteryStatusReceiver(boolean autoDiscount, String level, int second) {
        this.autoDiscount = autoDiscount;
        this.level = level;
        this.second = second;
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


        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        BatteryLowReceiver receiver = new BatteryLowReceiver(this.level, second, autoDiscount);

        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            context.registerReceiver(receiver, filter);
        } else {
            ReceiverUtil.getInstance(context).unregisterReceiver(context, receiver);

        }
    }
}
