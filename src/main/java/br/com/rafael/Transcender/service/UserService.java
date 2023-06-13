package br.com.rafael.Transcender.service;

import br.com.rafael.Transcender.configuration.user.UserLogado;
import br.com.rafael.Transcender.model.Administrador;
import br.com.rafael.Transcender.model.Usuario;
import br.com.rafael.Transcender.model.dao.AdministradorDao;
import br.com.rafael.Transcender.model.dao.EmpresaDao;
import br.com.rafael.Transcender.model.dao.PessoaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {
    //aqui se tratarão as regras de negócio em operações no banco de dados

    //injeção das daos para utilização delas
    @Autowired
    PessoaDao pessoadao;
    
    @Autowired
    EmpresaDao empresadao;

    @Autowired
    AdministradorDao admindao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = pessoadao.findByLogin(username);

        if (u == null){
            u = empresadao.findByLogin(username);
        }
        if (u == null){
            u = admindao.findByLogin(username);
        }
        if (u == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new UserLogado(u);
    }
}
