/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui.fragment</p>
 * <p>File：MoreDialogFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/4/11:24.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.fragment.BaseDialogFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.content.ui.CommentActivity;

/**<p>Class：com.tp.venus.module.content.ui.fragment.MoreDialogFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/4/11:24
 * @version 1.0.0
 */

public class MoreDialogFragment extends BaseDialogFragment {

    public static MoreDialogFragment newInstance(String contentId, String toUserId){
        MoreDialogFragment mMoreDialogFragment = new MoreDialogFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(Status.Content.ID, contentId);
        mBundle.putString(Status.User.TOUSERID, toUserId);
        mMoreDialogFragment.setArguments(mBundle);
        return mMoreDialogFragment;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String contentId = getArguments().getString(Status.Content.ID);
        final String toUserId = getArguments().getString(Status.User.TOUSERID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.home_more_dialog, null);
        final Dialog mDialog = builder.setView(view).create();
        TextView comment = (TextView) view.findViewById(R.id.comment);
        RxViewListener.clicks(comment, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = IntentBuilder.create(getContext(), CommentActivity.class).putString(Status.Content.ID, contentId)
                        .putString(Status.User.TOUSERID, toUserId).build();
                getContext().startActivity(mIntent);
                mDialog.dismiss();
            }
        });
        TextView cannel = (TextView) view.findViewById(R.id.cannel);
        RxViewListener.clicks(cannel, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mDialog.dismiss();
            }
        });
        return mDialog;
    }
}
