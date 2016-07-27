package com.tp.venus.config;

/**
 * @FileName Url
 * @Description 程序中所有的请求 url
 * @Author zy
 * @Date 2015-12-15 13:45
 * @Version V 1.0
 */
public class Url {

    /** 发现 **/
    public static final String FOUND_URL = AppConfig.SERVICE_HOST + "content/search/found";

    /** 关注 **/
    public static final String ATTENTION_URL = AppConfig.SERVICE_HOST + "content/search/attention";

    /**   评论列表 **/
    public static final String COMMENT_SEARCH = AppConfig.SERVICE_HOST + "content/comment/search/";

    /**     用户的帖子列表  **/
    public static final String USER_CONTENT = AppConfig.SERVICE_HOST + "/content/search/";

    /**  用户的收藏列表   **/
    public static final String USER_FAVORITE = AppConfig.SERVICE_HOST + "/common/favorite/search";



    /** 用户标签  **/
    public static final String USER_TAG = AppConfig.SERVICE_HOST + "/content/tag/search";

    /** 用户关注列表 **/
    public static final String USER_ATTENTION = AppConfig.SERVICE_HOST + "/common/attention/search";

    /**   获取使用过此标签的帖子列表   **/
    public static final String TAG_CONTENT_SEARCH = AppConfig.SERVICE_HOST + "/content/search/tag/";

    /**     标签搜索    **/
    public static final String TAG_SEARCH = AppConfig.SERVICE_HOST + "/content/tag/search";

    /**   信息搜索  **/
    public static final String MESSAGE_SEARCH = AppConfig.SERVICE_HOST + "/common/message/search/";

    /** 用户协议  **/
    public static final String PROTOCOL = AppConfig.SERVICE_HOST + "static/protocol.html";

    /** 用户等级  **/
    public static final String LEVEL = AppConfig.SERVICE_HOST + "static/level.html";

    /**    banner搜索     **/
    public static final String BANNER_SEARCH = AppConfig.SERVICE_HOST + "/banner/list/";


    ////////////////////////////////商城
    /**
     * 获取商品详情接口
     */
    public static final String SHOP_PRODUCT_GET_INFO = "/mall/product/get_info";

    /**
     * 商品搜索列表接口
     */
    public static final String SHOP_PRODUCT_SEARCH = "/mall/product/list_search";

    /**
     * 把商品加入购物车接口
     */
    public static final String SHOP_CART_PRODUCT_ADD = "/mall/cart/product_add";

    /**
     * 购物车中的产品列表接口
     */
    public static final String SHOP_CART_LIST = "/mall/cart/product_list";

    /**
     * 变更购物车商品的选购信息(个数)接口
     */
    public static final String SHOP_CART_PRODUCT_UPDATE = "/mall/cart/product_update";

    /**
     * 删除购物车商品接口
     */
    public static final String SHOP_CART_PRODUCT_DELETE = "/mall/cart/product_delete";

    /**
     * 获取当前用户的默认收货地址详情接口
     */
    public static final String SHOP_ADDRESS_DEFAULT_INFO = "/mall/address/get_default_info";

    /**
     * 收货地址列表接口
     */
    public static final String SHOP_ADDRESS_LIST = "/mall/address/list";

    /**
     * 保存/修改收货地址接口
     */
    public static final String SHOP_ADDRESS_SAVE_UPDATE = "/mall/address/save";

    /**
     * 设置默认收货地址接口
     */
    public static final String SHOP_ADDRESS_SET_DEFAULT = "/mall/address/set_default";

    /**
     * 删除收货地址接口
     */
    public static final String SHOP_ADDRESS_DELETE = "/mall/address/delete";

    /**
     * 生成订单接口
     */
    public static final String SHOP_ORDER_CREATE = "/mall/order/create_from_cart";

    /**
     * 生成订单接口(立即购买)
     */
    public static final String SHOP_ORDER_CREATE_BUYNOW = "/mall/order/create_not_from_cart";

    /**
     * 订单列表(我的订单)接口
     */
    public static final String SHOP_ORDER_MY = "/mall/order/list";

    /**
     * 获取订单详情接口
     */
    public static final  String SHOP_ORDER_FIND_BY_ID = "/mall/order/get_info";

    /**
     * 修改订单相关信息(状态)接口
     */
    public static final String SHOP_ORDER_UPDATE_STATUS = "/mall/order/update";

    /**
     * 计算订单运费和应付总额接口
     */
    public static final String SHOP_ORDER_CALC = "/mall/order/freight/calc";

    /**
     * 订单支付接口
     */
    public static final String SHOP_ORDER_PAY = "/mall/order/pay";

    /**
     * 支付完成后APP端调用的支付完成后页面跳转接口
     */
    public static final String SHOP_ORDER_PAY_SUCCESS = "/pay/finish";

    /**
     * 购物车商品总数接口
     */
    public static final String SHOP_CART_COUNT = "/mall/cart/product_sum";

    /**
     * 保存售后服务信息接口
     */
    public static final String SHOP_SAVE_SERVICE_INFO = "/mall/order/save_aftersale_service_info";


    /**
     * 商城专题列表接口
     */
    public static final String SHOP_HOME_SPECIAL_LIST = "/mall/banner/list";

    /**
     * 根据专题ID来查询专题下的商品接口
     */
    public static final String SHOP_SPECIAL_PRODUCT_LIST = "/mall/banner/product_list_by_id";

    /**
     * 首页商品分类列表接口
     */
    public static final String SHOP_CLASSES_HOME = "/mall/product_class/home_page_list";

    /**
     * 商品分类列表接口(获取下一级分类列表)
     */
    public static final String SHOP_CLASSES_GET_CHILDREN = "/mall/product_class/list";

    /**
     * 根据分类ID获取分类下的商品列表接口
     */
    public static final String SHOP_CLASSES_GET_PRODUCT_LIST = "/mall/product_class/product_list_by_class_id";

    /**
     * 直播商品
     */
    public static final String LIVE_PRODUCT =  "/live/program/product/list/{programId}";

    /*****************************************************************************************/



}
