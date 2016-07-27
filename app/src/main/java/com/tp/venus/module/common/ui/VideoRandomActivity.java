/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.ui</p>
 * <p>File：VideoRandomActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/7/11:34.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.module.common.bean.VideoInfo;
import com.tp.venus.module.common.presenter.IVideoRandomPersenter;
import com.tp.venus.module.common.presenter.impl.VideoRandomPersenter;
import com.tp.venus.module.common.ui.view.IVideoRandomView;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.video.MyMediaController;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.CenterLayout;
import io.vov.vitamio.widget.VideoView;

/**
 * <p>Class：com.tp.venus.module.common.ui.VideoRandomActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/7/11:34
 */

public class VideoRandomActivity extends BaseActivity implements IVideoRandomView {

    VideoView mVideoView;
    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.video_frament)
    FrameLayout videoFrament;
    @InjectView(R.id.desc)
    TextView desc;
    @InjectView(R.id.probar)
    ProgressBar probar;
    CenterLayout mCenterLayout;
    @InjectView(R.id.video_layout)
    RelativeLayout videoLayout;
    @InjectView(R.id.progressBar_layout)
    RelativeLayout progressBarLayout;

    private IVideoRandomPersenter mIVideoRandomPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_video_layout);
        ButterKnife.inject(this);
        if (mIVideoRandomPersenter == null) {
            mIVideoRandomPersenter = new VideoRandomPersenter(this);
        }
        mIVideoRandomPersenter.searchRandomVideoInfo();
    }

    private void init(VideoInfo mVideoInfo) {
        getToolbarBuilder(mToolbar).setTitle(mVideoInfo.getVideoTitle()).build();
        String path = mVideoInfo.getVideoUrl();
        // String path = "http://7xnrd4.com1.z0.glb.clouddn.com/o_1a972nn5m1sufupa1ut91rkn97g.mp4";

        mCenterLayout = new CenterLayout(this);
        mVideoView = new VideoView(this);
        mVideoView.setVideoPath(path);
        mCenterLayout.addView(mVideoView);
        videoLayout.addView(mCenterLayout);

     /*   probar = new ProgressBar(this);
        //probar.setBackground(R.attr.progressBarStyleLarge);
        progressBarLayout.addView(probar);*/

        String str = "视频描述:";
        if (StringUtil.isNotEmpty(mVideoInfo.getVideoDesc())) {
            str += mVideoInfo.getVideoDesc();
        }
        desc.setText(str);
        MyMediaController mMyMediaController = new MyMediaController(this, true, videoFrament);
        mMyMediaController.setFileName("");
        mVideoView.setMediaController(mMyMediaController);
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.seekTo(0);
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:                   //开始缓冲
                        mVideoView.pause();
                        probar.setVisibility(View.VISIBLE);
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mVideoView.start();
                        probar.setVisibility(View.GONE);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onSuccess(List<VideoInfo> datas) {
        if (CollectionUtils.isNotEmpty(datas)) {
            VideoInfo mVideoInfo = datas.get(0);
            init(mVideoInfo);
        } else {
            ToastUtil.getInstance().show("九叔又偷懒了，竟然忘记了上干货");
        }
    }
}
