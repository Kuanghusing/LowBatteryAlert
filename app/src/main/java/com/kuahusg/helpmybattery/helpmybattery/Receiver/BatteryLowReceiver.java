package com.kuahusg.helpmybattery.helpmybattery.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.view.WindowManager;

import com.kuahusg.helpmybattery.helpmybattery.Interface.OnFinishListener;
import com.kuahusg.helpmybattery.helpmybattery.UI.Fragment.LowBatteryProgressDialog;
import com.kuahusg.helpmybattery.helpmybattery.Util.NetworkUtil;

/**
 * Created by kuahusg on 16-8-1.
 */
public class BatteryLowReceiver extends BroadcastReceiver {
    private String level;
    private int second;
    private boolean autoDiscount;
    private LowBatteryProgressDialog dialog = null;
    private OnFinishListener listener;


    /**
     * this receiver can't register in manifest
     *
     * @param level
     * @param second
     * @param autoDiscount
     */
    public BatteryLowReceiver(String level, int second, boolean autoDiscount, OnFinishListener listener) {
        this.level = level;
        this.second = second;
        this.autoDiscount = autoDiscount;
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        float toggle_level = Float.parseFloat(this.level);
        int level_now = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float level_percent = level_now / (float) scale * 100;
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;


        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            if (listener != null) {
                listener.finish();
            }
            return;
        }
        /**
         * test notification
         */

//        NotificationTest.showNotification(0, "BatteryLowReceiver: " + level_percent, context);

        if (level_percent == toggle_level && !isCharging) {
            /**
             * need to disconnect the network?
             */
            if (autoDiscount) {
                NetworkUtil.getNetWorkUtil(context).toggleWifi(false);
                NetworkUtil.getNetWorkUtil(context).toggleMobileData(false);
            }
            /**
             * show the alert dialog
             */
            if (dialog == null) {
                dialog = new LowBatteryProgressDialog(context, second);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
            else
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
            if (!dialog.isShowing())
                dialog.show();
        } else if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
