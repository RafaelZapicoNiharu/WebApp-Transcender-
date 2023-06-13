package br.com.rafael.Transcender.configuration.user;

import br.com.rafael.Transcender.model.Empresa;
import br.com.rafael.Transcender.model.Pessoa;
import br.com.rafael.Transcender.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserLogado implements UserDetails {
    private Usuario user;

    public UserLogado(Usuario user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> roles = new ArrayList<>();

        if (user instanceof Pessoa) {
            roles.add(new SimpleGrantedAuthority("ROLE_PESSOA"));
        } else if (user instanceof Empresa) {
            roles.add(new SimpleGrantedAuthority("ROLE_EMPRESA"));
        }
        if (user.getId() == 1){
            roles.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
        }

        return roles;
    }

    public Usuario getUser() {
        return user;
    }

    @Override
    public String getPassword() { return user.getSenha();}

    @Override
    public String getUsername() { return user.getLogin();}

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
        return true;
    }
}
