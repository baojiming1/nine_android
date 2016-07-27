/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.widget.loading</p>
 * <p>File：LoadDialogFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/21/18:41.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.widget.loading;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.tp.venus.R;
import com.tp.venus.base.fragment.BaseDialogFragment;

/**<p>Class：com.tp.venus.widget.loading.LoadDialogFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/21/18:41
 * @version 1.0.0
 */

public class LoadDialogFragment extends BaseDialogFragment {



    public static LoadDialogFragment newInstance(){
        LoadDialogFragment mLoadDialogFragment = new LoadDialogFragment();
        return mLoadDialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.common_loading, null);
        Dialog mDialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        return mDialog;
    }

    /**
     * 显示对话框
     */
    public void showDialog(){
        if(isHidden() ){
            show(getFragmentManager(), "LoadDialogFragment");
        }
    }

    public void hideDialog(){
        if( !isHidden() ) {
            hideDialog();
        }
    }


}
