/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.ui.fragment</p>
 * <p>File：PlayVerticalFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/29/14:01.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.ViewPagerFragmentPagerAdapter;
import com.tp.venus.base.fragment.BaseViewPagerFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.qinjia.ui.PlayActivity;
import com.tp.venus.module.qinjia.ui.view.LiveListener;
import com.tp.venus.module.qinjia.view.GLSurfaceViewContainer;
import com.tp.venus.util.ResourcesUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * <p>Class：com.tp.venus.module.qinjia.ui.fragment.PlayVerticalFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/6/29/14:01
 */

public class PlayVerticalFragment extends BaseViewPagerFragment implements LiveListener {

    @InjectView(R.id.surfaceViewContainer)
    RelativeLayout surfaceViewContainer;
    @InjectView(R.id.live_back)
    ImageView liveBack;
    @InjectView(R.id.barrage)
    ImageView barrage;
    @InjectView(R.id.expand)
    ImageView expand;
    @InjectView(R.id.video_views)
    RelativeLayout videoViews;
    private GLSurfaceViewContainer glSurfaceViewContainer;
    private View rootView;
    public DanmakuView mDanmakuView;
    TextView loadingText;
    LinearLayout loadingView;
    PlayActivity mPlayActivity;
    private String programId;
    private boolean isOpenDanmu = true;

    public static PlayVerticalFragment newInstance(String programId) {
        PlayVerticalFragment mLiveProductFragment = new PlayVerticalFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Status.ID, programId);
        mLiveProductFragment.setArguments(bundle);
        return mLiveProductFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_qinjia_play_vertical;
    }


    @Override
    public void onSetupTabAdapter(ViewPagerFragmentPagerAdapter mViewPagerFragmentPagerAdapter) {
        mViewPagerFragmentPagerAdapter.addTab("聊天室", ChatFragment.newInstance());
        mViewPagerFragmentPagerAdapter.addTab("商品", LiveProductFragment.newInstance(programId));
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        programId = getArguments().getString(Status.ID);
        this.rootView = view;
        loadingText = (TextView) rootView.findViewById(R.id.loadingText);
        loadingView = (LinearLayout) rootView.findViewById(R.id.loadingView);
        int width = ResourcesUtil.getPoint(this.getContext()).x;
        ViewGroup.LayoutParams lp = surfaceViewContainer.getLayoutParams();
        lp.height = width;
        surfaceViewContainer.setLayoutParams(lp);
        surfaceViewContainer.removeAllViews();
        surfaceViewContainer.addView(glSurfaceViewContainer);
        surfaceViewContainer.addView(mDanmakuView);
        //回退
        RxViewListener.clicks(liveBack, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                finishActivity();
            }
        });
        RxViewListener.clicks(expand, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mPlayActivity.changeFragment(mPlayActivity.orientation_horizontal);
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

        if (mPlayActivity.isPlay) {
            hideLoading();
        } else {
            showLoading();
        }
        if (mPlayActivity.isConnect) {
            mPlayActivity.player.onResume();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPlayActivity.isConnect) {
            mPlayActivity.player.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPlayActivity.isConnect) {
            mPlayActivity.player.onStop();
            mPlayActivity.isPlay = false;
        }
        loadingText = (TextView) rootView.findViewById(R.id.loadingText);
        loadingView = (LinearLayout) rootView.findViewById(R.id.loadingView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPlayActivity = ((PlayActivity) getActivity());
        glSurfaceViewContainer = mPlayActivity.glSurfaceViewContainer;
        mDanmakuView = mPlayActivity.mDanmakuView;
    }

    public void initView(GLSurfaceViewContainer glSurfaceViewContainer, DanmakuView mDanmakuView) {
        this.surfaceViewContainer = glSurfaceViewContainer;
        this.mDanmakuView = mDanmakuView;
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
    public void onDestroyView() {
        surfaceViewContainer.removeAllViews();
        super.onDestroyView();
        ButterKnife.reset(this);
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
        if (videoViews.getVisibility() == View.GONE) {
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
