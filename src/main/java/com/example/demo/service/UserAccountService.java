package com.example.demo.service;

import com.example.demo.entities.UserAccount;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAccounts() {
        userAccountRepo.save(new UserAccount(1L, "user", passwordEncoder.encode("password"), "USER"));
        userAccountRepo.save(new UserAccount(2L, "admin", passwordEncoder.encode("password"), "ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserMapper.toUserDEtails(userAccountRepo.findByUsername(username));
    }
}
