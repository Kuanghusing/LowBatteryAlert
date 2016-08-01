package com.kuahusg.helpmybattery.helpmybattery.UI.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kuahusg.helpmybattery.helpmybattery.R;
import com.kuahusg.helpmybattery.helpmybattery.UI.Fragment.SettingFragment;
import com.kuahusg.helpmybattery.helpmybattery.Util.ReceiverUtil;

/**
 * Created by kuahusg on 16-8-1.
 */
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getFragmentManager().
                beginTransaction()
                .replace(R.id.frame_layout_setting, new SettingFragment())
                .commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ReceiverUtil.getInstance(this).registerReceiver(this);
    }

    /*private void init() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        enable = preferences.getBoolean(getString(R.string.switch_open_close), false);
        toggleLevel = preferences.getString(getString(R.string.battery_level), "-1");
        autoDiscounet = preferences.getBoolean(getString(R.string.auto_disconnect), false);
        BatteryLowReceiver receiver = new BatteryLowReceiver(toggleLevel, SECOND, autoDiscounet);

        if (enable)
            initBatteryReceiver(receiver);
        else
            unregisterReceiver(receiver);
    }

    public void initBatteryReceiver(BatteryLowReceiver receiver) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
//        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(receiver, filter);
    }*/
}
