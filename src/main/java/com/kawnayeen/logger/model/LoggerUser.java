package com.kawnayeen.logger.model;

import com.kawnayeen.logger.model.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by kawnayeen on 1/2/17.
 */
public class LoggerUser extends Account implements UserDetails {

    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    boolean tokenAuthenticated;
    String accessToken;

    public LoggerUser(Account account) {
        super(account);
        this.initializeGrantedAuthority();
        this.tokenAuthenticated = false;
        accessToken = "Basic Auth No token";

    }

    public String getAccessToken() {
        return accessToken;
    }

    public LoggerUser(Account account, String token) {
        super(account);
        this.initializeGrantedAuthority();
        this.tokenAuthenticated = true;
        this.accessToken = token;
    }

    private void initializeGrantedAuthority() {
        grantedAuthorities = getRoles()
                .stream()
                .map(e -> new SimpleGrantedAuthority(e.getCode()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialExpired();
    }

    public boolean isTokenAuthenticated() {
        return tokenAuthenticated;
    }
}
