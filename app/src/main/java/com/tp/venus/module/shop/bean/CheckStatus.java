/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：CheckStatus.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/17/14:53.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import android.widget.CheckBox;

import com.alibaba.fastjson.annotation.JSONField;

/**<p>Class：com.tp.venus.module.shop.bean.CheckStatus</p>
 * <p>Description：</p>
 * <pre>
 *
 * 购物车中checkbox 选择状态
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/17/14:53
 * @version 1.0.0
 */

public class CheckStatus {

    @JSONField(serialize = false)
    public CheckBox mCheckBox;
    public boolean isChecked;
    @JSONField(serialize = false)
    public ShopCart item;

    public CheckStatus(CheckBox mCheckBox, boolean isChecked, ShopCart item) {
        this.mCheckBox = mCheckBox;
        this.isChecked = isChecked;
        this.item = item;
    }
    public CheckStatus(){}

    public CheckBox getmCheckBox() {
        return mCheckBox;
    }

    public void setmCheckBox(CheckBox mCheckBox) {
        this.mCheckBox = mCheckBox;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ShopCart getItem() {
        return item;
    }

    public void setItem(ShopCart item) {
        this.item = item;
    }

}
