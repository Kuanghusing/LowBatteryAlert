package com.kuahusg.helpmybattery.helpmybattery.UI.Fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.kuahusg.helpmybattery.helpmybattery.R;
import com.kuahusg.helpmybattery.helpmybattery.Receiver.TestReceiver;

/**
 * Created by kuahusg on 16-8-1.
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    Preference preferenceTest;
    Preference preferenceAbout;
    Preference preferenceSwitch;
    Preference preferenceAutoDiscount;
    Preference preferenceToggle;
    TestReceiver receiver;


    public static final String TEST_ACTION = "com.kuahusg.helpmybattery.test_broadcast";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        initEvent();

        IntentFilter filter = new IntentFilter();
        filter.addAction(TEST_ACTION);
        receiver = new TestReceiver();
        getActivity().registerReceiver(receiver, filter);


    }

    private void initEvent() {
        preferenceTest = findPreference(getString(R.string.test));
        preferenceTest.setOnPreferenceClickListener(this);
        preferenceAbout = findPreference(getString(R.string.about));
        preferenceAbout.setOnPreferenceClickListener(this);

        preferenceSwitch = findPreference(getString(R.string.switch_open_close));
        preferenceAutoDiscount = findPreference(getString(R.string.auto_disconnect));
        preferenceToggle = findPreference(getString(R.string.battery_level));
        preferenceSwitch.setOnPreferenceChangeListener(this);
/*        preferenceSwitch.setOnPreferenceChangeListener(this);
        preferenceAutoDiscount.setOnPreferenceChangeListener(this);
        preferenceToggle.setOnPreferenceChangeListener(this);*/
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == preferenceTest) {
            try {
                Intent intent = new Intent(TEST_ACTION);
                getActivity().sendBroadcast(intent);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), getString(R.string.test_fail), Toast.LENGTH_LONG).show();

            }
        } else if (preference == preferenceAbout) {
            new AboutDialogFragment().show(getFragmentManager(), "aboutFragment");
        }

        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        if (preference == preferenceSwitch) {
            preferenceAutoDiscount.setEnabled((boolean) o);
            preferenceToggle.setEnabled((boolean) o);

        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
