/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.model</p>
 * <p>File：Menu.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/16/10:54.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

/**<p>Class：com.tp.venus.model.Menu</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/16/10:54
 * @version 1.0.0
 */

public class Menu {
    public int id;
    public @StringRes int title;
    public String subTitle;
    public @DrawableRes int icon;
    public @DrawableRes int forward;
    public String rightTitle;
    public @DrawableRes int rightBackground;
    public @LayoutRes int rightView;


    public Menu(){}


    public static class  Builder{
        private Menu menu;

        public static Builder create(){
            return new Builder();
        }
        private Builder(){
            this.menu = new Menu();
        }

        public Builder addId(int id){
            menu.id = id;
            return this;
        }

        public Builder addTitle(@StringRes int title){
            menu.title = title;
            return this;
        }

        public Builder addSubTitle(String subTitle){
            menu.subTitle = subTitle;
            return this;
        }

        public Builder addIcon(@DrawableRes int icon){
            menu.icon = icon;
            return this;
        }

        public Builder addForward(@DrawableRes int forward){
            menu.forward = forward;
            return this;
        }

        public Builder addRightTitle(String rightTitle){
            menu.rightTitle = rightTitle;
            return this;
        }

        public Builder addRightBackground(@DrawableRes int rightBackground){
            menu.rightBackground = rightBackground;
            return this;
        }

        public Builder addRightView(@LayoutRes int rightView){
            menu.rightView = rightView;
            return this;
        }

        public Menu build(){
            return this.menu;
        }

    }

}
