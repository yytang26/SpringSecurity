package cn.tyy.service;

import cn.tyy.entity.User;

import cn.tyy.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:tyy
 * @version:1.0
 * @Date:2020/10/18 21:42
 */
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userMapper.findByUser(s);
        if(null==user){
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setAuthories(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()
        ));
        return user;
    }
    private List<GrantedAuthority> generateAuthorities(String roles){
        List<GrantedAuthority> authorities = new ArrayList<>();
        String []roleArray=roles.split(";");
        if(roleArray!=null && !"".equals(roleArray)){
            for(String role:roleArray){
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }
}
