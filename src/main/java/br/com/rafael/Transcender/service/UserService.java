package br.com.rafael.Transcender.service;

import br.com.rafael.Transcender.configuration.user.UserLogado;
import br.com.rafael.Transcender.model.*;
import br.com.rafael.Transcender.model.dao.AdministradorDao;
import br.com.rafael.Transcender.model.dao.EmpresaDao;
import br.com.rafael.Transcender.model.dao.PessoaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    //aqui se tratarão as regras de negócio em operações no banco de dados

    //injeção das daos para utilização delas
    @Autowired
    PessoaDao pessoadao;
    
    @Autowired
    EmpresaDao empresadao;

    @Autowired
    AdministradorDao admindao;

    @Transactional
    public void saveUsuario(Usuario newUsuario) {
        if(newUsuario.getDocumento().length()==14){ //se for 14 digitos é cnpf
            empresadao.save((Empresa) newUsuario); //entao salva uma empresa
        } else if (newUsuario.getDocumento().length()==11) { //se for 11 digitos é cnpf
            pessoadao.save((Pessoa) newUsuario); //entao salva uma pessoa
        }else{
            throw new UsernameNotFoundException("Documento deve ter 11 ou 14 digitos!");
        }
    }




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
