package br.com.rafael.Transcender.model.dao;

import br.com.rafael.Transcender.model.Administrador;
import br.com.rafael.Transcender.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorDao extends JpaRepository<Administrador, Integer> {
    Usuario findByLogin(String username);
}
