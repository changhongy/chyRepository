package com.otto.borrow.web.constant;
/**
 * Project Name：seckilling
 * File Name：GlobalConstant
 * Package Name：com.seckilling.constant
 * Date：2018/8/10 00:13
 */

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.constant
 * @ClassName GlobalConstant
 * @date 2018/8/10 00:13
 */
public class GlobalConstant {

    public static final String COOKIE_NAME = "token";

    public static final Integer HUNDRED_MONEY = 100;

    public static final String ACTIVITY_ONLY = "only";

    public static final String ADMIN_TYPE = "type";

    public static final String ACTIVITY_PLAY_NOTIFY = "notify";

    public static final String ACTIVITY_LOCK_KEY = "lock_play";

    public final static String PLAY_QUEUE = "play.msg.queue";

    public final static String MAIL_QUEUE = "mail.msg.queue";

    /**
     * 默认加注时间 30s
     */
    public final static long DEFAULT_TIME_SECOND = 30 * 1000L;

    /**
     * 下注
     */
    public final static String WEB_SOCKET_TOPIC_PLAY_GAME = "/topic/playGame";

    /**
     * 开奖
     */
    public final static String WEB_SOCKET_TOPIC_LOTTERY = "/topic/lottery";

    public final static String WEB_SOCKET_TOPIC_ = "";
}
