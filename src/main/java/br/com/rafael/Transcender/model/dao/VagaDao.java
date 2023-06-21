package br.com.rafael.Transcender.model.dao;


import br.com.rafael.Transcender.model.Empresa;
import br.com.rafael.Transcender.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaDao  extends JpaRepository<Vaga, Integer> {
    List<Vaga> findAllById(String id);

    List<Vaga> findAllByCandidatosId(int id);




    List<Vaga> findAllByEmpresaId(int empresa);


}
