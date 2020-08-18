package com.springlearning.mylibrary.service;

import com.springlearning.mylibrary.dao.UserDAO;
import com.springlearning.mylibrary.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDAO userDAO;

    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    public User selectById(int id) {
        return userDAO.selectById(id);
    }

    public User selectByName(String name) {
        return userDAO.selectByName(name);
    }

    public User selectByEmail(String email) {
        return userDAO.selectByEmail(email);
    }

    public void updatePassword(User user) {
        userDAO.updatePassword(user);
    }
}
