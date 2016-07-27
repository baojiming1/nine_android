/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.adapter</p>
 * <p>File：MenuCommonAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/16/11:01.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.builder.MenuBuilder;
import com.tp.venus.model.Menu;

import java.util.Collection;

/**<p>Class：com.tp.venus.base.adapter.MenuCommonAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/16/11:01
 * @version 1.0.0
 */

public abstract class MenuCommonAdapter<T extends Menu> extends CommonAdapter<T, CommonViewHolder>  {

    private boolean showForward ;
    private boolean showLiner;


    public MenuCommonAdapter(Context mContext) {
        super(mContext, R.layout.common_menu);
        addAll( getMenu(MenuBuilder.create(this)) );
    }



    /**
     * a通用菜单初始化
     * @param mCommonViewHolder
     * @param item
     * @param position
     */
    @Override
    public void convert(final CommonViewHolder mCommonViewHolder, final T item, int position) {
        ImageView menu_icon = mCommonViewHolder.findViewById(R.id.menu_icon);
        TextView menu_title = mCommonViewHolder.findViewById(R.id.menu_title);
        TextView menu_subtitle = mCommonViewHolder.findViewById(R.id.menu_subtitle);
        TextView right_title = mCommonViewHolder.findViewById(R.id.right_title);
        ImageView menu_forward = mCommonViewHolder.findViewById(R.id.menu_forward);
        View menu_liner = mCommonViewHolder.findViewById(R.id.menu_liner);
        if(item.icon != 0 ){
            menu_icon.setImageResource(item.icon);
        } else {
            menu_icon.setVisibility(View.GONE);
        }
        menu_title.setText(item.title);
        if( !TextUtils.isEmpty(item.subTitle ) ){
            menu_subtitle.setText(item.subTitle);
            menu_subtitle.setVisibility(View.VISIBLE);
        }

        if( !TextUtils.isEmpty(item.rightTitle) ){
            right_title.setText(item.rightTitle);
            right_title.setVisibility(View.VISIBLE);
            if( item.rightBackground != 0){
                right_title.setBackgroundResource(item.rightBackground);
               // right_title.setVisibility(View.VISIBLE);
            }
        } else{
            right_title.setVisibility(View.GONE);
        }

        if( !showForward ){
            menu_forward.setVisibility(View.GONE);
        }
        if( showLiner ){
            if( !isLast( position) ){
               /* RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) menu_liner.getLayoutParams();
                lp.setMargins(lp.leftMargin, 20, lp.rightMargin, lp.bottomMargin);
                menu_liner.setLayoutParams(lp);*/
                menu_liner.setVisibility(View.VISIBLE);
            }
        }

        after(mCommonViewHolder, item, position);

    }


    public void setShowForward(boolean showForward) {
        this.showForward = showForward;
    }

    public void setShowLiner(boolean showLiner) {
        this.showLiner = showLiner;
    }

    /**
     * 获取菜单
     * @return
     */
    public abstract Collection<T> getMenu(MenuBuilder mMenuBuilder);

    /**
     * 之后的处理事件, 需要时请重写
     * @param mCommonViewHolder
     * @param item
     * @param position
     */
    public  void after(CommonViewHolder mCommonViewHolder,  T item, int position){

    }


}
