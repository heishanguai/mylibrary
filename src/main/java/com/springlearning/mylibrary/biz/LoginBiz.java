package com.springlearning.mylibrary.biz;

import com.springlearning.mylibrary.model.Ticket;
import com.springlearning.mylibrary.model.User;
import com.springlearning.mylibrary.model.exceptions.LoginRegisterException;
import com.springlearning.mylibrary.service.TicketService;
import com.springlearning.mylibrary.service.UserService;
import com.springlearning.mylibrary.utils.ConcurrentUtils;
import com.springlearning.mylibrary.utils.MD5;
import com.springlearning.mylibrary.utils.TicketUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginBiz {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    /**
     * 登录逻辑。先检查邮箱和密码，然后更新ticket
     * @return 返回最新的ticket
     * @throws Exception 账号密码错误
     */
    public String login(String email, String password) throws Exception {
        User user = userService.selectByEmail(email);

        //检查登录信息
        if (user == null)
            throw new LoginRegisterException("邮箱不存在");
        if (!StringUtils.equals(MD5.next(password), user.getPassword()))
            throw new LoginRegisterException("密码不正确");

        //检查ticket
        Ticket ticket = ticketService.selectByUserId(user.getId());
        //如果没有ticket，则生成一张ticket
        if (ticket == null) {
            ticket = TicketUtils.next(user.getId());
            ticketService.addTicket(ticket);
            return ticket.getTicket();
        }
        //是否过期
        if (ticket.getExpiredAt().before(new Date())){
            ticketService.deleteByUserId(user.getId());
        }

        ticket = TicketUtils.next(user.getId());
        ticketService.addTicket(ticket);

        //将成功登录的用户信息加入到CocurrentUtils
        //并返回新的ticket
        ConcurrentUtils.setHost(user);
        return ticket.getTicket();
    }

    /**
     * 登出逻辑，直接将对应的ticket删除
     */
    public void logout(String t) {
        ticketService.deleteByTicket(t);
    }

    /**
     * 注册一个用户，并返回用户的ticket
     * @return 返回用户的ticket
     */
    public String register(User user) throws Exception {

        //用户信息检查
        if (userService.selectByEmail(user.getEmail()) != null){
            throw new LoginRegisterException("用户邮箱已经存在");
        }

        //密码加密
        String md5 = MD5.next(user.getPassword());
        user.setPassword(md5);

        //数据库添加用户
        userService.addUser(user);

        //生成ticket
        Ticket ticket = TicketUtils.next(user.getId());

        //数据库中添加ticket
        ticketService.addTicket(ticket);

        ConcurrentUtils.setHost(user);
        return ticket.getTicket();
    }
}
