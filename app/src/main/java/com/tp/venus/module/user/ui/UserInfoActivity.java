/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：UserInfoActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/14/14:14.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.adapter.MenuCommonAdapter;
import com.tp.venus.base.builder.MenuBuilder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.model.Menu;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.presenter.IUserInfoPresenter;
import com.tp.venus.module.user.presenter.impl.UserInfoPresenter;
import com.tp.venus.module.user.ui.view.IUserInfoView;
import com.tp.venus.module.user.util.UserUtil;
import com.tp.venus.util.FileUtil;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.util.qiniu.MediaUploader;
import com.tp.venus.util.qiniu.PictureUploader;

import java.io.File;
import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <p>Class：com.tp.venus.module.user.ui.UserInfoActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/14/14:14
 */

public class UserInfoActivity extends BaseActivity implements IUserInfoView {

    private final int icon = 1, nickname = 2, sex = 3, area = 4;
    private static final int IMAGE_RESULT_OK = 1;       //图片


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.top_list)
    RecyclerView mTopRecyclerView;
    @InjectView(R.id.bottom_list)
    RecyclerView mBottomRecyclerView;


    private TopMenuCommonAdapter topMenuCommonAdapter ;
    private BottomMenuCommonAdapter bottomMenuCommonAdapter ;
    private MenuOnClick mMenuOnClick = new MenuOnClick();

    private IUserInfoPresenter mIUserInfoPresenter;
    private User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_info_edit);

        ButterKnife.inject(this);
        initView();
    }

    private void initView(){
        if( mIUserInfoPresenter == null){
            mIUserInfoPresenter = getPresenter(new UserInfoPresenter(this));
        }
        mUser = mIUserInfoPresenter.getCurrentUser();
        if(mUser == null){
            mIUserInfoPresenter.goLoginView();
            return;
        }
        getToolbarBuilder(mToolbar).setTitle(R.string.user_info).build();
        topMenuCommonAdapter = new TopMenuCommonAdapter(this);
        RecyclerViewBuilder.create(mTopRecyclerView).setAdapter(topMenuCommonAdapter).setDefaultItemDecoration(RecyclerViewBuilder.DECOR_VERTICAL_LIST, R.drawable.divider_bg_fine)
                .addOnRecyclerViewItemClickListener(mMenuOnClick).build();

        bottomMenuCommonAdapter = new BottomMenuCommonAdapter(this);
        RecyclerViewBuilder.create(mBottomRecyclerView).setAdapter(bottomMenuCommonAdapter).setDefaultItemDecoration(RecyclerViewBuilder.DECOR_VERTICAL_LIST, R.drawable.divider_bg_fine)
                .addOnRecyclerViewItemClickListener(mMenuOnClick).build();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if( requestCode == RESULT_OK){
            return ;
        }
        if(requestCode == IMAGE_RESULT_OK){                 //上传头像
            openAlbum(data);
        }
    }

    //上传头像
    private void openAlbum(Intent data){
        if(data == null){
            return ;
        }
        Uri originalUri = data.getData();        //获得图片的uri
        if(originalUri == null ){
            return ;
        }
        PictureUploader mPictureUploader = new PictureUploader(this, this);
        String[] proj = {MediaStore.Images.Media.DATA};
        //好像是android多媒体数据库的封装接口，具体的看Android文档
        Cursor cursor = managedQuery(originalUri, proj, null, null, null);
        //按我个人理解 这个是获得用户选择的图片的索引值
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        //将光标移至开头 ，这个很重要，不小心很容易引起越界
        cursor.moveToFirst();
        //最后根据索引值获取图片路径
        String path = cursor.getString(column_index);
        File file = FileUtil.getFile(path, this);
        mPictureUploader.upload(file);
        mPictureUploader.setListener(new MediaUploader.UploadListener() {
            @Override
            public void onStarted(int total, int position) {

            }

            @Override
            public void onProgress(int total, int position, double progress) {

            }

            @Override
            public void onOk(int total, int position, String url) {
                mIUserInfoPresenter.editIcon(url);
            }

            @Override
            public void onFailed(int total, int position, double progress, String errorMessage) {
                ToastUtil.getInstance().show("上传图片失败,请重试");
            }
        });
    }


    @Override
    public void onSuccess(String data, @UserInfoType int type) {
        String message = "";
        switch (type){
            case UserInfoType.ADDRESS:
                message = "修改地址成功";
                bottomMenuCommonAdapter.addressTV.setText(data);
                break;
            case UserInfoType.ICON :
                message = "修改头像成功";
                GlideManager.with(UserInfoActivity.this).loadImage4user(topMenuCommonAdapter.iconV, data);
                 break;
            case UserInfoType.NICKNAME :
                message = "修改昵称成功";
                topMenuCommonAdapter.nicknameTV.setText(data);
                break;
            case UserInfoType.SEX :
                message = "修改性别成功";
                bottomMenuCommonAdapter.sexTV.setText(data);
                break;
            default:
                return ;
        }
        ToastUtil.getInstance().show(message);
    }

     @Override
    public void onError(String message) {
        ToastUtil.getInstance().show(message);
    }


    /*******************************************************************************/
    /**
     * Top菜单
     */
    class TopMenuCommonAdapter extends MenuCommonAdapter<Menu>{

        public TextView nicknameTV ;
        public CircleImageView iconV;

        public TopMenuCommonAdapter(Context mContext) {
            super(mContext);
        }

        @Override
        public Collection<Menu> getMenu(MenuBuilder mMenuBuilder) {
            Menu mIcon = Menu.Builder.create().addId(icon).addTitle( R.string.icon ).addRightView(R.layout.common_user_icon).build();
            Menu mNickname = Menu.Builder.create().addId(nickname).addTitle(R.string.nickname).addRightView(R.layout.menu_right_textview).build();
            return mMenuBuilder.addMenu(mIcon)
                    .addMenu(mNickname)
                    .build();
        }

        @Override
        public void after(CommonViewHolder mCommonViewHolder, Menu item, int position) {
            ViewStub mViewStub = mCommonViewHolder.findViewById(R.id.rightView);
            mViewStub.setLayoutResource(item.rightView);
            switch (item.id){
                case icon :
                    if( iconV == null){
                        iconV = (CircleImageView) mViewStub.inflate();
                    }
                    ViewGroup.LayoutParams lp = iconV.getLayoutParams();
                    lp.height = 55;
                    lp.width = 55;
                    iconV.setLayoutParams(lp);
                    GlideManager.with(UserInfoActivity.this).loadImage4user(iconV, mUser.getIcon());
                    break;
                case nickname :
                    if( nicknameTV == null){
                        nicknameTV = (TextView) mViewStub.inflate();
                    }
                    nicknameTV.setText(mUser.getNickname() + "");
                    break;
            }
        }
    }

    /**
     * Bottom菜单
     */
    class BottomMenuCommonAdapter extends MenuCommonAdapter<Menu>{
        public TextView sexTV;
        public TextView addressTV;
        public BottomMenuCommonAdapter(Context mContext) {
            super(mContext);
        }
        @Override
        public Collection<Menu> getMenu(MenuBuilder mMenuBuilder) {
            Menu mSex = Menu.Builder.create().addId(sex).addTitle(R.string.gender).addRightView(R.layout.menu_right_textview).build();
            Menu mArea = Menu.Builder.create().addId(area).addTitle(R.string.area).addRightView(R.layout.menu_right_textview).build();
            return mMenuBuilder.addMenu(mSex)
                    .addMenu(mArea)
                    .build();
        }

        @Override
        public void after(CommonViewHolder mCommonViewHolder, Menu item, int position) {
            ViewStub mViewStub = mCommonViewHolder.findViewById(R.id.rightView);
            mViewStub.setLayoutResource(item.rightView);
            switch (item.id){
                case sex :
                    if( sexTV == null){
                        sexTV = (TextView) mViewStub.inflate();
                    }
                    sexTV.setText(UserUtil.getGender(mUser.getGender()));
                    break;
                case area:
                    if( addressTV == null){
                        addressTV = (TextView) mViewStub.inflate();
                    }
                    addressTV.setText(mUser.getAddress());
                    break;
            }
        }
    }

    class MenuOnClick implements CommonAdapter.OnRecyclerViewItemClickListener<Menu> {


        @Override
        public void onItemClick(View view, Menu item, int position) {
            switch (item.id){
                case icon :
                    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent, IMAGE_RESULT_OK);
                    break;
                case nickname :
                    createNicknameDialog();
                    break;
                case sex :
                    createSexDialog();
                    break;
                case area :
                    createCPDDialog();
                    break;
            }
        }
    }

    /**
     * 修改地区的对话框
     */
    private void createCPDDialog(){
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("输入修改的地区")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String address = et.getText().toString();
                        mIUserInfoPresenter.editAddress(address);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


    /**
     * 创建一个对话框
     */
    private void createNicknameDialog(){
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("输入修改的昵称")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String nickname = et.getText().toString();
                        mIUserInfoPresenter.editNickName(nickname);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

   // ArrayAdapter adapter ;
    /**
     * 创建编辑性别的对话框
     */
    private void createSexDialog(){
     /*   final Spinner mSpinner = new Spinner(UserInfoActivity.this);
        if( adapter == null){
            adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.test_list_item, ResourcesUtil.getStringArray(this, R.array.sexs));
        }
        mSpinner.setAdapter(adapter);*/
        final Spinner mSpinner = (Spinner) LayoutInflater.from(this).inflate(R.layout.sex_layout, null);

       // mSpinner.set
      //  final EditText sexET = new EditText(UserInfoActivity.this);
        new AlertDialog.Builder(UserInfoActivity.this).setTitle("输入修改的性别")
                .setView(mSpinner)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView mTextView = (TextView) mSpinner.getSelectedView();
                        String sex = mTextView.getText().toString();
                        short gender = 0;               //性别 0：保密  1：女 2：男
                        String sGender = "";
                        switch (sex){
                            case "女":
                                gender = 1;
                                sGender = "女";
                                break;
                            case "男" :
                                gender = 2;
                                sGender = "男";
                                break;
                            case "保密":
                                gender = 0;
                                sGender = "保密";
                                break;
                            default:
                                ToastUtil.getInstance().show("对不起，该只接受'女','男','保密'的字样");
                                return ;
                        }
                        mIUserInfoPresenter.editGender(gender, sGender);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

}
