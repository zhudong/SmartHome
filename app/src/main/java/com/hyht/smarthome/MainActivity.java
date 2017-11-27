package com.hyht.smarthome;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;


import com.hyht.smarthome.base.BaseActivity;
import com.hyht.smarthome.manager.BusEvent;
import com.hyht.smarthome.ui.fragment.AccountFragment;
import com.hyht.smarthome.ui.fragment.HomeFragment;
import com.hyht.smarthome.ui.view.CustomToast;
import com.hyht.smarthome.ui.view.NoteRadioGroup;
import com.zhy.m.permission.MPermissions;

import java.util.ArrayList;
import java.util.List;

import com.hyht.smarthome.configs.PermissionCode;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;


public class MainActivity extends BaseActivity {
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentLists;
    private int mFrragmentIndex = 0;
    static Handler handler = new Handler();
    public NoteRadioGroup mRbgHomeFragment;
    private ImageView mIv_demand_red_point;
    private ImageView mIv_package_red_point;
    private LinearLayout fl_home_btn_top;
    private FrameLayout fl_home_button;
    private ImageView iv_home_btn;
    private boolean isShowingAnimation = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View setInitView() {
        View mainView = View.inflate(this, R.layout.activity_main, null);
        mRbgHomeFragment = (NoteRadioGroup) mainView.findViewById(R.id.rbg_home_fragment);
        mIv_demand_red_point = (ImageView) mainView.findViewById(R.id.iv_demand_red_point);
        mIv_package_red_point = (ImageView) mainView.findViewById(R.id.iv_package_red_point);
        fl_home_btn_top = (LinearLayout) mainView.findViewById(R.id.fl_home_btn_top);
        fl_home_button = (FrameLayout) mainView.findViewById(R.id.fl_home_button);
        fl_home_button.setOnClickListener(this);
        iv_home_btn = (ImageView) mainView.findViewById(R.id.iv_home_btn);
        //KLog.i("YDK ,run");
        //    AccountManager.getInstance().login(this);
        //AccountManager.getInstance().getRedPoint();
        //        AccountManager.getInstance().loginByEmail("feicui@qq.com", "111111", null);

        return mainView;
    }

    @Override
    public void initView() {
        initData();
        initListener();

//        MPermissions.requestPermissions(this, PermissionCode.readSDcard, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        boolean booleanExtra = getIntent().getBooleanExtra(ConfigManager.KEY_JUMP_SETTING, false);
//        boolean isJumpMeFrag = getIntent().getBooleanExtra(Constants.KEY_IS_JUMP_MEFRAG, false);
//        if (booleanExtra || isJumpMeFrag) {
//            mRbgHomeFragment.check(R.id.rb_me);
//            if (isJumpMeFrag) showDialog();
//            if (booleanExtra) {
//                Intent intent = new Intent(this, AccountSettingActivity.class);
//                intent.putExtra(AccountSettingActivity.KEY_JUMP_PREFERENCE, true);
//                startActivity(intent);
//            }
//        } else {
            //默认主页第一个rb是被选中:
            mRbgHomeFragment.check(R.id.rb_home);
//            showHomeTips();
//        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_home_button:
//                operateHomeBtnView(!isShowBtnView);
                break;
//            case R.id.rl_source:
//                //CustomToast.makeText(this, "我是货源", Toast.LENGTH_SHORT).show();
//                if (!isShowingAnimation) {
//                    if (mFrragmentIndex != 4) {
//                        switchFrag(4);
//                        RadioButton child = (RadioButton) mRbgHomeFragment.findViewById(R.id.rb_source);
//                        child.setChecked(true);
//                    }
////                    operateHomeBtnView(false);
//                }
//
//                break;
//            case R.id.rl_group:
//                if (!isShowingAnimation) {
//                    //CustomToast.makeText(this, "我是拼单", Toast.LENGTH_SHORT).show();
//                    if (mFrragmentIndex != 5) {
//                        switchFrag(5);
//                        RadioButton child = (RadioButton) mRbgHomeFragment.findViewById(R.id.rb_source);
//                        child.setChecked(true);
//                    }
////                    operateHomeBtnView(false);
//                }
//
//                break;
//            case R.id.rl_mask:
//                if (!isShowingAnimation)
////                    operateHomeBtnView(!isShowBtnView);
//                break;
        }
    }


    private void initListener() {

        mRbgHomeFragment.setOnCheckedChangeListener(new NoteRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(NoteRadioGroup group, int checkedId) {
                // 需要根据回调的checkedId,进行ViewPager的切换
                switch (checkedId) {
                    case R.id.rb_home:// 首页
                        mFrragmentIndex = 0;
                        break;
                    case R.id.rb_need:// 商品管理
                        mFrragmentIndex = 1;
                        break;
                    case R.id.rb_package:// 添加商品
                        mFrragmentIndex = 2;
                        break;
                    case R.id.rb_account:// 我的订单
                        mFrragmentIndex = 3;
                        break;
                    default:
                        break;
                }
                switchFrag(mFrragmentIndex);
            }
        });

    }

    boolean isInSwithch;

    public synchronized void switchFrag(final int index) {
//        UserManager.getInstance().getAllRedPointNum();
        mFrragmentIndex = index;
        if (isInSwithch)
            return;
        isInSwithch = true;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //LogUtils.d("switch", "switch fragmet");
                mFragmentManager = getSupportFragmentManager();
                Fragment fragmentByTag = mFragmentManager.findFragmentByTag(mFrragmentIndex + "");
                if (fragmentByTag == null) {
                    fragmentByTag = mFragmentLists.get(mFrragmentIndex);
                }/* else {
                每次切换到第一页的时候 判断一下状态
                    if (index == 0 || index == 4) {
                        OrderStateManager.getInstance().init();
                    }
                }*/

                FragmentTransaction transaction = mFragmentManager.beginTransaction();

                if (!fragmentByTag.isAdded()) {    // 先判断是否被add过
                    transaction.add(R.id.container, fragmentByTag, mFrragmentIndex + "");
                    for (int i = 0; i < mFragmentLists.size(); i++) {
                        if (i == mFrragmentIndex) {
                            transaction.show(mFragmentLists.get(i));
                        } else {
                            transaction.hide(mFragmentLists.get(i));
                        }
                    }
                } else {
                    for (int i = 0; i < mFragmentLists.size(); i++) {
                        if (i == mFrragmentIndex) {
                            transaction.show(mFragmentLists.get(i));
                        } else {
                            transaction.hide(mFragmentLists.get(i));
                        }
                    }
                }
                transaction.commitAllowingStateLoss();
                isInSwithch = false;
//                if (index == 0) showHomeTips();
            }
        }, 0);

    }


    private void initData() {
        mFragmentLists = new ArrayList<>();
        mFragmentLists.add(new HomeFragment());
        mFragmentLists.add(new HomeFragment());
        mFragmentLists.add(new HomeFragment());
        mFragmentLists.add(new AccountFragment());
//        mFragmentLists.add(new MyNeedFragment());
//        mFragmentLists.add(new MyPackageFragment());
//        mFragmentLists.add(new MeFragment());
//        mFragmentLists.add(new ProduSrcMainFragment());
//        mFragmentLists.add(new CrowdListFragment());
//        RedPointCountManager.getOrderCount();
        //HelpSignedLocalDataSource.getInstance().initLoaclData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    int mBackPressedCount;

    @Override
    public void onBackPressed() {
        Log.d("MainActivity", "------------mFrragmentIndex: " + mFrragmentIndex);
        //closeLoading();

        if (mBackPressedCount >= 1) {
            super.onBackPressed();
        } else {
            CustomToast.makeText(this, getString(R.string.String_back_toast), Toast.LENGTH_SHORT).show();
            mBackPressedCount++;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBackPressedCount = 0;
            }
        }, 2000);
    }

    public int getFrragmentIndex() {
        return mFrragmentIndex;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //  @Subscribe
    public void onEventMainThread(BusEvent event) {
        super.onEventMainThread(event);
        if (event.getType() == BusEvent.GO_HOME_PAGE) {
            mFrragmentIndex = 0;
            switchFrag(mFrragmentIndex);
            RadioButton child = (RadioButton) mRbgHomeFragment.getChildAt(mFrragmentIndex);
            child.setChecked(true);
        }


    }


    /**
     * 需求和包裹的小红点方法
     *
     * @param demandCount  需求单数量
     * @param packageCount 包裹数量
     */
    public void showDAndPRedPoint(int demandCount, int packageCount) {
        mIv_demand_red_point.setVisibility(demandCount > 0 ? View.VISIBLE : View.GONE);
        mIv_package_red_point.setVisibility(packageCount > 0 ? View.VISIBLE : View.GONE);
    }

    /*适配6.0 权限检查*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(PermissionCode.readSDcard)
    public void requestContactSuccess() {
//        new UpdateManager().getNewVersion(this, 0);
    }

    @PermissionDenied(PermissionCode.readSDcard)
    public void requestContactFailed() {
    }
}
