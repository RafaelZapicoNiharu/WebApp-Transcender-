package br.com.rafael.Transcender.model.dao;


import br.com.rafael.Transcender.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaDao extends JpaRepository<Empresa, Integer> {
}
