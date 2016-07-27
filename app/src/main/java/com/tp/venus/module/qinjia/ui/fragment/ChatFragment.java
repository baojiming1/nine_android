/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.ui.fragment</p>
 * <p>File：ChatFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/29/16:00.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tp.venus.R;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.module.qinjia.ui.PlayActivity;
import com.tp.venus.module.qinjia.view.ChatRoom;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.qinjia.ui.fragment.ChatFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/6/29/16:00
 */

public class ChatFragment extends BaseFragment  {

    ChatRoom chatRoom;
    @InjectView(R.id.container)
    RelativeLayout container;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qinjia_chat, null);
        ButterKnife.inject(this, view);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chatRoom.getChatBubble().duration = 100000;
        container.addView(chatRoom);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        chatRoom = ((PlayActivity) getActivity()).chatRoom;
    }

    @Override
    public void onDestroyView() {
        container.removeAllViews();
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
