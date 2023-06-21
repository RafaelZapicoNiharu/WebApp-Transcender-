package br.com.rafael.Transcender.service;

import br.com.rafael.Transcender.model.Empresa;
import br.com.rafael.Transcender.model.Pessoa;
import br.com.rafael.Transcender.model.Vaga;
import br.com.rafael.Transcender.model.dao.VagaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VagaService {
    @Autowired
    VagaDao vagas;

    //aqui sera o metodo que retornara todas as habilidades
    public List<Vaga> getMyVagasCompany(int empresa) {
        return vagas.findAllByEmpresaId(empresa);
    }
    public List<Vaga> getMyVagasUser(int loginUserLogada) {
        return vagas.findAllByCandidatosId(loginUserLogada);
    }


    public List<Vaga> getMyVagas() {
        return vagas.findAll();
    }
    @Transactional
    public void saveHabilidade(Vaga newVaga) {
        vagas.save(newVaga);
    }



}
