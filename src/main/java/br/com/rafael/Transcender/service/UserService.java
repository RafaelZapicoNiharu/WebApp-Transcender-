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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    //aqui se tratarão as regras de negócio em operações no banco de dados

    //injeção das daos para utilização delas
    @Autowired
    PessoaDao pessoadao;
    
    @Autowired
    EmpresaDao empresadao;

//    @Autowired
//    PasswordEncoder passo;

    @Autowired
    AdministradorDao admindao;

    @Transactional
    public void saveUsuario(Usuario newUsuario) {

            if(newUsuario.getDocumento().length()==14){ //se for 14 digitos é cnpj
                Empresa empresa = new Empresa();
                empresa.setNome(newUsuario.getNome());
                empresa.setLogin(newUsuario.getLogin());
                empresa.setDocumento(newUsuario.getDocumento());
                empresa.setTelefone(newUsuario.getTelefone());
                empresa.setDescricao(newUsuario.getDescricao());
                empresa.setEmail(newUsuario.getEmail());
                empresa.setSenha(new BCryptPasswordEncoder().encode(newUsuario.getSenha()));

                empresadao.save(empresa); //entao salva uma empresa
            } else if (newUsuario.getDocumento().length()==11) { //se for 11 digitos é cpfs
                Pessoa empresa1 = new Pessoa();
                empresa1.setNome(newUsuario.getNome());
                empresa1.setLogin(newUsuario.getLogin());
                empresa1.setDocumento(newUsuario.getDocumento());
                empresa1.setTelefone(newUsuario.getTelefone());
                empresa1.setDescricao(newUsuario.getDescricao());
                empresa1.setEmail(newUsuario.getEmail());
                empresa1.setSenha(new BCryptPasswordEncoder().encode(newUsuario.getSenha()));
                pessoadao.save(empresa1); //entao salva uma pessoa
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

    public Usuario buscarUsuarioId(int id) {
        Usuario u = pessoadao.findById(id);

        if (u == null){
            u = empresadao.findById(id);
        }
        if (u == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return u;
    }
    @Transactional
    public void editarUsuario(Usuario usuario) {

        if(usuario instanceof Pessoa){
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(usuario.getNome());
            pessoa.setLogin(usuario.getLogin());
            pessoa.setDocumento(usuario.getDocumento());
            pessoa.setTelefone(usuario.getTelefone());
            pessoa.setDescricao(usuario.getDescricao());
            pessoa.setEmail(usuario.getEmail());
            pessoa.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            pessoadao.save(pessoa);
        }else if((usuario instanceof Empresa)){
            Empresa empresa = new Empresa();
            empresa.setNome(usuario.getNome());
            empresa.setLogin(usuario.getLogin());
            empresa.setDocumento(usuario.getDocumento());
            empresa.setTelefone(usuario.getTelefone());
            empresa.setDescricao(usuario.getDescricao());
            empresa.setEmail(usuario.getEmail());
            empresa.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            empresadao.save(empresa);
        }
    }
}
