package br.com.rafael.Transcender.service;

import br.com.rafael.Transcender.model.Empresa;
import br.com.rafael.Transcender.model.Habilidade;
import br.com.rafael.Transcender.model.Vaga;
import br.com.rafael.Transcender.model.dao.HabilidadeDao;
import br.com.rafael.Transcender.model.dao.VagaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class VagaService {
    @Autowired
    VagaDao vagas;
    //aqui sera o metodo que retornara todas as habilidades
    public List<Vaga> getMyVagasCompany(Empresa empresa) {
        return vagas.findAllByEmpresa(empresa);
    }
    @Transactional
    public void saveHabilidade(Vaga newVaga) {
        vagas.save(newVaga);
    }


}
