package com.hyht.smarthome.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017-11-27.
 */

public class UiUtils {

    //获取屏幕宽度:
    public static int getScreenWidthPixels(Activity activity) {
        //获取屏幕的宽高的像素
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
