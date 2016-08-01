package com.kuahusg.helpmybattery.helpmybattery.UI.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kuahusg.helpmybattery.helpmybattery.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by kuahusg on 16-8-1.
 */
public class AboutDialogFragment extends DialogFragment {
    StringBuilder aboutText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        readResource();

        View view = inflater.inflate(R.layout.fragment_about, container);
        TextView tvAbout = (TextView) view.findViewById(R.id.tv_about);
        tvAbout.setText(Html.fromHtml(aboutText.toString()));
        tvAbout.setMovementMethod(ScrollingMovementMethod.getInstance());

        return view;

    }

    private void readResource() {
        aboutText = new StringBuilder();
        InputStream is = getResources().openRawResource(R.raw.about);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String tmp;
        try {
            while ((tmp = br.readLine()) != null) {
                aboutText.append(tmp);
            }
            is.close();
        } catch (IOException e) {
            Toast.makeText(getActivity(),getString(R.string.test_fail),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
