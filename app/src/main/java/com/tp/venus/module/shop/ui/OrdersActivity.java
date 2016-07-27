/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：OrdersActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/6/10:04.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.base.activity.BaseViewPagerActivity;
import com.tp.venus.base.adapter.ViewPagerFragmentStatePagerAdapter;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.ui.fragment.MyOrderFragment;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StatusBarCompat;

import java.util.List;

import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.OrdersActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/4/6/10:04
 */

public class OrdersActivity extends BaseViewPagerActivity implements AppBarLayout.OnOffsetChangedListener {


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;

    private MyOrderFragment all;
    private MyOrderFragment wait_pay;
    private MyOrderFragment wait_send_goods;
    private MyOrderFragment ongoing;
    private MyOrderFragment finish;

    @Override
    protected void setColorPrimary() {
        StatusBarCompat.setColor(this,  ResourcesUtil.getColor(this, R.color.white), 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getIntExtra(Status.TYPE_FIELD, Status.Order.ALL);
        setCurrentItem(type);
    }

    @Override
    public void onSetupTabAdapter(ViewPagerFragmentStatePagerAdapter mViewPagerFragmentStatePagerAdapter) {
        all = MyOrderFragment.newInstance(Status.Order.ALL);
        wait_pay = MyOrderFragment.newInstance(Status.Order.WAIT_PAY);
        wait_send_goods = MyOrderFragment.newInstance(Status.Order.WAIT_SEND_GOODS);
        ongoing = MyOrderFragment.newInstance(Status.Order.ONGOING);
        finish = MyOrderFragment.newInstance(Status.Order.FINISH);
        mViewPagerFragmentStatePagerAdapter.addTab("全部", all);
        mViewPagerFragmentStatePagerAdapter.addTab("待付款", wait_pay);
        mViewPagerFragmentStatePagerAdapter.addTab("待发货", wait_send_goods);
        mViewPagerFragmentStatePagerAdapter.addTab("进行中", ongoing);
        mViewPagerFragmentStatePagerAdapter.addTab("已完成", finish);
    }

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.common_coordinatorlayout_tablayout_viewpager);
    }

    @Override
    public void initView() {
        getToolbarBuilder(mToolbar).setTitle(R.string.my_orders)
                .setHomeAsUpIndicator(R.drawable.grey_back)
                .build().getMidTextView().setTextColor(ResourcesUtil.getColor(this, R.color.black));
        mToolbar.setBackgroundResource(R.color.white);
        mTabLayout.setBackgroundResource(R.color.white);
        mTabLayout.setSelectedTabIndicatorColor(ResourcesUtil.getColor(this, R.color.red));
        mTabLayout.setTabTextColors(ResourcesUtil.getColor(this, R.color.dark_grey), ResourcesUtil.getColor(this, R.color.red));
    }

    private void setCurrentItem(int type){
        int index = 0;
        switch (type){
            case Status.Order.WAIT_PAY:
                index = 1;
                break;
            case Status.Order.WAIT_SEND_GOODS:
                index = 2;
                break;
            case Status.Order.ONGOING:
                index = 3;
                break;
            case Status.Order.FINISH:
                index = 4;
                break;
            case Status.Order.ALL:
            default:
                index = 0;
                break;
        }
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        SwipeRefreshLayout mSwipeRefreshLayout = initSwipeRefreshLayout();
        if( mSwipeRefreshLayout != null){
            if (i == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        }
    }

    private SwipeRefreshLayout initSwipeRefreshLayout(){
        List<Fragment> list = getSupportFragmentManager().getFragments();
        int current = mViewPager.getCurrentItem();
        return findSwipeRefreshLayout(current, list);
    }


    private SwipeRefreshLayout findSwipeRefreshLayout(int current, List<Fragment> list){
        Fragment fragment = null;
       switch (current){
           case 0 :
               fragment = all;
               break;
           case 1 :
               fragment = wait_pay;
               break;
           case 2 :
               fragment = wait_send_goods;
               break;
           case 3 :
               fragment = ongoing;
               break;
           case 4:
               fragment = finish;
               break;
           default:
               return null;
       }
        int index = list.indexOf(fragment);
        if( index != -1){
            return (SwipeRefreshLayout) list.get(index).getView().findViewById(R.id.mSwipeRefreshLayout);
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAppBarLayout.removeOnOffsetChangedListener(this);
    }



   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionUtil.CALL_PHONE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                PermissionUtil.getInstance().callPhone(this,  R.string.customer_phone);
            } else {
                ToastUtil.getInstance().show("获取授权失败");
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }*/
}
