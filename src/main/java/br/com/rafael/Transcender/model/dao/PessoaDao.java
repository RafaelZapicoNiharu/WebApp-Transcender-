package br.com.rafael.Transcender.model.dao;



import br.com.rafael.Transcender.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaDao extends JpaRepository<Pessoa, Integer> {
}
