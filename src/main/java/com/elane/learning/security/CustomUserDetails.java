package com.elane.learning.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义用户
 */
@Data
public class CustomUserDetails implements UserDetails {

    private Set<GrantedAuthority> authorities;

    private String password;

    private String username;

    private String status;

    private String tenantCode;

    public CustomUserDetails(String username, String password, String status, String tenantCode, Set<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.tenantCode = tenantCode;
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return SecurityConstant.NORMAL.equals(this.status);
    }
}
