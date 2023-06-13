package br.com.rafael.Transcender.configuration;

import br.com.rafael.Transcender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalAuthentication() //lembrar de perguntar ao daves oq esses fazem pq eu esqueci :)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();}//criptografia das senhas

    @Autowired
    UserService uServ; //injeção do userservice

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uServ) //aqui vai estar pegando o user  pra logar
                .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //aqui a gente configurou quais telas serão acessadas pelos determinados usuarios do sistema
        //ou seja, as permissões que cad um vai ter, e também os que serão publicos
        http.authorizeRequests()
                .antMatchers("/home","/cadastro","/h2/**","/css/**","/img/**","/webjars/**").permitAll()
                .antMatchers("/mod/**").hasRole("ADMINISTRADOR")
                .antMatchers("/company/**").hasRole("EMPRESA")
                .antMatchers("/user/**").hasRole("EMPRESA")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .permitAll();
    }



}
