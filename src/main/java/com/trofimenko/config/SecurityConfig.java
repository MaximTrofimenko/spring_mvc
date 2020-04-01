package com.trofimenko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource myDataSource;

    @Autowired
    public void setMyDataSource(DataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Override//вытягивает пользователей из базы данных
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(myDataSource);
//
//        User.UserBuilder users = User.builder();

        //тут можем руками добавлять пользователей
//        auth.inMemoryAuthentication()
//                .withUser(users.username("alex")
//                .password("{noop}123")
//                .roles("USER", "ADMIN"))
//                .withUser(users.username("bob")
//                .password("{noop}123")
//                .roles("USER"));
   }

    @Override//точечная настройка параметров доступа, можно это все отключить и тоже будет работать
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//все пользователи должны пройти авторизацию
                .antMatchers("/").hasAnyRole("USER")//к данному адресу только пользователи user
//                .antMatchers("/").permitAll()  //это если все пользователи можно
                .antMatchers("/admin/**").hasRole("ADMIN")//только для тех кто админ
                .and()
                .formLogin()//для настройки форм авторизации
                .loginPage("/login")//переходим по адресу
                .loginProcessingUrl("/authenticateTheUser")//эта ссылка это не наш контроллер!это стандартный вшитый спрингом, это
                // это url куда отправляются данные форм со страницы /login
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/");

    }





}