package com.hyht.smarthome.net;

import com.hyht.smarthome.bean.AdyenUserInfoBean;
import com.hyht.smarthome.bean.PayBean;
import com.hyht.smarthome.bean.PayResultBaen;
import com.hyht.smarthome.configs.Constants;
import com.hyht.smarthome.utils.LogUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017-11-27.
 */

public class NetEngine {
    static Retrofit retrofit;

    public NetEngine() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static AdyenUserInfoBean postRequest() {
        final AdyenUserInfoBean[] adyenUserInfo = {new AdyenUserInfoBean()};
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<AdyenUserInfoBean> call = request.getUserInfoByHttp(102802);
        call.enqueue(new Callback<AdyenUserInfoBean>() {
            @Override
            public void onResponse(Call<AdyenUserInfoBean> call, Response<AdyenUserInfoBean> response) {
                adyenUserInfo[0] = response.body();
            }

            @Override
            public void onFailure(Call<AdyenUserInfoBean> call, Throwable t) {

            }
        });
        return adyenUserInfo[0];
    }

    public static void recurringPay(PayBean bean) {
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<PayResultBaen> call = requestInterface.recurringPay(bean);
        call.enqueue(new Callback<PayResultBaen>() {
            @Override
            public void onResponse(Call<PayResultBaen> call, Response<PayResultBaen> response) {
                LogUtils.d(response.body().authCode + "--------------------");
            }

            @Override
            public void onFailure(Call<PayResultBaen> call, Throwable t) {

            }
        });
    }
}
