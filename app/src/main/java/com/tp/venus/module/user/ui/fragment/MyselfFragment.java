/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui.fragment</p>
 * <p>File：MyselfFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/9/10:10.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.adapter.MenuCommonAdapter;
import com.tp.venus.base.builder.MenuBuilder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.model.Menu;
import com.tp.venus.module.home.receiver.MessageReceiver;
import com.tp.venus.module.message.bean.BadgeCount;
import com.tp.venus.module.message.presenter.IBadgePresenter;
import com.tp.venus.module.message.presenter.impl.BadgePresenter;
import com.tp.venus.module.message.ui.NoticeActivity;
import com.tp.venus.module.message.ui.view.IBadgeView;
import com.tp.venus.module.shop.ui.AddressManagerActivity;
import com.tp.venus.module.shop.ui.OrdersActivity;
import com.tp.venus.module.shop.ui.ShopCartActivity;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.presenter.IUserPresenter;
import com.tp.venus.module.user.presenter.impl.UserPresenter;
import com.tp.venus.module.user.ui.FavoriteActivity;
import com.tp.venus.module.user.ui.SettingActivity;
import com.tp.venus.module.user.ui.UserInfoActivity;
import com.tp.venus.module.user.ui.view.IUserView;
import com.tp.venus.receiver.JPushReceiver;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StatusBarCompat;
import com.tp.venus.util.StringUtil;

import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <p>Class：com.tp.venus.module.user.ui.fragment.MyselfFragment</p>
 * <p>Description：</p>
 * <pre>
 *     我
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/9/10:10
 */

public class MyselfFragment extends BaseFragment implements CommonAdapter.OnRecyclerViewItemClickListener<Menu>, IUserView, IBadgeView, View.OnClickListener {


    @InjectView(R.id.payment)
    LinearLayout payment;
    @InjectView(R.id.deliver_goods)
    LinearLayout deliverGoods;
    @InjectView(R.id.out_goods)
    LinearLayout outGoods;
    @InjectView(R.id.finish)
    LinearLayout finish;


    public static MyselfFragment newInstance() {
        return new MyselfFragment();
    }

    private final int address_manager = 1, shop_cart = 2, my_orders = 3, my_message = 4, my_favorite = 5;

    @InjectView(R.id.userInfoLayout)
    RelativeLayout userInfoLayout;
    @InjectView(R.id.mIconBackground)
    ImageView mIconBackground;
    @InjectView(R.id.setting)
    ImageView setting;
    @InjectView(R.id.part_two)
    RecyclerView part_two;
    @InjectView(R.id.part_one)
    RecyclerView part_one;
    @InjectView(R.id.mCircleImageView)
    CircleImageView mCircleImageView;
    @InjectView(R.id.nickname)
    TextView nickname;
    @InjectView(R.id.address)
    TextView address;

    private IUserPresenter mIUserPresenter;
    private PartTowMenuCommonAdapter mPartTowMenuCommonAdapter;
    private IBadgePresenter mIBadgePresenter;

    private PartOneMenuCommonAdapter mPartOneMenuCommonAdapter;

    private boolean isvisible = false;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {                  //可见时
            mIUserPresenter.showUserInfo();
          /*  mIBadgePresenter.messageCount();*/
            isvisible = true;
        }                               //不可见时
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.home_fragment_myself, null);
        View view = inflater.inflate(R.layout.home_fragment_myself, container, false);
        ButterKnife.inject(this, view);
        if (mIUserPresenter == null) {
            mIUserPresenter = getPresenter(new UserPresenter(this));
        }
        if (mIBadgePresenter == null) {
            mIBadgePresenter = getPresenter(new BadgePresenter(this));
        }
        if (isvisible && MessageReceiver.isShowHint) {
            JPushReceiver.noticeMain(mContext, new Bundle(), false);
            /*mIBadgePresenter.messageCount();*/
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        if (mPartTowMenuCommonAdapter == null) {
            mPartTowMenuCommonAdapter = new PartTowMenuCommonAdapter(this.getContext());
            RecyclerViewBuilder.create(part_two).setAdapter(mPartTowMenuCommonAdapter).addOnRecyclerViewItemClickListener(this).build()
                    .setAutoMeasureEnabled(true);
        }
        if (mPartOneMenuCommonAdapter == null) {
            mPartOneMenuCommonAdapter = new PartOneMenuCommonAdapter(this.getContext());
            RecyclerViewBuilder.create(part_one).setAdapter(mPartOneMenuCommonAdapter).addOnRecyclerViewItemClickListener(this).build()
                    .setAutoMeasureEnabled(true);
        }
        RxViewListener.clicks(setting, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                startActivity(SettingActivity.class);
            }
        });
        RxViewListener.clicks(userInfoLayout, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                startActivity(UserInfoActivity.class);
            }
        });
        payment.setOnClickListener(this);
        deliverGoods.setOnClickListener(this);
        outGoods.setOnClickListener(this);
        finish.setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void onItemClick(View view, Menu item, int position) {
        switch (item.id) {
            case address_manager:
                startActivity(AddressManagerActivity.class);
                break;
            case shop_cart:
                startActivity(ShopCartActivity.class);
                break;
            case my_orders:
                startActivity(OrdersActivity.class);
                break;
            case my_favorite:                  //我的收藏夹
                startActivity(FavoriteActivity.class);
                break;
            case my_message:                   //信息中心
                startActivity(NoticeActivity.class);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.payment:
                intent = getIntentBuilder(OrdersActivity.class).putInt(Status.TYPE_FIELD, Status.Order.WAIT_PAY).build();
                break;
            case R.id.deliver_goods :
                intent = getIntentBuilder(OrdersActivity.class).putInt(Status.TYPE_FIELD, Status.Order.WAIT_SEND_GOODS).build();
                break;
            case R.id.out_goods:
                intent = getIntentBuilder(OrdersActivity.class).putInt(Status.TYPE_FIELD, Status.Order.ONGOING).build();
                break;
            case R.id.finish:
                intent = getIntentBuilder(OrdersActivity.class).putInt(Status.TYPE_FIELD, Status.Order.FINISH).build();
                break;
            default:
                return ;
        }
        startActivity(intent);
    }


    @Override
    public void showUserInfo(User mUser) {
        nickname.setText(mUser.getNickname() + "");
        String addr = mUser.getAddress();
        if (StringUtil.isNotEmpty(addr)) {
            address.setText(addr);
        }
        GlideManager mGlideManager = GlideManager.with(this);
        mGlideManager.loadImage4user(mCircleImageView, mUser.getIcon());
        mGlideManager.loadImage4user(mIconBackground, mUser.getIcon());
    }

    @Override
    public void attentionSuccess(boolean isAttention) {
        // 没有
    }

    @Override
    public void showBadge(BadgeCount mBadgeCount) {
        int badge = mBadgeCount.getNoticeCount();
        if (badge > 0) {
            Menu menu = mPartTowMenuCommonAdapter.getItem(1);
            menu.rightBackground = R.drawable.message_bg_shape_sel;
            menu.rightTitle = badge + "";
        } else {
            Menu menu = mPartTowMenuCommonAdapter.getItem(1);
            menu.rightTitle = "";
        }
        mPartTowMenuCommonAdapter.notifyItemChanged(1);
        JPushReceiver.noticeMain(mContext, new Bundle(), false);
    }




    /**
     * 二菜单适配器
     */
    class PartTowMenuCommonAdapter extends MenuCommonAdapter<Menu> {
        public PartTowMenuCommonAdapter(Context mContext) {
            super(mContext);
        }

        @Override
        public Collection<Menu> getMenu(MenuBuilder mMenuBuilder) {
            return mMenuBuilder
                    .addMenu(my_favorite, R.string.my_favorite, R.drawable.favorite)
                    .addMenu(my_message, R.string.system, R.drawable.notice)
                    .showLiner().build();
        }

        @Override
        public void after(CommonViewHolder mCommonViewHolder, Menu item, int position) {
        }
    }

    /**
     * 第一部分
     */
    class PartOneMenuCommonAdapter extends MenuCommonAdapter<Menu> {
        public PartOneMenuCommonAdapter(Context mContext) {
            super(mContext);
        }

        @Override
        public Collection<Menu> getMenu(MenuBuilder mMenuBuilder) {
            return mMenuBuilder.addMenu(address_manager, R.string.address_manager, R.drawable.address)
                    .addMenu(shop_cart, R.string.shopCart, R.drawable.my_cart)
                    .addMenu(my_orders, R.string.my_orders, R.drawable.myorders)
                    .showLiner().build();
        }

        @Override
        public void after(CommonViewHolder mCommonViewHolder, Menu item, int position) {
        }
    }


}
