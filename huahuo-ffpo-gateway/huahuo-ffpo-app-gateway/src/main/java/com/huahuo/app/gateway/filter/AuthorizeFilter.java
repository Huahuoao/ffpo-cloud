package com.huahuo.app.gateway.filter;


import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Lazy
@Component
@Slf4j
public class AuthorizeFilter implements Ordered, GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取request和response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //2.判断是否是登录
        if (request.getURI().getPath().contains("/login")) {
            //放行
            return chain.filter(exchange);
        }
        if (request.getURI().getPath().contains("/img")) {
            //放行
            return chain.filter(exchange);
        }

        if (request.getURI().getPath().contains("/feign")) {
            //放行
            return chain.filter(exchange);
        }
        //3.获取token
        String token = request.getHeaders().getFirst("token");
        //4.判断token是否存在
        if (StringUtils.isBlank(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        String key = "bycbug";
        //5.判断token是否有效
        try {
            JWT jwt = JWTUtil.parseToken(token);
            Integer uid = (Integer) jwt.getPayload("id");
            String firstTime = (String) jwt.getPayload("outtime");
            String now1 = DateUtil.now();
            log.info("Uid=" + uid.toString());
            //是否是过期
            log.info("获取到过期时间，现在过期时间为" + firstTime);
            log.info("判断token是否有效：" + jwt.setKey(key.getBytes()).verify());
            log.info("判断token是否没有过期：" + firstTime.compareTo(now1));
            //非法
            if (!jwt.setKey(key.getBytes()).verify()) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            // 合法但是过期，需要重新登录
            if (jwt.setKey(key.getBytes()).verify() && firstTime.compareTo(now1) == -1) {
                //状态码401
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            //获取用户信息
            //更新token时间
            Date date = DateUtil.parse(now1);
            Date dateTime = DateUtil.offsetDay(date, 3);
            String s = DateUtil.formatDateTime(dateTime);
            jwt.setPayload("outtime", s);
            log.info("过期时间已更新，现在过期时间为：" + s);
            //存储header中
            ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders -> {
                httpHeaders.add("userId", uid + "");
            }).build();
            //重置请求
            exchange.mutate().request(serverHttpRequest);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }


        //6.放行

        return chain.filter(exchange);
    }

    /**
     * 优先级设置  值越小  优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
