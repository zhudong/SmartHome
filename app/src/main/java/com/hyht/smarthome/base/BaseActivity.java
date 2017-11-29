package com.hyht.smarthome.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.hyht.smarthome.MainActivity;
import com.hyht.smarthome.R;
import com.hyht.smarthome.manager.ActivityController;
import com.hyht.smarthome.manager.BusEvent;
import com.hyht.smarthome.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Longer on 2016/10/26.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
//    public ViewUtils mViewUtils = null;

    DisplayMetrics dm;
    public int mWidthPixels;
    public int mHeightPixels;
//    private SweetAlertDialog mSweetAlertDialog;
//    private View mMenuRoot;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
        //沉浸式标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = setInitView();
        setContentView(view);
//        getSupportActionBar().hide();
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 19) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
            view.setPadding(0, 40, 0, 0);
        }
        //
        //mSweetAlertDialog = new SweetAlertDialog(this);

        //这里这一段会影响弹出的dialog型的Activity，故暂时注释掉
        //getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

//        ButterKnife.bind(this);
//        mViewUtils = new ViewUtils(this);
        //获取屏幕的宽高的像素
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SysApplication.mWidthPixels = dm.widthPixels;
        SysApplication.mHeightPixels = dm.heightPixels;
//        mMenuRoot = View.inflate(this, R.layout.menu_layout, null);
//        mMenuRoot.findViewById(R.id.ll_menu_2home_page).setOnClickListener(new MenuClickLintener());
//        mMenuRoot.findViewById(R.id.ll_menu__share).setOnClickListener(new MenuClickLintener());

        //PushAgent.getInstance(this).onAppStart();
        ActivityController.addActivity(this);
        initView();
    }

    class MenuClickLintener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.ll_menu__share:
//                    share();
//                    break;
//                case R.id.ll_menu_2home_page:
//                    //Toast.makeText(BaseActivity.this, "home", Toast.LENGTH_SHORT).show();
//                    EventBus.getDefault().post(new BusEvent(BusEvent.GO_HOME_PAGE, null));
//                    break;
            }
        }
    }


    /**
     * 初始化视图(例如一系列的注册控件之类的)的方法,重写它就可以了
     */
    public void initView() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.iv_in_title_back) {
//            finish();
//        }
//        if (v.getId() == R.id.tv_in_title_back_tv) finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    /**
     * 返回当前activity(需要注意的是这里要是要返回子类的实例)
     **/
    public BaseActivity myActivity() {
        return this;
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //ActivityController.removeActivity(this);
        EventBus.getDefault().unregister(this);
        //ButterKnife.unbind(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

   /* public String getMachineCode() {
      *//*  final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();*//*
        //LogUtils.e("这是机器码:" + uniqueId);
        //SysApplication.machineCode = uniqueId;
        //NetEngine.setUuid(uniqueId);
        return uniqueId;
    }
*/

    /**
     * @param et 隐藏软键盘
     * @nama yl
     */
    public void Hidekeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();// isOpen若返回true，则表示输入法打开
        if (isOpen) {
            ((InputMethodManager) et.getContext().getSystemService(
                    INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    public void showSoftInput(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    public void hideSoftInput() {
        InputMethodManager m = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (m.isActive()) {
            m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * @param et 显示软键盘
     * @nama yl
     */
    public void Showkeyboard(EditText et) {
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .showSoftInput(et, 0);

    }

    /**
     * 因为我们不知道继承这个类的Activity长什么样子,这里我们就定义为抽象方法让其进行返回来设置
     *
     * @return View
     */
    public abstract View setInitView();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected View getAnchorView() {
        return null;
    }

    PopupWindow mPopupWindow;

    protected void switchMenu() {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(this);
            //mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//            mPopupWindow.setContentView(mMenuRoot);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            mMenuRoot.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    switchMenu();
//                }
//            });

        }
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAsDropDown(getAnchorView());
        }
    }




    public void setIsShowContentView(boolean isShow) {
        mIsShowContenttext = isShow;
    }

    private boolean mIsShowContenttext = false;


    @Subscribe
    public void onEventMainThread(BusEvent event) {
        LogUtils.d("main", this.getClass().getSimpleName());

        if (event.getType() == BusEvent.GO_HOME_PAGE) {
            if (!(this instanceof MainActivity)) {
                finish();
            }
        }

/*
        if (event.getType() == BusEvent.SEND_DOWNLOAD_IMAGES_PROGRESS) {
            if (!((this.hashCode() + "").equals(event.getStrParam())))
                return;
            int temp = event.getIntValue();
            KLog.i("downlaod " + temp);
            if (temp == 0) {
                //出现下载进度条提示
                showProgressDiaLog(SweetAlertDialog.PROGRESS_TYPE, getTitleString(temp));
                getSweetAlertDialog().setCancelable(true);
            }
            getSweetAlertDialog().setTitleText(getTitleString(temp));
            if (ShareManager.urls != null && ShareManager.urls.size() == temp) {
                //下载完毕，显示选择分享方式activity
                getSweetAlertDialog().changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                getSweetAlertDialog().setTitleText(getString(R.string.item_name_has_been_copy));
                dissMissProgressDiaLog(1000L);
                toActivity(ShareSelectActiviy.class);
            } else {
                //更改下载进度条提示

            }
        }
        */
    }

    /*public String getTitleString(int temp) {
        if (ShareManager.urls == null) {
            return "null";
        }
        return getString(R.string.pictures_downing) + temp + "/" + ShareManager.urls.size();
    }*/

    public void toActivity(Class target) {
        this.startActivity(new Intent(this, target));
    }

    public void toActivityByBoolean(Class target, String key, boolean value) {
        Intent intent = new Intent(this, target);
        intent.putExtra(key, value);
        this.startActivity(intent);
    }


    /**
     * 跳转Activity的方法,传入我们需要的参数即可
     */
    public <T> void startDDMActivity(Class<T> activity, boolean isAinmain) {
        Intent intent = new Intent(myActivity(), activity);
        startActivity(intent);
        //是否需要开启动画(目前只有这种x轴平移动画,后续可以添加):
        if (isAinmain) {
            this.overridePendingTransition(R.anim.activity_translate_x_in, R.anim.activity_translate_x_out);
        }
    }


    protected void share() {

    }

    public void unEnableShare() {
//        if (mMenuRoot != null) {
//            mMenuRoot.findViewById(R.id.ll_menu__share).setVisibility(View.GONE);
//            mMenuRoot.findViewById(R.id.tv_divide).setVisibility(View.GONE);
//        }
    }


//    public void showCustomerToastSafly(final String text) {
//        UIUtils.postTaskSafely(new Runnable() {
//            @Override
//            public void run() {
//                CustomToast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public <T>ObservableTransformer<T, T> setThread(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
