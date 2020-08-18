package com.springlearning.mylibrary.utils;

import com.springlearning.mylibrary.model.Ticket;
import org.joda.time.DateTime;

public class TicketUtils {

    public static Ticket next(int uid) {
        Ticket ticket = new Ticket();
        ticket.setUserId(uid);
        ticket.setTicket(UuidUtils.next());
        //设置ticket过期时间
        DateTime expiredTime = new DateTime();
        expiredTime = expiredTime.plusMonths(3);
        ticket.setExpiredAt(expiredTime.toDate());

        return ticket;
    }
}
