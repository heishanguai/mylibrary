package com.springlearning.mylibrary.service;

import com.springlearning.mylibrary.dao.TicketDAO;
import com.springlearning.mylibrary.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TicketService {

    @Resource
    private TicketDAO ticketDAO;

    public int addTicket(Ticket t) {
        return ticketDAO.addTicket(t);
    }

    public Ticket selectByUserId(int uid) {
        return ticketDAO.selecByUserId(uid);
    }

    public Ticket selectByTicket(String t) {
        return ticketDAO.selectByTicket(t);
    }

    public void deleteByUserId(int uid) {
        ticketDAO.deleteByUserId(uid);
    }

    public void deleteByTicket(String t) {
        ticketDAO.deleteByTicket(t);
    }
}
