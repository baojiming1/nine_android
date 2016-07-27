/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：SettingActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/13/10:03.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.adapter.MenuCommonAdapter;
import com.tp.venus.base.builder.MenuBuilder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.model.Menu;
import com.tp.venus.module.common.ui.WebViewActivity;
import com.tp.venus.module.home.ui.MainActivity;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.presenter.ISettingPresenter;
import com.tp.venus.module.user.presenter.impl.SettingPresenter;
import com.tp.venus.module.user.ui.view.ISettingView;
import com.tp.venus.util.DataCleanManager;
import com.tp.venus.util.StringUtil;
import com.tp.venus.widget.RippleView;

import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.SettingActivity</p>
 * <p>Description：</p>
 * <pre>
 *     设置页面
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/13/10:03
 */

public class SettingActivity extends BaseActivity implements ISettingView {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.top_list)
    RecyclerView mTopRecyclerView;
    @InjectView(R.id.bottom_list)
    RecyclerView mBottomRecyclerView;
    @InjectView(R.id.login_out)
    RippleView loginOut;

    private final int updatePwd = 1, clear = 2, protocol = 3,  about = 4, set_pwd = 5, bind_phone = 6;

    private ISettingPresenter mISettingPresenter;
    private MenuCommonAdapter<Menu> topMenuMenuCommonAdapter;
    private MenuCommonAdapter<Menu> bottomMenuMenuCommonAdapter;
    private MyOnRecyclerViewItemClickListener mMyOnClickListener = new MyOnRecyclerViewItemClickListener();
    private String cacheString;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_setting);
        ButterKnife.inject(this);
        if (mISettingPresenter == null) {
            mISettingPresenter = getPresenter(new SettingPresenter(this));
        }
        user = mISettingPresenter.getCurrentUser();
        initView();
    }

    private void initView() {
        getToolbarBuilder(mToolbar).setTitle(R.string.setting).build();
        cacheString = DataCleanManager.getCacheSize( getApplicationContext().getExternalCacheDir());
        topMenuMenuCommonAdapter = new TopMenuMenuCommonAdapter(this);
        RecyclerViewBuilder.create(mTopRecyclerView).setAdapter(topMenuMenuCommonAdapter).addOnRecyclerViewItemClickListener(mMyOnClickListener)
                .setAutoMeasureEnabled(true)
                .build();
        bottomMenuMenuCommonAdapter = new BottomMenuMenuCommonAdapter(this);
        RecyclerViewBuilder.create(mBottomRecyclerView).setAdapter(bottomMenuMenuCommonAdapter).addOnRecyclerViewItemClickListener(mMyOnClickListener)
                .setAutoMeasureEnabled(true)
                .build();
        RxViewListener.clicks(loginOut, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mISettingPresenter.loginOut();
            }
        });
    }

    @Override
    public void onSuccess() {
        Intent mIntent = getIntentBuilder(MainActivity.class).putInt(MainActivity.JUMP_FRAGMENT, 0).build();
        startActivity(mIntent);
    }

    @Override
    public void onError() {

    }

    /**
     * 上面的菜单
     */
    class TopMenuMenuCommonAdapter extends MenuCommonAdapter<Menu> {
        public TopMenuMenuCommonAdapter(Context mContext) {
            super(mContext);
        }
        @Override
        public Collection<Menu> getMenu(MenuBuilder mMenuBuilder) {
            return mMenuBuilder
                    .addMenu(about, R.string.about, R.drawable.our)
                    .addMenu(clear, R.string.clear_cache, R.drawable.delete, cacheString)
                    .showLiner()
                    .build();
        }
    }

    /**
     * 下面的菜单
     */
    class BottomMenuMenuCommonAdapter extends MenuCommonAdapter<Menu> {

        public TextView tPhone ;

        public BottomMenuMenuCommonAdapter(Context mContext) {
            super(mContext);
        }

        @Override
        public Collection<Menu> getMenu(MenuBuilder mMenuBuilder) {
            mMenuBuilder = mMenuBuilder.addMenu(protocol, R.string.protocol, R.drawable.protocol)
                    .showLiner();
            if( StringUtil.isNotEmpty(user.getMobile())){
                Menu set = Menu.Builder.create().addId(set_pwd).addIcon(R.drawable.modify_password).addTitle( R.string.set_pwd ).build();
                mMenuBuilder.addMenu(set);
            }
            Menu bindphone = Menu.Builder.create().addId(bind_phone).addIcon(R.drawable.bind_mobile).addTitle( R.string.bind_phone ).addRightView(R.layout.menu_right_textview).build();
            return mMenuBuilder
                    .addMenu(bindphone)
                    .build();
        }

        @Override
        public void after(CommonViewHolder mCommonViewHolder, Menu item, int position) {
            switch (item.id){
                case set_pwd:
                    break;
                case bind_phone:
                    if( tPhone == null){
                        ViewStub v = mCommonViewHolder.findViewById(R.id.rightView);
                        v.setLayoutResource(item.rightView);
                        tPhone = (TextView) v.inflate();
                    }
                    tPhone.setText(user.getMobile() + "");
                    break;
            }
        }
    }


    /**
     * 菜单点击事件
     */
    class MyOnRecyclerViewItemClickListener implements CommonAdapter.OnRecyclerViewItemClickListener<Menu> {
        @Override
        public void onItemClick(View view, Menu item, int position) {
            switch (item.id) {
                case updatePwd:
                    startActivity(UpdatePwdActivity.class);
                    break;
                case clear:
                    new AlertDialog.Builder(SettingActivity.this).setTitle("您确定要清除缓存吗?").setPositiveButton("确定", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DataCleanManager.cleanInternalCache(getApplicationContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case protocol:
                    Intent protocol = getIntentBuilder(WebViewActivity.class).putString(WebViewActivity.URL_KEY, Url.PROTOCOL)
                            .putInt(WebViewActivity.TITLE_KEY, R.string.protocol)
                            .build();
                    startActivity(protocol);
                    break;
                case about:
                    startActivity(AboutActivity.class);
                    break;
                case set_pwd :
                    Intent mIntent = getIntentBuilder(ForgetPasswdActivity.class).putInt(Status.TYPE_FIELD, 1)
                            .putString(ForgetPasswdActivity.MOBILE, user.getMobile()).build();
                    startActivity(mIntent);
                    break;
                case bind_phone:
                    String phone = user.getMobile();
                    if(StringUtil.isNotEmpty(phone)){
                        new AlertDialog.Builder(SettingActivity.this).setTitle("继续绑定将会自动解绑当前手机号码，是否继续?").setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(BindPhoneActivity.class);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    } else {
                        startActivity(BindPhoneActivity.class);
                    }
                    break;
            }
        }
    }

}
