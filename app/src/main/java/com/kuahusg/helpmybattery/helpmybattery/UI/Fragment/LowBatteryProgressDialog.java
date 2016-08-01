package com.kuahusg.helpmybattery.helpmybattery.UI.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.kuahusg.helpmybattery.helpmybattery.R;

/**
 * Created by kuahusg on 16-8-1.
 */
public class LowBatteryProgressDialog extends ProgressDialog {
    public static final int COUNT = 1;
    Context mContext;
    int second;

    public LowBatteryProgressDialog(Context context, int second) {
        super(context);
        this.mContext = context;
        this.second = second;
        init();
    }



    public void init() {
        setTitle(R.string.dialog_title);
        setButton(BUTTON_NEGATIVE, mContext.getString(R.string.btn_cancel), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LowBatteryProgressDialog.this.dismiss();
            }
        });
        setCancelable(false);

    }

    @Override
    public void show() {
        super.show();
        mHandler.sendEmptyMessage(COUNT);
    }

    public void setSecond(int second) {
        String warn = mContext.getString(R.string.warn);
        setMessage(String.format(warn, second));
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case COUNT:
                    if (second > 0) {
                        setSecond(--second);
                        sendEmptyMessageDelayed(COUNT, 1000);
                    } else
                        LowBatteryProgressDialog.this.dismiss();
            }
        }
    };
}
