package com.springlearning.mylibrary.interceptor;

import com.springlearning.mylibrary.service.TicketService;
import com.springlearning.mylibrary.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TicketService ticketService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String t = CookieUtils.getCookie("t", request);
        //无ticket，去登陆
        if (StringUtils.isEmpty(t)) {
            response.sendRedirect("/users/login");
            return false;
        }
        //ticket无效，去登陆
        if (ticketService.selectByTicket(t) == null) {
            response.sendRedirect("/users/login");
            return false;
        }
        //ticket过期
        if (ticketService.selectByTicket(t).getExpiredAt().before(new Date())) {
            response.sendRedirect("/users/login");
            return false;
        }
        return true;
    }
}
