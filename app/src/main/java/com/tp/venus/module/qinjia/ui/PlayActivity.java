/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.ui</p>
 * <p>File：PlayActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/29/10:33.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.gotye.live.chat.Ack;
import com.gotye.live.chat.ChatObserver;
import com.gotye.live.chat.GLChatSession;
import com.gotye.live.chat.LoginAck;
import com.gotye.live.chat.Message;
import com.gotye.live.core.GLCore;
import com.gotye.live.core.GLRoomSession;
import com.gotye.live.core.model.AuthToken;
import com.gotye.live.core.model.LiveContext;
import com.gotye.live.core.model.RoomIdType;
import com.gotye.live.player.Code;
import com.gotye.live.player.GLPlayer;
import com.gotye.live.player.GLRoomPlayer;
import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.config.Constant;
import com.tp.venus.config.Status;
import com.tp.venus.module.common.ui.view.ICommonView;
import com.tp.venus.module.qinjia.entity.LiveProgram;
import com.tp.venus.module.qinjia.persenter.ILivePlayPersenter;
import com.tp.venus.module.qinjia.persenter.impl.LivePlayPersenter;
import com.tp.venus.module.qinjia.ui.fragment.PlayHorizontalFragment;
import com.tp.venus.module.qinjia.ui.fragment.PlayVerticalFragment;
import com.tp.venus.module.qinjia.ui.view.LiveListener;
import com.tp.venus.module.qinjia.view.ChatRoom;
import com.tp.venus.module.qinjia.view.GLSurfaceViewContainer;
import com.tp.venus.module.qinjia.view.NDanmaku;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * <p>Class：com.tp.venus.module.qinjia.ui.PlayActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/6/29/10:33
 */

public class PlayActivity extends BaseActivity implements GLRoomPlayer.Listener, ChatObserver, ChatRoom.SendListener, ICommonView {

    public GLRoomSession mGLRoomSession;
    public GLRoomPlayer player;
    public GLSurfaceViewContainer glSurfaceViewContainer;
    public GLChatSession chatSession;
    public ChatRoom chatRoom;
    public DanmakuView mDanmakuView;
    NDanmaku mNDanmaku;

    LiveListener mLiveListener;

    public int orientation_vertical = 1;         //1.竖屏  2.横屏
    public int orientation_horizontal = 2;
    public int orientation;
    public boolean isPlay = false;
    public CharSequence playState = "";

    private LiveProgram mLiveProgram;
    private ILivePlayPersenter mILivePlayPersenter;
    private String nickname = "游客";
    private String userId = "111111111111";
    private String room_pwd = "121212";
    private String room_id = Constant.ROOM_ID;
    private String programId;
    public boolean isConnect = false;

    public Timer timer ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qinjia_play);
        ButterKnife.inject(this);
        initData();
        orientation = getOrientation();
        initCommont(room_id, room_pwd, nickname);
        connectRoom();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        PlayVerticalFragment mPlayVerticalFragment = PlayVerticalFragment.newInstance(programId);
        mPlayVerticalFragment.initView(glSurfaceViewContainer, mDanmakuView);
        mLiveListener = mPlayVerticalFragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, mPlayVerticalFragment).commit();
        startTask();
    }

    private void initData(){
        mLiveProgram = getIntent().getParcelableExtra(Status.PARCELABLE_KEY);
        if(mLiveProgram != null){
            room_pwd = mLiveProgram.getUserPwd();
            room_id = mLiveProgram.getRoomId();
            programId = mLiveProgram.getProgramId();
        }
        if(mILivePlayPersenter == null){
            mILivePlayPersenter = new LivePlayPersenter(this);
        }
        User user = mILivePlayPersenter.getCurrentUser();
        if( user != null){
            nickname = user.getNickname();
            userId = user.getUserId();
        }
    }

    public Handler handler = new Handler(){

        public void handleMessage( android.os.Message msg) {
            switch (msg.what) {
                case 1 :
                    mLiveListener.hideVideoLayout();
                    break;
            }
        }
    };

    public void startTask(){
        timer = new Timer(true);
        timer.schedule(new MyTimerTask(),1000, 10000); //延时1000ms后执行，6000ms执行一次
    }

    /**
     * 重置定时任务
     */
    public void resetTask(){
        timer.cancel();
        timer = null;
        startTask();
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            android.os.Message message = new android.os.Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }


    public int getOrientation(){
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation ; //获取屏幕方向
        if(ori == mConfiguration.ORIENTATION_LANDSCAPE){            //横屏
            return orientation_horizontal;
        }else if(ori == mConfiguration.ORIENTATION_PORTRAIT){       //竖屏
            return orientation_vertical;
        }
        return orientation_vertical;
    }

    //链接放假
    private void connectRoom(){
        mGLRoomSession.auth(new GLRoomSession.Callback<AuthToken>() {
            @Override
            public void onFinish(int code, AuthToken authToken) {
                if (code == Status.Code.SUCCESS) {
                    //验证成功
                    connectChatRoom();
                    isConnect = true;
                    player.play();
                } else {
                    //验证失败
                    ToastUtil.getInstance().show("验证失败");
                }
            }
        });
    }

    //初始化组件
    private void initCommont(String roomId, String roomPwd, String nickname){
        glSurfaceViewContainer = new GLSurfaceViewContainer(this);
        mGLRoomSession = GLCore.createSession(roomId, roomPwd, nickname, RoomIdType.GOTYE);
        player = new GLRoomPlayer(mGLRoomSession); // 设 置 回 调 player.setListener(this);
        player.setListener(this);
        //mGLSurfaceView.setDisplayMode(GLSurfaceView.SCREEN_FILL);
        player.setSurfaceView(glSurfaceViewContainer.getSurfaceView());
        chatRoom = new ChatRoom(this);
        chatRoom.addSendListener(this);
        chatSession = new GLChatSession(mGLRoomSession);
        chatSession.addObserver(this);
        mDanmakuView = new DanmakuView(this);
        mNDanmaku = new NDanmaku(mDanmakuView, this);
    }



    //链接聊天室
    public void connectChatRoom(){
        chatSession.login(new Ack<LoginAck>() {
            @Override
            public void ack(LoginAck loginAck) {
                if (loginAck.getCode() == com.gotye.live.chat.Code.SUCCESS) {
                    chatSession.sendNotify("enter", null);
                    chatRoom.getChatBubble().addChat("连接聊天服务器成功", "");
                } else {
                    //失败
                    chatRoom.getChatBubble().addChat("连接聊天服务器失败", "");
                }
            }
        });
    }


    public void changeFragment(int orientation){
        player.onStop();
        isPlay = false;
        if(orientation == orientation_vertical){            //竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            PlayVerticalFragment mPlayVerticalFragment = PlayVerticalFragment.newInstance(programId);
            mPlayVerticalFragment.initView(glSurfaceViewContainer, mDanmakuView);
            mLiveListener = mPlayVerticalFragment;
            getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, mPlayVerticalFragment).commit();

        } else {                                //横屏
           // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            PlayHorizontalFragment mPlayHorizontalFragment = PlayHorizontalFragment.newInstance();
            mPlayHorizontalFragment.initView(glSurfaceViewContainer, chatRoom, mDanmakuView);
            mLiveListener = mPlayHorizontalFragment;
            getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, mPlayHorizontalFragment).commit();
        }
    }


    @Override
    protected void onStop() {
        player.onStop();
        isPlay = false;
        super.onStop();
    }

    @Override
    protected void onResume() {
        mNDanmaku.onResume();
        player.setListener(this);
        player.onResume();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        mNDanmaku.onBackPressed();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        mNDanmaku.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mNDanmaku.onDestroy();
        player.onDestroy();
        chatSession.removeObserver(this);
        chatSession.logout();
        mGLRoomSession.destroy();
        timer.cancel();
        timer.purge();
        super.onDestroy();
    }

    @Override
    public void onLiveStateChanged(GLRoomPlayer glRoomPlayer, GLRoomPlayer.LiveState state) {
        //Toast.makeText(this, "主播状态改变 : " + state, Toast.LENGTH_SHORT).show();
        if (state == GLRoomPlayer.LiveState.STOPPED) {
            isPlay = false;
            hide("直播结束");
        } else {
            isPlay = true;
            show("");
        }
    }

    @Override
    public void onPlayerDisconnected(GLPlayer glPlayer) {
        isPlay = false;
        show("网络异常");
    }

    @Override
    public void onPlayerReconnecting(GLPlayer glPlayer) {
        isPlay = false;
        show("正在连接...");
    }

    @Override
    public void onPlayerConnected(GLPlayer glPlayer) {
        isPlay = true;
        hide("");
    }

    private void show(CharSequence str){
        playState = str;
        mLiveListener.show(str);
    }

    private void hide(CharSequence str){
        playState = str;
        mLiveListener.hide(str);
    }



    @Override
    public void onPlayerError(GLPlayer glPlayer, int errorCode) {
        isPlay = false;
        switch (errorCode) {
            case Code.LIVE_NOT_STARTTEDYET:
                show("直播未开始");
                break;
            case Code.NETWORK_DISCONNECT:
                show("网络断开");
                break;
            case Code.GET_LIVE_STATE_FAILED:
                show("获取直播状态失败");
                break;
            case Code.VERIFY_FAILED:
                show("token无效");
                break;
            case Code.FAILED:
                show("失败");
                break;
            case Code.GET_LIVE_URL_FAILED:
                show("获取直播URL失败");
                break;
        }
    }

    @Override
    public void onPlayerStatusUpdate(GLPlayer glPlayer) {

    }

    @Override
    public void onDisconnected(GLChatSession glChatSession) {

    }

    @Override
    public void onReloginSuccess(GLChatSession glChatSession) {

    }

    @Override
    public void onRelogining(GLChatSession glChatSession) {

    }

    @Override
    public void onReloginFailed(GLChatSession glChatSession) {

    }

    @Override
    public void onForceLogout(GLChatSession glChatSession) {
        Dialog alertDialog = new AlertDialog.Builder(this).
                setTitle("提示").
                setMessage("你的帐号在别处登录").
                setIcon(null).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        finish();

                    }
                }).
                create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    public void onReceiveMessage(GLChatSession glChatSession, Message message) {
        String a = message.getText();
        if (a.equals("enter")&&message.getMessageType()== Message.NOTIFY) {
            message.setText("进入了频道");
            mGLRoomSession.getLiveContext(new GLRoomSession.Callback<LiveContext>() {
                @Override
                public void onFinish(int code, LiveContext object) {
                    Intent mIntent = new Intent("UPDATA");
                    mIntent.putExtra("count", object.getPlayUserCount());
                    sendBroadcast(mIntent);
                }
            });
        } else {
            mNDanmaku.send(a);
        }
        chatRoom.getChatBubble().addChat(message.getSenderNickname(), message.getText());
    }

    @Override
    public void send() {
        mNDanmaku.send(chatRoom.getEditText());
        chatRoom.sendMessage(nickname, userId, chatSession);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }


}
