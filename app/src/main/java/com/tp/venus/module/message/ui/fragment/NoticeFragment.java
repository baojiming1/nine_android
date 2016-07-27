/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.ui.fragment</p>
 * <p>File：NoticeFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/17/9:57.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.common.bean.Notice;
import com.tp.venus.module.common.ui.WebViewActivity;
import com.tp.venus.module.message.bean.NoticeMessage;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.TimeUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**<p>Class：com.tp.venus.module.message.ui.fragment.NoticeFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/17/9:57
 * @version 1.0.0
 */

public class NoticeFragment extends BaseSwipRefreshFragment<NoticeMessage> {


    public static NoticeFragment newInstance(){
        return new NoticeFragment();
    }

    @Override
    public Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url(Url.MESSAGE_SEARCH + Status.Message.NOTICE).post(bodyBuilder.build()).build();
    }

    @Override
    public void convertLayout(CommonViewHolder mCommonViewHolder, NoticeMessage item, int position) {
        final Notice mNotice = item.getNotice();
        mCommonViewHolder.findViewById(R.id.message_content_icon).setVisibility(View.GONE);
        CircleImageView mCircleImageView = mCommonViewHolder.findViewById(R.id.message_comment_icon);
        if( StringUtil.isEmpty(mNotice.getIcon())){
            mCircleImageView.setBackgroundResource(R.drawable.default_placeholder);
        } else {
            GlideManager.with(this).loadImage4other(mCircleImageView, mNotice.getIcon());
        }
        TextView nickname =  mCommonViewHolder.findViewById(R.id.nickname);
        nickname.setText(mNotice.getTitle() + "");
        TextView time = mCommonViewHolder.findViewById(R.id.time);
        time.setText(TimeUtils.getFriendlyTime(item.getCreateTime()));
        TextView message = mCommonViewHolder.findViewById(R.id.message);
        message.setText(mNotice.getMessage() + "");
        if( mNotice.getShowType() == Status.WEB && StringUtil.isNotEmpty(mNotice.getUrl())){
            RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                    Intent mIntent = getIntentBuilder(WebViewActivity.class).putString(WebViewActivity.URL_KEY, mNotice.getUrl()).build();
                    startActivity(mIntent);
                }
            });
        }
    }

    @Override
    public int getItemLayout() {
        return R.layout.message_notice_list_item;
    }


    @Override
    protected int getTokenStatus() {
        return Status.TokenStatus.MUST;
    }


}
