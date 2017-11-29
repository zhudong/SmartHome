package com.hyht.smarthome.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.hyht.smarthome.MainActivity;
import com.hyht.smarthome.R;
import com.hyht.smarthome.base.BaseActivity;

/**
 * Created by Administrator on 2017-11-29.
 */

public class SplashActivity extends BaseActivity {
    private View rootView;

    @Override
    public View setInitView() {
        rootView = LayoutInflater.from(this).inflate(R.layout.splash_activity, null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
        return rootView;
    }

}
