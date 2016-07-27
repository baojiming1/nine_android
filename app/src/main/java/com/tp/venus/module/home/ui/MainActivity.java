package com.tp.venus.module.home.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.gestures.commons.DepthPageTransformer;
import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.adapter.ViewPageInfo;
import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.content.ui.fragment.MainFragment;
import com.tp.venus.module.home.receiver.MessageReceiver;
import com.tp.venus.module.home.ui.view.IMainView;
import com.tp.venus.module.message.presenter.IMainPersenter;
import com.tp.venus.module.message.presenter.impl.MainPersenter;
import com.tp.venus.module.shop.ui.fragment.ShopHomeFragemtn;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.ui.fragment.MyselfFragment;
import com.tp.venus.util.AppManager;
import com.tp.venus.util.JPushUtil;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.util.UmengUtil;
import com.tp.venus.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @FileName FragmentActivity
 * @Description 主页
 * @Author zy
 * @Date 2015-12-08 09:18
 * @Version V 1.0
 */
public class MainActivity extends BaseActivity implements BaseView, IMainView {

    public static final String JUMP_FRAGMENT = "jump";
    public static final String HINT_KEY = "hint_key";
    public static final int shopping = 0, home = 1, my = 2;        //0:商城  1:首页  2: 我的

    @InjectView(R.id.mNoScrollViewPager)
    NoScrollViewPager mNoScrollViewPager;
    @InjectView(R.id.ibHome)
    ImageView ibHome;
    @InjectView(R.id.iShopping)
    ImageView iShopping;
    @InjectView(R.id.ibMy)
    ImageView ibMy;
    @InjectView(R.id.message_notice)
    TextView messageNotice;
    @InjectView(R.id.iShoppingTv)
    TextView iShoppingTv;
    @InjectView(R.id.ibMyTv)
    TextView ibMyTv;

    private int currentTabIndex = 0;// 当前fragment的index
    private MainFragmentPagerAdapter mMainFragmentPagerAdapter;

    public static boolean isForeground = false;
    private long exitTime = 0;

    private MessageReceiver mMessageReceiver;
    private IMainPersenter mMainPersenter;
    private ShopHomeFragemtn mShopHomeFragemtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (mMainPersenter == null) {
            mMainPersenter = new MainPersenter(this);
        }
        mMainPersenter.refreshToken();
        initView();
        updateApk();
        registerMessageReceiver();
    }

    @Override
    protected void setColorPrimary() {
        super.setColorPrimary();
    }





    //注册信息推送广播器
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver(messageNotice);
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MessageReceiver.MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        setColorPrimary();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 返回二次退出
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.getInstance().show("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getInstance().AppExit(this);
        }
    }

    //检查更新
    private void updateApk() {
        UmengUtil.updateApk(this);          //友盟检查更新
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int jump = intent.getIntExtra(JUMP_FRAGMENT, -1);
        if (jump != -1) {
            mNoScrollViewPager.setCurrentItem(jump);
        }
    }

    /**
     *
     */
    private void initView() {
        if (!getIntent().getBooleanExtra(HINT_KEY, false)) {
            messageNotice.setVisibility(View.GONE);
        } else {
            messageNotice.setVisibility(View.VISIBLE);
        }
        mMainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), mNoScrollViewPager);
        mShopHomeFragemtn = ShopHomeFragemtn.newInstance();
        mMainFragmentPagerAdapter
                .addTab(iShopping, mShopHomeFragemtn)
                .addTab(ibHome, MainFragment.newInstance())
                .addTab(ibMy, MyselfFragment.newInstance());
        mNoScrollViewPager.setAdapter(mMainFragmentPagerAdapter);
        mNoScrollViewPager.setCurrentItem(home);
        mNoScrollViewPager.setPageTransformer(true, new DepthPageTransformer());
    }


    @OnClick({R.id.ibHome, R.id.iShopping, R.id.ibMy})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ibHome:
                mNoScrollViewPager.setCurrentItem(home);
                break;
            case R.id.iShopping:
                mNoScrollViewPager.setCurrentItem(shopping);
                break;
            case R.id.ibMy:
                mNoScrollViewPager.setCurrentItem(my);
                break;
        }
    }

    @Override
    public void onSuccess(User user) {
        JPushUtil.registerJpush(user);
    }

    @Override
    public void onError() {
        JPushUtil.registerJpush(null);
    }

    /*********************************************************************************************************************/

    /**
     * 主界面的adapter
     */
    public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

        private ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();

        public MainFragmentPagerAdapter(FragmentManager fm, ViewPager mViewPager) {
            super(fm);
            mViewPager.setOffscreenPageLimit(3);                //多缓存几页，防止销毁
            mViewPager.addOnPageChangeListener(this);
        }

        @Override
        public Fragment getItem(int position) {
            return mTabs.get(position).mFragment;
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        public MainFragmentPagerAdapter addTab(View view, Fragment mFragment) {
            ViewPageInfo info = new ViewPageInfo(view, mFragment, ViewPageInfo.ICON);
            mTabs.add(info);
            return this;
        }


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position != currentTabIndex) {
                changSelected(position, true);
                changSelected(currentTabIndex, false);
                currentTabIndex = position;
            }
        }
        private void changSelected(int position, boolean isSelected) {
            switch (position) {
                case home:
                    ibHome.setSelected(isSelected);
                    break;
                case shopping:
                    if( isSelected ){
                        iShoppingTv.setTextColor(ResourcesUtil.getColor(MainActivity.this, R.color.themeTextColor));
                    } else {
                        iShoppingTv.setTextColor(ResourcesUtil.getColor(MainActivity.this, R.color.themeGray));
                    }
                    iShopping.setSelected(isSelected);
                    break;
                case my:
                    if( isSelected ){
                        ibMyTv.setTextColor(ResourcesUtil.getColor(MainActivity.this, R.color.themeTextColor));
                    } else {
                        ibMyTv.setTextColor(ResourcesUtil.getColor(MainActivity.this, R.color.themeGray));
                    }
                    ibMy.setSelected(isSelected);
                    break;
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
