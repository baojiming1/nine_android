/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.nine.module.home.ui</p>
 * <p>File：SplashActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/9/16:20.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.home.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.tp.venus.R;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.SharePreferencesUtils;

/**
 * <p>Class：com.tp.nine.module.home.ui.SplashActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/9/16:20
 */

public class SplashActivity extends Activity implements View.OnClickListener {

    public static final String SPLAH_BOOLEAN = "SPLAH_BOOLEAN";

    final int[] SPLASH = {R.drawable.splash_1, R.drawable.splash_2, R.drawable.splash_3, R.drawable.splash_4};


    private RollPagerView mRollPagerView;
    private Button skip;
    private SplashStaticPagerAdapter mSplashStaticPagerAdapter;
    private ColorPointHintView mColorPointHintView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean flag = SharePreferencesUtils.getBoolean(this, SPLAH_BOOLEAN, false);
        if( !flag ){
            initGuide();
        } else {
            initImage();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashStaticPagerAdapter = null;
        mColorPointHintView = null;
    }

    private void initGuide(){
        setContentView(R.layout.activity_splash);
        mRollPagerView = (RollPagerView) findViewById(R.id.mRollPagerView);
        skip = (Button) findViewById(R.id.skip);
        mColorPointHintView = new ColorPointHintView(this, ResourcesUtil.getColor(this, R.color.themeTextColor), ResourcesUtil.getColor(this, R.color.themeColor));
        mRollPagerView.setHintView(mColorPointHintView);
        mRollPagerView.setPlayDelay(0);
        mRollPagerView.setHintPadding(0,0,0,30);
        mSplashStaticPagerAdapter = new SplashStaticPagerAdapter(SPLASH, this);
        mRollPagerView.setAdapter(mSplashStaticPagerAdapter);
        skip.setOnClickListener(this);
        SharePreferencesUtils.putBoolean(this, SPLAH_BOOLEAN, true);
    }


    private void initImage() {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackgroundResource(R.drawable.splash);
        setContentView(imageView);
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                goMainActivity();
            }
        }, 2000);
    }

    @Override
    public void onClick(View v) {
        goMainActivity();
    }

    private void goMainActivity(){
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

 /*   private void initVideo(){
*//*  RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);
        videoView.setMediaController(null);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        videoView.start();*//*
    }*/


    class SplashStaticPagerAdapter extends StaticPagerAdapter  {

        private Context mContext;
        private int[] images;

        public SplashStaticPagerAdapter(int[] images, Context mContext) {
            this.mContext = mContext;
            this.images = images;
        }


        @Override
        public View getView(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.main_splash_image_item, container, false);
            ImageView mImageView = (ImageView) view.findViewById(R.id.mImageView);
           // Glide.with(SplashActivity.this).load(images[position]).into(mImageView);
            mImageView.setBackgroundResource(images[position]);
            if( position == (images.length -1)){            //最后一个
                View next = view.findViewById(R.id.next);
                next.setVisibility(View.VISIBLE);
                next.setOnClickListener(SplashActivity.this);
            }
            return view;
        }

        @Override
        public int getCount() {
            return images.length;
        }
    }
}
