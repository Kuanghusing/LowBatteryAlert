package com.kuahusg.helpmybattery.helpmybattery.UI.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kuahusg.helpmybattery.helpmybattery.R;
import com.kuahusg.helpmybattery.helpmybattery.Receiver.BatteryStatusReceiver;
import com.kuahusg.helpmybattery.helpmybattery.UI.Fragment.LowBatteryProgressDialog;
import com.kuahusg.helpmybattery.helpmybattery.Util.NetworkUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnToggleMobileData;
    Button btnShowDialog;
    Button btnOpenSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToggleMobileData = (Button) findViewById(R.id.btn_toggle_mobile_data);
        btnShowDialog = (Button) findViewById(R.id.btn_show_dialog);
        btnToggleMobileData.setOnClickListener(this);
        btnShowDialog.setOnClickListener(this);
        btnOpenSetting = (Button) findViewById(R.id.btn_setting);
        btnOpenSetting.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toggle_mobile_data:
                boolean active = NetworkUtil.getNetWorkUtil(MainActivity.this).isNetworkActive();
                Toast.makeText(MainActivity.this, "active? " + active, Toast.LENGTH_SHORT).show();

                NetworkUtil.getNetWorkUtil(MainActivity.this).toggleWifi(!active);
                break;
            case R.id.btn_show_dialog:
                LowBatteryProgressDialog dialog = new LowBatteryProgressDialog(MainActivity.this, 5);
                dialog.show();
                break;
            case R.id.btn_setting:
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                break;


        }
    }
}
