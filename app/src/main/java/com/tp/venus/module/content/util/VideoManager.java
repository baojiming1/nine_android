/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.util</p>
 * <p>File：VitamioVideoManager.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/1/11:34.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.util;

/**<p>Class：com.tp.venus.module.content.util.VitamioVideoManager</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/1/11:34
 * @version 1.0.0
 */

public class VideoManager  {

  /*  private Context mContext;
    private VideoPlayerView mVideoPlayerView;
    private String video;
    private ImageView videoImage;
    private GlideManager mGlideManager;
    private View videoLayout;
    private ImageView iconPlay;


    public VideoManager(Context mContext,View videoLayout, String video){
        this.mContext = mContext;
        this.video = video;
        this.videoLayout = videoLayout;
        this.mGlideManager = GlideManager.with(mContext);
        init();
    }

    *//**
     * 初始化
     *//*
    public void init() {
        videoImage = ViewHolder.findById(videoLayout, R.id.videoImage);
        mVideoPlayerView = ViewHolder.findById(videoLayout, R.id.mVideoPlayerView);
        iconPlay = ViewHolder.findById(videoLayout, R.id.icon_play);
        mGlideManager.loadVideo4other(videoImage, video);
        mVideoPlayerView.addMediaPlayerListener(new SimpleMainThreadMediaPlayerListener(){
            @Override
            public void onVideoPreparedMainThread() {
                //开始播放时触发
                super.onVideoPreparedMainThread();
                videoImage.setVisibility(View.GONE);
                iconPlay.setVisibility(View.GONE);
            }

            @Override
            public void onVideoStoppedMainThread() {
                //结束播放时触发
                super.onVideoStoppedMainThread();
                videoImage.setVisibility(View.VISIBLE);
                iconPlay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onVideoCompletionMainThread() {
                super.onVideoCompletionMainThread();
            }
        });
        RxViewListener.clicks(iconPlay, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                MyVideoPlayManager.start(mVideoPlayerView, video);
            }
        });
        RxViewListener.clicks(mVideoPlayerView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                if( MyVideoPlayManager.isPlaying() ){
                    MyVideoPlayManager.stop();
                }
            }
        });
    }

*/

}
