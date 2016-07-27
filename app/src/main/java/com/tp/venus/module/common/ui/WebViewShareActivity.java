/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.common.ui</p>
 * <p>File：WebViewShareActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/25/10:25.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.util.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**<p>Class：com.tp.venus.module.common.ui.WebViewShareActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/25/10:25
 * @version 1.0.0
 */

public class WebViewShareActivity extends WebViewActivity  {

    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
            {
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                    SHARE_MEDIA.SINA
            };
    protected ShareAction mShareAction;
    protected boolean isFinish = false;
    protected UMImage umImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = mToolbarBuilder.getRightTextView();
        textView.setBackgroundResource(R.drawable.more_menu);

        umImage = new UMImage(this, BitmapFactory.decodeResource(getResources(), R.drawable.default_placeholder));
        mShareAction = new ShareAction(WebViewShareActivity.this)
                .setDisplayList( displaylist )
                .setListenerList(umShareListener);
        RxViewListener.clicks(textView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                if( isFinish ){
                    mShareAction
                            .withTitle(title)
                            .withText( title)
                            .withTargetUrl(currentUrl)
                            .withMedia( umImage )
                            .open();

                }
            }
        });
    }

    @Override
    protected void onViewFinished() {
        super.onViewFinished();
        isFinish = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get( this ).onActivityResult( requestCode, resultCode, data);
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtil.getInstance().show("分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.getInstance().show("分享失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.getInstance().show("分享取消");
        }
    };

}
