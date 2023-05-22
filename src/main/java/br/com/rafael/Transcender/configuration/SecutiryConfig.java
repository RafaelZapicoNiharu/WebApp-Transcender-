package br.com.rafael.Transcender.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home","/css/**","/img/**").permitAll()
                .anyRequest().authenticated().and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .permitAll();
    }

    @Bean //mana da pra usar isso aqui em todo lugar neah
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user")
                .password(passwordEncoder().encode("123"))
                .roles("PESSOA").authorities("CREATE_USER")
                .and().withUser("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMINISTRADOR").authorities("CREATE_USER");
    }
}