package com.kuahusg.helpmybattery.helpmybattery.Util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.kuahusg.helpmybattery.helpmybattery.R;

/**
 * Created by kuahusg on 16-8-2.
 */
public class NotificationTest {
    public static void showNotification(int id, String title, Context context) {
        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(context.getClass().getName())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX)
                .build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, notification);
    }
}
