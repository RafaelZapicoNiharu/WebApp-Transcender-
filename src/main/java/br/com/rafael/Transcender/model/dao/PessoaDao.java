package br.com.rafael.Transcender.model.dao;



import br.com.rafael.Transcender.model.Pessoa;
import br.com.rafael.Transcender.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaDao extends JpaRepository<Pessoa, Integer> {
    Usuario findByLogin(String username);
}
