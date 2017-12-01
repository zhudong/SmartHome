package com.hyht.smarthome.ui.fragment;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hyht.smarthome.MainActivity;
import com.hyht.smarthome.R;
import com.hyht.smarthome.base.BaseEntity;
import com.hyht.smarthome.base.BaseFragment;
import com.hyht.smarthome.bean.PayBean;
import com.hyht.smarthome.bean.PayResultBaen;
import com.hyht.smarthome.bean.PhoneBean;
import com.hyht.smarthome.bean.Result;
import com.hyht.smarthome.configs.Constants;
import com.hyht.smarthome.net.BaseObserver;
import com.hyht.smarthome.net.PhoneObserver;
import com.hyht.smarthome.net.RetrofitFactory;
import com.hyht.smarthome.utils.LogUtils;


/**
 * Created by Administrator on 2017-11-27.
 */

public class AccountFragment extends BaseFragment<MainActivity> {

    private View rootView;
    private TextView wifiSSIDTv;
    private EditText wifiPwdEt;
    private Button sendBtn;

    private EditText phoneEt;
    private Button inquiryBtn;
    private TextView phoneTv;
    @Override
    protected void initTitleBar() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.account_send_btn:
                break;
            case R.id.account_inquiry_btn:
                if(!TextUtils.isEmpty(phoneEt.getText()))
                getPhoneAddress(phoneEt.getText().toString());
                break;
        }
    }

    @Override
    public View initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.accout_fragment_layout, null);
        wifiSSIDTv = rootView.findViewById(R.id.account_wifi_ssid_tv);
        wifiPwdEt = rootView.findViewById(R.id.account_wifi_pwd_et);
        sendBtn = rootView.findViewById(R.id.account_send_btn);

        inquiryBtn = rootView.findViewById(R.id.account_inquiry_btn);
        phoneEt = rootView.findViewById(R.id.account_phone_et);
        phoneTv = rootView.findViewById(R.id.account_phone_address_tv);

        WifiManager wifiManager = (WifiManager) mContext.getApplicationContext().getSystemService(mContext.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        wifiSSIDTv.setText("Wi-Fi SSID: " + wifiInfo.getSSID());

        sendBtn.setOnClickListener(this);
        inquiryBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void initData() {

    }

    public void getPhoneAddress(String phone){
        RetrofitFactory.getInstance().API()
                .getPhoneAddress(Long.parseLong(phone), Constants.JUHE_KEY)
                .compose(this.<PhoneBean<Result>>setThread())
                .subscribe(new PhoneObserver<Result>() {
                    @Override
                    protected void onSuccess(PhoneBean<Result> t) throws Exception {
                        phoneTv.setText(t.getResult().getCompany() + t.getResult().getProvince() + t.getResult().getCity() + t.getResult().getCard());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });


    }

    public void recurringPayByRxJava(){
        PayBean bean = new PayBean();
        bean.setReference(56269);
        bean.setAmount(0.009999999776482582f);
        bean.setPaymentData("adyenan0_1_1$kpXJtVY0/mztqnFoKSjuEE87bd1ium9TJu/B/l/g9AAfRsJJPSZYaG/Y+D/7Ur4K560FgSQPsmLovKX9LwtJfz+lgPppeERSnJHo9fF1ulF95ruPfoniJJGLpnIIZBOEsgyIY3BRFWTvDfEp3CwUrOqRHyuIeLY0DuxDZjyVQz5kbNSTWX+onchPG+3ym9G2Xk0CjbBn7GIx37fTfqOPsC3+ajellCTwSPayh4ceY8NBhT1lL0nqNebyQrt4xxa17tCSUiV0kimi10FTqUy/klCvktdfHSu7Y+0irNbOaFOsqt0rhAofwqITO5U9RrJfq2TN9tyKxGshiEj8DmQO0w==$FCsRaGeJNuRFtRpZzkx7Z3dG1VbkeSsHnREuA/qG49dhgS/ZYlBTenzUAYx0cV1ObBS4YjQQFBzEv4VCA8S+OqN4bKi8Q6IsZKUzlDQI1n6SRh98C+pxgmRmFO9YPaznXusiAiC+d7mL5H3Rk0Xfm4wEJGv8uF+MDbg=");
        bean.setCurrency("USD");
        bean.setRecurringDetailReference("8314927557630265");
        RetrofitFactory.getInstance().API()
                .recurringPayByRxJava(bean)
                .compose(this.<BaseEntity<PayResultBaen>>setThread())
                .subscribe(new BaseObserver<PayResultBaen>() {
                    @Override
                    protected void onSuccess(BaseEntity<PayResultBaen> t) throws Exception {
                        LogUtils.d(t.getMsg() + "------------");
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        LogUtils.e(e.toString(), isNetWorkError + "--------------");
                    }
                });
    }
}
