package cn.tyy.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Author:tyy
 * @version:1.0
 * @Date:2020/10/18 21:03
 */
public class User implements UserDetails {
    private Long id;
    private String userName;
    private String password;
    private String roles;
    private boolean enable;
    private Collection<? extends GrantedAuthority> authories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authories;
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
        return this.enable;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setAuthories(List<GrantedAuthority> authories){
        this.authories=authories;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
