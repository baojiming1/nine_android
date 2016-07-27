/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.config</p>
 * <p>File：Status.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/17:16.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.config;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

/**<p>Class：com.tp.venus.config.Status</p>
 * <p>Description：</p>
 * <pre>
 *      所有常量类
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/17:16
 * @version 1.0.0
 */
public @interface Status {

    /**
     * TYPE_FIELD
     */
     String TYPE_FIELD = "type";

    /**
     * Parcelable对象key
     */
    String PARCELABLE_KEY = "Parcelable";

    /**
     * token
     */
    String TOKEN = "token";

    /**
     * 打开类型-client
     */
    int CLIENT = 1;

    /**
     * ID
     */
    String ID = "id";

    /**
     * 打开类型 - web
     */
    int WEB = 2;

    @interface Code{

        /**
         * 成功
         */
        int SUCCESS = 200;

        /**
         * 授权失败
         */
        int AuthenticationFail = 403;

        /**
         * 用户不存在
         */
        int USER_EMPTY = 702;
    }


    /**
     * Token是否需要的状态
     */
    @IntDef({TokenStatus.COMPLET, TokenStatus.MUST, TokenStatus.NORMAL})
    @interface TokenStatus {

        /**
         * key 字段
         */
        String KEY = "tokenStatus";

        /**
         * 必须
         */
        int MUST = 1;
        /**
         * 无需
         */
        int NORMAL = 2;
        /**
         * 有则使用，无则不使用
         */
        int COMPLET = 3;
    }


    /**
     * 用户类型常量
     */
    @StringDef({User.ID})
     @interface User{

        /**
         * 用户主键标识符
         */
        String ID = "userId";

        /**
         * 当前用户标识符
         */
        String CURRENT_ID = "currentUserId";

        /**
         * 接收者
         */
        String TOUSERID = "toUserId";

        /**
         * 手机号登陆
         */
        int MOBILE = 1;

        /**
         * 邮箱登陆
         */
        int EMAIL = 2;

    }


    /**
     * 关注关系的常量
     */
    @IntDef({Attention.USER, Attention.CONTENT, Attention.TAG})
     @interface Attention{
        /**
         * 用户之间的关注
         */
        int USER = 1;

        /**
         * 2.帖子关注
         */
         int CONTENT = 2;

        /**
         * 3.用户的标签之间的关注
         */
         int TAG = 3;
    }

    /**
     * 标签常量
     */
    @IntDef({Tag.ALL, Tag.ATTENTION, Tag.OCCUPY, Tag.JOIN})
    @interface Tag{

        String ID = "tagId";

        /**
         * 包含OCCUPY ATTENTION PARTICIPATE
         */
        int ALL = 0;

        /**
         * 1.我/他占领的标签列表
         */
         int OCCUPY  = 1;

        /**
         * 2.我/他关注的标签列表
         */
         int ATTENTION = 2;

        /**
         *  3.我/他参与的标签列表
         */
         int JOIN  = 3;


    }

    /**
     * 帖子常量
     */
    @interface Content{

        String ID = "contentId";

    }

    /**
     * 评论
     */
    @interface Comment{

        /**
         * 发送评论
         */
        int SEND__EVENT = 1;

        /**
         * 回复谁评论
         */
        int REPLY_EVENT = 2;

        /**
         *  清空评论
         */
        int CLEAR_EVENT = 3;

        /**
         * 设置touserId
         */
        int SET_TOUSERID = 4;
    }

    /**
     * 信息
     */
    @interface Message{
        /**
         * AT好友
         */
        int AT = 1;
        /**
         * 评论
         */
        int COMMENT = 2;

        /**
         * 点赞
         */
        int PARAISE = 3;

        /**
         * 粉丝
         */
        int FNS = 4;

        /**
         * 通知
         */
        int NOTICE = 5;
    }

    /**
     * 
     */
    @IntDef({Banner.ACTIVITY, Banner.ADVERT, Banner.SHOP_HOME})
    @interface Banner{
        /**
         * 1.首页广告
         */
        int ADVERT = 1;

        /**
         * 2.活动专题,
         */
        int ACTIVITY = 2;

        /**
         * 商城首页广告
         */
        int SHOP_HOME = 3;
    }

    @interface QuPai{

        String appkey = "2052aab6c9785e7";
        String appsecret = "e43ddba85ba347b1a7db423d5d361109";
        String space = "comtpvenus"; //存储目录 建议使用uid cid之类的信息

        //码率
        int DEFAULT_BITRATE = 1500 * 1000;

        /**
         * VideoPreset
         */
        String DEFAULT_VIDEO_Preset = "faster";

        /**
         * 默认CRF参数
         */
        int DEFAULT_VIDEO_RATE_CRF = 6;

        /**
         * VideoLevel
         */
        int DEFAULT_VIDEO_LEVEL = 30;

        /**
         * VideoTune
         */
        String DEFAULT_VIDEO_TUNE = "zerolatency";

        /**
         * movflags_KEY
         */
        String DEFAULT_VIDEO_MOV_FLAGS_KEY = "movflags";

        /**
         * movflags_VALUE
         */
        String DEFAULT_VIDEO_MOV_FLAGS_VALUE = "+faststart";

        /**
         * 默认最大时长
         */
        int DEFAULT_DURATION_MAX_LIMIT = 20;
        /**
         * 默认最小时长
         */
        int DEFAULT_DURATION_LIMIT_MIN = 2;

        /**
         * 水印本地路径，文件必须为rgba格式的PNG图片
         */
        String WATER_MARK_PATH = "assets://Qupai/watermark/qupai-logo.png";
    }

    /**
     * 有赞常来
     */
    @interface YouZan{
        String Home = "https://wap.koudaitong.com/v2/feature/6kw6bw7a";
        String CENTER = "http://wap.koudaitong.com/v2/showcase/usercenter?kdt_id=16180674";
        String PRODUCT = "";
        String uat = "qbtht";
        String DETAIL = "https://wap.koudaitong.com/v2/showcase/goodsfast?alias=";
    }

    /**
     *  友盟相关的常量
     */
    @interface UMeng{
        /******************** 微信 *********************/
        String WEIXIN_AppID = "wxd807109ec58edf28";
        String WEIXIN_AppSecret = "0a5e297963518f05d2d1f458a6e47407";


    }

    /**
     * 产品
     */
    @interface Product{

        String ID = "id";

    }

    /**
     * 订单
     */
    @interface Order{

        String ORDER_KEY = "order_key";

        String ORDER_DETAIL = "order_detail";

        int CHANGE_ADDRESS_CODE = 10001;

        String CHANGE_ADDRESS_KEY = "change_address_key";

        String MY_ORDER_STATUS = "my_order_status";

        String BUY_NOW_KEY = "buy_now";

        /**
         * 全部订单
         */
        int ALL = 0;
        /**
         * 1.待付款(创建订单之后的状态)
         */
        int WAIT_PAY = 1;
        /**
         * 2.待发货(用户付款之后的状态)
         */
        int WAIT_SEND_GOODS = 2;
        /**
         * 3.进行中(卖方发货之后的状态)
         */
        int ONGOING = 3;
        /**
         * 4.已完成(用户确认收货之后的状态)
         */
        int FINISH = 4;

        //1.未支付(订单创建);2.用户支付完成等待确认;3.支付成功(待发货);4.已发货(确认收货);5.完成订单（已收货）;6.申请售后;7.申请退款;9.订单关闭
        /**
         * 1.未支付(订单创建)
         */
        int STATUS_WAIT_PAY = 1;

        /**
         * 2.用户支付完成等待确认
         */
        int STATUS_WAIT_CONFIRM = 2;

        /**
         * 3.支付成功(待发货)
         */
        int STATUS_PAY_SUCCESS = 3;

        /**
         * 4.已发货(确认收货)
         */
        int STATUS_SEND_GOODS = 4;

        /**
         * 5.完成订单（已收货）
         */
        int STATUS_FINISH = 5;

        /**
         * 6.申请售后
         */
        int STATUS_CUSTOMER_SERVICE = 6;

        /**
         * 7.申请退款
         */
        int STATUS_REFUND = 7;

        /**
         * 9.订单关闭
         */
        int STATUS_CLOSE = 9;

        /**
         * 联系客服
         */
        int CALL_CUSTOMER = 10;

    }

    /**
     * 收藏
     */
    @IntDef({Favorite.CONTENT, Favorite.PRODUCT})
    @interface Favorite{

        /**
         * 帖子
         */
        int CONTENT = 1;

        /**
         * 商品
         */
        int PRODUCT = 2;

    }

    /**
     *
     */
    @interface Pay{
        /**
         * 支付宝支付
         */
        int ALIBABA_PAY = 1;

        /**
         * 微信支付
         */
        int WECHAT_PAY = 2;

        /**
         *  临时订单页回传值
          */
        int TEMP_ORDER_DETAIL = 10010;

        /**
         *  订单页回传值
         */
        int ORDER_DETAIL = 10020;

        /**
         * 支付页中的订单ID
         */
        int PAY_ORDER_ID = 10000;

        /**
         * 支付完成后的值
         */
        int PAY_RESULT = 10030;

    }
}
