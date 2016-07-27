/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：ImageLoaderManager.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/12/13:22.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tp.venus.R;
import com.tp.venus.util.qiniu.QiNiuUtil;

/**<p>Class：com.tp.venus.util.ImageLoaderManager</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/12/13:22
 * @version 1.0.0
 */

public class  GlideManager {

    private RequestManager mRequestManager;
    private Context mContext;


    /************************************ 构造器  start ***************************************/
    private GlideManager(Context mContext){
        mRequestManager = Glide.with(mContext);
        this.mContext = mContext;
    }

    private GlideManager(Activity activity){
        mRequestManager = Glide.with(activity);
        mContext = activity;
    }

    private GlideManager(FragmentActivity activity){
        mRequestManager = Glide.with(activity);
        mContext = activity;
    }

    private GlideManager(Fragment fragment){
        mRequestManager = Glide.with(fragment);
        mContext = fragment.getContext();
    }
    /************************************ 构造器  end ***************************************/


    public static GlideManager with(Context context) {
        return new GlideManager(context);
    }

    public static GlideManager with(Activity activity) {
        return new GlideManager(activity);
    }

    public static GlideManager with(FragmentActivity activity) {
        return new GlideManager(activity);
    }
    public static GlideManager with(Fragment fragment) {
        return new GlideManager(fragment);
    }


    /**context
     * 加载网络图片(默认用户)
     * @param mImageView
     * @param url
     */
    public void loadImage4user(ImageView mImageView, String url){
        load(mImageView, url, R.drawable.default_user_icon);
    }

    /**
     * 加载网络图片(默认其他)
     * @param mImageView
     * @param url
     */
    public void loadImage4other(ImageView mImageView, String url){
        load(mImageView, url, R.drawable.default_placeholder);
    }

    public void loadLocalImage(){
        //mRequestManager.load
    }

    /**
     * 加载图片(不充满)
     * @param mImageView
     * @param url
     */
    public void loadImageCus(ImageView mImageView, String url ){
        mRequestManager.load(url)
                .placeholder( R.drawable.default_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .fitCenter()
                .error(R.drawable.default_placeholder)
                .into( mImageView );
    }

    /**
     * 销毁
     */
    public void onDestroy(){
        mRequestManager.onStop();
        mRequestManager.onDestroy();
        mRequestManager = null;
    }


    public DrawableRequestBuilder loadImage(String url){
        return mRequestManager.load(url)
                .placeholder( R.drawable.default_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .fitCenter()
                .error(R.drawable.default_placeholder);
    }

    /**
     * 加载视频中的某一针图片
     * @param mImageView
     * @param video
     */
    public void loadVideo4other(ImageView mImageView, String video){
        String url = QiNiuUtil.getVideoImage(video);
        load(mImageView, url, R.drawable.default_placeholder);
    }


    /**
     * 加载
     * @param mImageView
     * @param url
     * @param drawable
     */
    private void load(ImageView mImageView, final String url, @DrawableRes int drawable){

        DrawableRequestBuilder mDrawableRequestBuilder = mRequestManager
                .load(url)
                .placeholder(drawable)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .error(R.drawable.default_placeholder);
       /* if( mImageView instanceof CircleImageView){                 //头像的组件需要特殊处理
            final CircleImageView mCircleImageView = (CircleImageView) mImageView;
            ViewGroup.LayoutParams lp = mImageView.getLayoutParams();
            SimpleTarget mSimpleTarget = new SimpleTarget<Bitmap>(lp.width, lp.height){
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    mCircleImageView.setImageBitmap(resource);
                }
            };
            mDrawableRequestBuilder.into(mSimpleTarget);
        } else {
            mDrawableRequestBuilder.into( mImageView );
        }
*/
        mDrawableRequestBuilder.into( mImageView );
    }


   /* public interface MyDataModel {
        String buildUrl(int width, int height);
    }*/
   /* public class MyUrlLoader extends BaseGlideUrlLoader<MyDataModel> {
        public MyUrlLoader(Context context) {
            super(context);
        }

        @Override
        protected String getUrl(MyDataModel model, int width, int height) {
            // Construct the url for the correct size here.
            return model.buildUrl(width, height);
        }
    }*/


   /* class MyRequestListener implements RequestListener {

        @Override
        public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
            e.printStackTrace();
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boo lean isFirstResource) {
            return false;
        }
    }*/


}
