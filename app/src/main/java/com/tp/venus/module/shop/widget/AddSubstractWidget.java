/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.widget</p>
 * <p>File：AddSubstractWidget.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/22/13:52.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.widget;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.tp.venus.R;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.util.DoubleUtils;

/**<p>Class：com.tp.venus.module.shop.widget.AddSubstractWidget</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/22/13:52
 * @version 1.0.0
 */

public class AddSubstractWidget {

    public static final int MAX_INT = 99;
    public static final int MIN_INT = 1;

    private EditText buyCount;
    private TextView add;
    private TextView subtract;
    private Context mContext;
    private DialogPlus mDialogPlus;
    private OnClickListener mOnClickListener;

    public AddSubstractWidget(View view, Context mContext){
        buyCount = (EditText) view.findViewById(R.id.number);
        add = (TextView) view.findViewById(R.id.add);
        subtract = (TextView) view.findViewById(R.id.subtract);
        this.mContext = mContext;
        init();
    }


    public void addOnclickListener(OnClickListener mOnClickListener){
        this.mOnClickListener = mOnClickListener;
    }

    public int getBuyCount(){
        return DoubleUtils.getInstance().toInteger(buyCount.getText().toString(), MIN_INT);
    }

    public void setBuyCount(int buyCount){
        this.buyCount.setText(buyCount + "");
    }

    private void init(){
        RxViewListener.clicks(buyCount, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                showDialog();
            }
        });
        RxViewListener.clicks(add, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                int c = DoubleUtils.getInstance().toInteger(buyCount.getText().toString(), 1);
                if(c >= MAX_INT){
                    return ;
                }
                c = c + 1;
                mOnClickListener.onAddClickListener(add, buyCount, c);
            }
        });
        RxViewListener.clicks(subtract, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                int c = DoubleUtils.getInstance().toInteger(buyCount.getText().toString(), 1);
                if( c <= 1){
                   return ;
                }
                c = c - 1;
                mOnClickListener.onSubtractListener(subtract, buyCount, c);
            }
        });
    }

    public void showDialog(){
        if(mDialogPlus == null){
            mDialogPlus = DialogPlus.newDialog(mContext).setContentHolder(new ViewHolder(R.layout.add_subtract)).create();
        }
        if( !mDialogPlus.isShowing() ){
            mDialogPlus.show();
        }
    }

    public interface OnClickListener {

        void onAddClickListener(View view, EditText buyCount, int count);

        void onSubtractListener(View view, EditText buyCount, int count);

        void onEditChangeListener(View view);
    }

}
