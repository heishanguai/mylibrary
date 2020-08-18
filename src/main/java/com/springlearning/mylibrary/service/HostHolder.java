package com.springlearning.mylibrary.service;

import com.springlearning.mylibrary.model.User;
import com.springlearning.mylibrary.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {

    public User getUser() {
        return ConcurrentUtils.getHost();
    }

    public void setUser(User user) {

        ConcurrentUtils.setHost(user);
    }
}
