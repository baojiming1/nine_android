/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.adapter</p>
 * <p>File：SexAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/22/10:25.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**<p>Class：com.tp.venus.module.user.adapter.SexAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/22/10:25
 * @version 1.0.0
 */

public class SexAdapter extends BaseAdapter {

    public final String[] sexs = {"男", "女", "保密"};
    private Context mContext;

    public SexAdapter(Context mContext){
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return sexs.length;
    }

    @Override
    public Object getItem(int position) {
        return sexs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(mContext);
        tv.setText(sexs[position]);
        return tv;
    }
}
