package com.huahuo.utils.thread;

import com.huahuo.model.user.pojos.User;

/**
 * @作者 花火
 * @创建日期 2022/10/21 14:45
 */
public class ThreadLocalUtil {
    private final static ThreadLocal<User> WM_USER_THREAD_LOCAL = new ThreadLocal<>();
    //存入线程
    public static void setUser(User wmUser)
    {
        WM_USER_THREAD_LOCAL.set(wmUser);
    }

    //从线程中获取
    public static User getUser(){
        return WM_USER_THREAD_LOCAL.get();
    }

    //清理
    public static void clear()
    {
        WM_USER_THREAD_LOCAL.remove();
    }
}
