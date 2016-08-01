package com.kuahusg.helpmybattery.helpmybattery.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kuahusg.helpmybattery.helpmybattery.Util.ReceiverUtil;

/**
 * Created by kuahusg on 16-8-1.
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ReceiverUtil.getInstance(context).registerReceiver(context);
    }
}
