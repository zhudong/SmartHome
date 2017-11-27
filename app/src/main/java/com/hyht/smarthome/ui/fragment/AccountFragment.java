package com.hyht.smarthome.ui.fragment;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hyht.smarthome.MainActivity;
import com.hyht.smarthome.R;
import com.hyht.smarthome.base.BaseFragment;


/**
 * Created by Administrator on 2017-11-27.
 */

public class AccountFragment extends BaseFragment<MainActivity> {

    private View rootView;
    private TextView wifiSSIDTv;
    private EditText wifiPwdEt;

    @Override
    protected void initTitleBar() {

    }

    @Override
    public View initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.accout_fragment_layout, null);
        wifiSSIDTv = rootView.findViewById(R.id.account_wifi_ssid_tv);
        wifiPwdEt = rootView.findViewById(R.id.account_wifi_pwd_et);

        WifiManager wifiManager = (WifiManager) mContext.getApplicationContext().getSystemService(mContext.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        wifiSSIDTv.setText("Wi-Fi SSID: " + wifiInfo.getSSID());
        return rootView;
    }

    @Override
    public void initData() {

    }
}
