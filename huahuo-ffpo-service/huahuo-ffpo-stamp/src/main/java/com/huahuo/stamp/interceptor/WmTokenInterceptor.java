package com.huahuo.stamp.interceptor;


import com.huahuo.model.user.pojos.User;
import com.huahuo.utils.thread.ThreadLocalUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @作者 花火
 * @创建日期 2022/10/21 14:42
 */
public class WmTokenInterceptor implements HandlerInterceptor {
    @Override
    /**
     * 得到header用户信息，存入线程
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        if (userId != null) {
            User wmUser = new User();
            wmUser.setId(Integer.valueOf(userId));
            ThreadLocalUtil.setUser(wmUser);
        }
        return true;
    }

    @Override
    /**
     * 删除id信息
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadLocalUtil.clear();
    }
}
