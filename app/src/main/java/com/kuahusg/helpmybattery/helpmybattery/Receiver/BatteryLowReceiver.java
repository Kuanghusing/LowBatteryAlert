package com.kuahusg.helpmybattery.helpmybattery.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.view.WindowManager;

import com.kuahusg.helpmybattery.helpmybattery.UI.Fragment.LowBatteryProgressDialog;
import com.kuahusg.helpmybattery.helpmybattery.Util.NetworkUtil;

/**
 * Created by kuahusg on 16-8-1.
 */
public class BatteryLowReceiver extends BroadcastReceiver {
    private String level;
    private int second;
    private boolean autoDiscount;

    public BatteryLowReceiver(String level, int second, boolean autoDiscount) {
        this.level = level;
        this.second = second;
        this.autoDiscount = autoDiscount;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        float toggle_level = Float.parseFloat(this.level);
        int level_now = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float level_percent = level_now / (float) scale;
        if (level_percent <= toggle_level) {
            if (autoDiscount) {
                NetworkUtil.getNetWorkUtil(context).toggleWifi(false);
                NetworkUtil.getNetWorkUtil(context).toggleMobileData(false);
            }
            LowBatteryProgressDialog dialog = new LowBatteryProgressDialog(context, second);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
            else
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
            dialog.show();
        }
    }
}
