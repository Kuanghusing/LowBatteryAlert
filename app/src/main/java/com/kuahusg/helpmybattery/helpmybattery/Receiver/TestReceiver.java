package com.kuahusg.helpmybattery.helpmybattery.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.WindowManager;
import android.widget.Toast;

import com.kuahusg.helpmybattery.helpmybattery.R;
import com.kuahusg.helpmybattery.helpmybattery.UI.Fragment.LowBatteryProgressDialog;

/**
 * Created by kuahusg on 16-8-1.
 */
public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        LowBatteryProgressDialog dialog = new LowBatteryProgressDialog(context, 10);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        else
//            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, context.getString(R.string.test_fail), Toast.LENGTH_LONG).show();
        }
    }
}
