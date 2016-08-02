package com.kuahusg.helpmybattery.helpmybattery.UI.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

import com.kuahusg.helpmybattery.helpmybattery.R;
import com.kuahusg.helpmybattery.helpmybattery.UI.Fragment.SettingFragment;

/**
 * Created by kuahusg on 16-8-1.
 */
public class SettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getFragmentManager().
                beginTransaction()
                .replace(R.id.frame_layout_setting, new SettingFragment())
                .commit();

/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }
    }

}
