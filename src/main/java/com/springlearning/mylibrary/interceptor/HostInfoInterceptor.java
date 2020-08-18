package com.springlearning.mylibrary.interceptor;

import com.springlearning.mylibrary.model.Ticket;
import com.springlearning.mylibrary.model.User;
import com.springlearning.mylibrary.service.TicketService;
import com.springlearning.mylibrary.service.UserService;
import com.springlearning.mylibrary.utils.ConcurrentUtils;
import com.springlearning.mylibrary.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    /**
     * 注入host信息，即登陆的user信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String t = CookieUtils.getCookie("t", request);
        if (!StringUtils.isEmpty(t)) {
            Ticket ticket = ticketService.selectByTicket(t);
            if (ticket != null && ticket.getExpiredAt().after(new Date())) {
                User host = userService.selectById(ticket.getUserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }
}
