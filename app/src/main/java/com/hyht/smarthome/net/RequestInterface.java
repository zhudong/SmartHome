package com.hyht.smarthome.net;

import com.hyht.smarthome.bean.AdyenUserInfoBean;
import com.hyht.smarthome.bean.PayBean;
import com.hyht.smarthome.bean.PayResultBaen;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017-11-27.
 */

public interface RequestInterface {
    @GET("adyen/listRecurringDetails/uin/102802")
    Call<ResponseBody> getUserInfo();

    @HTTP(method = "GET", path = "adyen/listRecurringDetails/uin/{uId}", hasBody = false)
    Call<AdyenUserInfoBean> getUserInfoByHttp(@Path("uId") int uId);

    @POST("recurringPay")
    Call<PayResultBaen> recurringPay(@Body PayBean payBean);

    @POST("recurringPayByRxJava")
    Observable<PayResultBaen> recurringPayByRxJava(@Body PayBean bean);
}
