/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：SearchActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/9/16:32.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.builder.ToolbarBuilder;
import com.tp.venus.module.shop.ui.fragment.SearchFragment;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.JSONUtil;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.SharePreferencesUtils;
import com.tp.venus.util.StringUtil;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.SearchActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/5/9/16:32
 */

public class SearchActivity extends BaseActivity implements SearchBox.MenuListener, SearchBox.SearchListener {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.searchbox)
    SearchBox searchbox;

    public static final String HISTORY_KEY = "historys_json_key";
    private SearchFragment mSearchFragment;
    private String searchTitle = "";
    private ToolbarBuilder mToolbarBuilder;
    private Set<String> historys;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity_search);
        ButterKnife.inject(this);
        initView();
    }

    private void initSearch(){
        searchbox.enableVoiceRecognition(this);
        searchbox.setHint("请输入搜索商品的名称");
        searchbox.setLogoText("请输入搜索商品的名称");
        searchbox.setLogoTextColor(ResourcesUtil.getColor(this, R.color.themeGray));
        searchbox.setMenuListener(this);
        searchbox.setSearchListener(this);
        Drawable icon = ResourcesUtil.getDrawable(this, R.drawable.ic_history);
        for (String history : historys) {
            searchbox.addSearchable(new SearchResult(history, icon));
        }
        searchbox.setAnimateDrawerLogo(true);
        searchbox.toggleSearch();
        searchbox.revealFromMenuItem(R.id.action_search, this);
    }

    private void initView() {
        mToolbarBuilder = getToolbarBuilder(mToolbar).setTitle(searchTitle).build();
        initNativeData();
        initSearch();
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openSearch();
                return true;
            }
        });
        mSearchFragment = SearchFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R .id.mFrameLayout, mSearchFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initNativeData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shop_search, menu);
        return true;
    }


    private void openSearch(){
        searchbox.revealFromMenuItem(R.id.action_search, this);
    }

    private void closeSearch(){
        searchbox.hideCircularly(this);
        if( !searchbox.getSearchText().isEmpty() ){
            mToolbarBuilder.getMidTextView().setText(searchTitle);
        }
    }


    private void initNativeData() {
        if( CollectionUtils.isNotEmpty(historys)){
            return ;
        }
        String str = SharePreferencesUtils.getString(this, HISTORY_KEY);
        historys = new LinkedHashSet<>();
        if( StringUtil.isEmpty(str)){
            return ;
        }
        List<String> list = JSONUtil.parseArray(str, String.class);
        if( CollectionUtils.isNotEmpty(list)){
            historys.addAll(list);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( CollectionUtils.isNotEmpty(historys)){
            String result = JSONUtil.toJSONString(historys);
            SharePreferencesUtils.putString(this, HISTORY_KEY, result);
        }
        historys = null;
        searchTitle = null;
        if( searchbox != null){
            searchbox.clearResults();
            searchbox.clearSearchable();
            searchbox = null;
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onMenuClick() {

    }

    @Override
    public void onSearchOpened() {

    }

    @Override
    public void onSearchCleared() {

    }

    @Override
    public void onSearchClosed() {
        closeSearch();
    }

    @Override
    public void onSearchTermChanged(String s) {

    }

    @Override
    public void onSearch(String s) {
        if(StringUtil.isNotEmpty(s)){
            searchTitle = s;
            if( !historys.contains(s)){
                historys.add(s);
            }
            SearchResult option = new SearchResult(s, ResourcesUtil.getDrawable(this, R.drawable.ic_history));
            searchbox.addSearchable(option);
            mSearchFragment.searchByKeyword(s);
        }
    }

    @Override
    public void onResultClick(SearchResult searchResult) {
        searchTitle = searchResult.title;
        mSearchFragment.searchByKeyword(searchTitle);
    }
}
