package cn.tyy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:tyy
 * @version:1.0
 * @Date:2020/10/17 9:51
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/myLogin.html").permitAll().and().csrf().disable();
//    }

    //chapter2
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/myLogin.html").
//                loginProcessingUrl("/login").successHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                httpServletResponse.setContentType("application/json;charset=UTF-8");
//                PrintWriter out=httpServletResponse.getWriter();
//                out.write("{\"error_code\":\"0\",\"message\":\"欢迎登陆系统\"}");
//            }
//        }).failureHandler(new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                httpServletResponse.setContentType("application/json;charset=UTF-8");
//                httpServletResponse.setStatus(401);
//                PrintWriter out=httpServletResponse.getWriter();
//                out.write("{\"error_code\":\"401\",\"name\":\""+e.getClass()+"\",\"message\":\""+e.getMessage()+"\"}");
//            }
//        }).and().csrf().disable();
//    }
    //chapter3
    //antMatchers("/admin/api/**)：匹配了/admin/api下的所有API
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/admin/api/**").hasRole("ADMIN").
//                antMatchers("/user/api/**").hasRole("USER").
//                antMatchers("/app.api/**").permitAll().anyRequest().authenticated().and().formLogin();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("123").roles("user").and().withUser("admin").password("123").roles("admin");
    }
    //chapter04：验证码校验
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/api/**").hasRole("ADMIN").
                antMatchers("/user/api/**").hasRole("USER").
                antMatchers("/app.api/**").permitAll().anyRequest().authenticated().
                and().csrf().disable().formLogin().and().sessionManagement().maximumSessions(1);
    }

    //    @Bean
//    public UserDetailsService userDetailSservice(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("123").roles("USER").build());
//        manager.createUser(User.withUsername("admin").password("123").roles("USER","ADMIN").build());
//        return manager;
//    }
    @Autowired
    private DataSource dataSource;
    @Bean
    public UserDetailsService userDetailsService(){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        if(!manager.userExists("user")){
            manager.createUser(User.withUsername("user").password("123").roles("USER").build());
        }
        if(!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin").password("123").roles("USER", "ADMIN").build());
        }
        return manager;
    }
}
