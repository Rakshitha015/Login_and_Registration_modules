package com.example.security_login_reg1.service;

import com.example.security_login_reg1.entity.User;

public interface UserService {
    
    public User saveUser(User user);

    public void removeSessionMessage();
}
