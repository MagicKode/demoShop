package com.example.demoshop.configuration;

import com.example.demoshop.service.impl.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //данная аннотация вместо @Configuration, т.к. она уже и так помечена этой аннотацией.
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailsService;

    @Override //какие url будут доступны, а какие нет
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/product/**", "/images/**", "/registration", "/user/**"/*, "/create", "/delete"*/)   //какие http  запросы нам доступны после авторизаци
                .permitAll() // разрешаем эти запросы
                .anyRequest().authenticated()
                .and()
                .formLogin()   //форма Логина нахдится по следующему урлу
                .loginPage("/login")
                .permitAll()  //обязательно прописывать РАЗРЕШЕНИЯ, иначе переход в форму Логина - ЗАЦИКЛИТСЯ!!!!
                .and()
                .logout()   // разрешаем ЛОГАУТ
                .permitAll();

    }

    @Override  //интегрируем КАК БУДЕМ АВТОРИЗОВЫВАТЬСЯ
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder()); // шифрование пароля
    }


    @Bean
    public PasswordEncoder passwordEncoder() {   //Шифрование пароля
        return new BCryptPasswordEncoder(8); //  алгаритм РАСШИФРОВКИ ПАРОЛЯ (из коробки)
    }
}
