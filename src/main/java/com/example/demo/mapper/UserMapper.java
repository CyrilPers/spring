package com.example.demo.mapper;

import com.example.demo.entities.UserAccount;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMapper {

    public static UserDetails toUserDEtails(UserAccount userAccount){
        return User.builder().username(userAccount.getUsername()).password(userAccount.getPassword()).authorities(userAccount.getAuthorities()).build();
    }
}
