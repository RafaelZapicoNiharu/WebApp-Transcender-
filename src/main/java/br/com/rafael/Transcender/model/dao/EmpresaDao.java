package br.com.rafael.Transcender.model.dao;


import br.com.rafael.Transcender.model.Empresa;
import br.com.rafael.Transcender.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EmpresaDao extends JpaRepository<Empresa, Integer> {
    Usuario findByLogin(String username);


}
