package com.example.security_login_reg1.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.security_login_reg1.entity.User;


public interface UserRepo extends JpaRepository<User,Integer> {
    

    public User findByEmail(String email);
    
}
