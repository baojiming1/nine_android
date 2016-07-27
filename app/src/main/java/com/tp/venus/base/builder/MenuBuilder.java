/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.builder</p>
 * <p>File：MenuBuilder.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/16/11:55.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.builder;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.tp.venus.base.adapter.MenuCommonAdapter;
import com.tp.venus.model.Menu;

import java.util.ArrayList;
import java.util.Collection;

/**<p>Class：com.tp.venus.base.builder.MenuBuilder</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/16/11:55
 * @version 1.0.0
 */

public class MenuBuilder {

    private MenuCommonAdapter mMenuCommonAdapter;
    private ArrayList<Menu> mMenus;
    private boolean showForward = true;
    private boolean showLiner = false;




    public static MenuBuilder create(MenuCommonAdapter mMenuCommonAdapter){
        return new MenuBuilder(mMenuCommonAdapter);
    }

    MenuBuilder(MenuCommonAdapter mMenuCommonAdapter){
        mMenus = new ArrayList<Menu>();
        this.mMenuCommonAdapter = mMenuCommonAdapter;
    }

    public MenuBuilder addMenu(Menu menu){
        mMenus.add(menu);
        return this;
    }

    public MenuBuilder addMenu(int id, @StringRes int title){
        Menu menu = new Menu();
        menu.id = id;
        menu.title = title;
        mMenus.add(menu);
        return this;
    }

    public MenuBuilder addMenu(int id, @StringRes int title, String rightTitle){
        Menu menu = new Menu();
        menu.id = id;
        menu.title = title;
        menu.rightTitle = rightTitle;
        mMenus.add(menu);
        return this;
    }

    public MenuBuilder addMenu(int id, @StringRes int title, @DrawableRes int icon){
        Menu menu = new Menu();
        menu.id = id;
        menu.title = title;
        menu.icon = icon;
        mMenus.add(menu);
        return this;
    }

    public MenuBuilder addMenu(int id, @StringRes int title, @DrawableRes int icon, String subTitle,  String rightTitle, @DrawableRes int rightIcon){
        Menu menu = new Menu();
        menu.id = id;
        menu.title = title;
        menu.icon = icon;
        menu.subTitle = subTitle;
        menu.rightTitle = rightTitle;
        menu.rightBackground = rightIcon;
        mMenus.add(menu);
        return this;
    }


    public MenuBuilder addMenu(int id, @StringRes int title, @DrawableRes int icon,   String rightTitle, @DrawableRes int rightIcon){
        Menu menu = new Menu();
        menu.id = id;
        menu.title = title;
        menu.icon = icon;
        menu.rightTitle = rightTitle;
        menu.rightBackground = rightIcon;
        mMenus.add(menu);
        return this;
    }



    public MenuBuilder addMenu(int id, @StringRes int title, @DrawableRes int icon, String rightTitle){
        Menu menu = new Menu();
        menu.id = id;
        menu.title = title;
        menu.icon = icon;
        menu.rightTitle = rightTitle;
        mMenus.add(menu);
        return this;
    }

    public MenuBuilder addMenu(int id, @StringRes int title, String subTitle, @DrawableRes int icon, String rightTitle){
        Menu menu = new Menu();
        menu.id = id;
        menu.title = title;
        menu.icon = icon;
        menu.subTitle = subTitle;
        menu.rightTitle = rightTitle;
        mMenus.add(menu);
        return this;
    }



    public MenuBuilder addMenu4RightIcon(int id, @StringRes int title, @DrawableRes int icon, @DrawableRes int rightIcon){
        Menu menu = new Menu();
        menu.id = id;
        menu.title = title;
        menu.icon = icon;
        menu.rightBackground = rightIcon;
        mMenus.add(menu);
        return this;
    }



    /**
     * 构建
     * @return
     */
    public Collection<Menu> build(){
        mMenuCommonAdapter.setShowForward(this.showForward);
        mMenuCommonAdapter.setShowLiner(this.showLiner);
        return this.mMenus;
    }

    /**
     * 显示自定义的下划线
     * @return
     */
    public MenuBuilder showLiner(){
        this.showLiner = true;
        return this;
    }

    /**
     * 隐藏跳转的图标
     * @return
     */
    public MenuBuilder  hideForward(){
        this.showForward = false;
        return this;
    }

    public boolean isShowForward(){
        return this.showForward;
    }

    public boolean isShowLiner(){
        return this.showLiner;
    }

    public Collection<Menu> getMenus(){
        return this.mMenus;
    }



}
