/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.ui.fragment</p>
 * <p>File：PlayHorizontalFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/10:15.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.ui.fragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.module.qinjia.ui.PlayActivity;
import com.tp.venus.module.qinjia.ui.view.LiveListener;
import com.tp.venus.module.qinjia.view.ChatRoom;
import com.tp.venus.module.qinjia.view.GLSurfaceViewContainer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * <p>Class：com.tp.venus.module.qinjia.ui.fragment.PlayHorizontalFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/6/30/10:15
 */

public class PlayHorizontalFragment extends BaseFragment implements LiveListener {

    @InjectView(R.id.surfaceViewContainer)
    RelativeLayout surfaceViewContainer;

    @InjectView(R.id.live_back)
    ImageView liveBack;
    @InjectView(R.id.barrage)
    ImageView barrage;
    @InjectView(R.id.expand)
    ImageView expand;
    /*    @InjectView(R.id.chatRoomContainer)
        RelativeLayout chatRoomContainer;*/
    @InjectView(R.id.loading)
    ProgressBar loading;
    @InjectView(R.id.right)
    LinearLayout right;
    @InjectView(R.id.live_lock)
    ImageView liveLock;
    @InjectView(R.id.video_views)
    RelativeLayout videoViews;
    private GLSurfaceViewContainer glSurfaceViewContainer;
    public ChatRoom chatRoom;
    private View rootView;
    TextView loadingText;
    LinearLayout loadingView;
    PlayActivity mPlayActivity;
    public DanmakuView mDanmakuView;

    public boolean isLock;
    public boolean isOpenDanmu = true;


    public static PlayHorizontalFragment newInstance() {
        return new PlayHorizontalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isLock = false;
        View view = inflater.inflate(R.layout.activity_qinjia_play_horizontal, container, false);
        ButterKnife.inject(this, view);
        this.rootView = view;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        loadingText = (TextView) rootView.findViewById(R.id.loadingText);
        loadingView = (LinearLayout) rootView.findViewById(R.id.loadingView);
        surfaceViewContainer.removeAllViews();
        surfaceViewContainer.addView(glSurfaceViewContainer);
        surfaceViewContainer.addView(mDanmakuView);
      /*  chatRoomContainer.addView(chatRoom);
        chatRoom.getChatBubble().duration = 2000;*/
        RxViewListener.clicks(liveBack, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                finishActivity();
            }
        });
        RxViewListener.clicks(expand, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mPlayActivity.changeFragment(mPlayActivity.orientation_vertical);
            }
        });
        RxViewListener.clicks(liveLock, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                if (!isLock) {
                    isLock = true;
                    mPlayActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);//强制为横屏
                    liveLock.setImageResource(R.drawable.live_lock_close);
                } else {
                    isLock = false;
                    //mPlayActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);//默认选择
                    mPlayActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
                    liveLock.setImageResource(R.drawable.live_lock_open);
                }
            }
        });
        RxViewListener.clicks(barrage, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                if (isOpenDanmu) {              //不开启
                    isOpenDanmu = false;
                    mDanmakuView.hide();
                    barrage.setImageResource(R.drawable.barrage_close);
                } else {                        //开启
                    isOpenDanmu = true;
                    mDanmakuView.show();
                    barrage.setImageResource(R.drawable.barrage_open);
                }
            }
        });
        loadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrHideVideoLayout();
                mPlayActivity.resetTask();
            }
        });
        if (mPlayActivity.isConnect) {
            mPlayActivity.player.onResume();
        }
        if (mPlayActivity.isPlay) {
            hideLoading();
        } else {
            showLoading();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        loadingText = (TextView) rootView.findViewById(R.id.loadingText);
        loadingView = (LinearLayout) rootView.findViewById(R.id.loadingView);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPlayActivity = ((PlayActivity) getActivity());
    }

    public void initView(GLSurfaceViewContainer glSurfaceViewContainer, ChatRoom chatRoom, DanmakuView mDanmakuView) {
        this.glSurfaceViewContainer = glSurfaceViewContainer;
        this.chatRoom = chatRoom;
        this.mDanmakuView = mDanmakuView;
    }


    @Override
    public void onDestroyView() {
        //chatRoomContainer.removeAllViews();
        surfaceViewContainer.removeAllViews();
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void hideLoading() {
        if (loadingView == null) {
            return;
        }
        if (loadingView.getVisibility() == View.VISIBLE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    public void showLoading() {
        if (loadingView == null) {
            return;
        }
        if (loadingView.getVisibility() == View.GONE) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    public void setLoadingText(CharSequence text) {
        if (loadingText != null) {
            loadingText.setText(text);
        }
    }

    @Override
    public void show(CharSequence str) {
        showLoading();
        setLoadingText(str);
    }

    @Override
    public void hide(CharSequence str) {
        setLoadingText(str);
        hideLoading();
    }


    public void showOrHideVideoLayout() {
        if( videoViews.getVisibility() == View.GONE){
            videoViews.setVisibility(View.VISIBLE);
        } else {
            hideVideoLayout();
        }
    }

    @Override
    public void hideVideoLayout(){
        if(videoViews.getVisibility() == View.VISIBLE){
            videoViews.setVisibility(View.GONE);
        }
    }
}
