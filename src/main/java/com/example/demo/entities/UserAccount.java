package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public UserAccount() {
    }

    public UserAccount(Long id, String username, String password, String... authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = Arrays.stream(authorities)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast).toList();
    }
}
