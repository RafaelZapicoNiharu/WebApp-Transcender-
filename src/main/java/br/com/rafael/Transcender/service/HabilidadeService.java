package br.com.rafael.Transcender.service;

import br.com.rafael.Transcender.model.Habilidade;
import br.com.rafael.Transcender.model.dao.HabilidadeDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class HabilidadeService {

    @Autowired
    HabilidadeDao habs; // aqui a gente pega a dao de habilidades pra utilizar o banco

    //aqui sera o metodo que retornara todas as habilidades
    public List<Habilidade> getMyHabilidades() {
        return habs.findAll();
    }

    @Transactional
    public void saveHabilidade(Habilidade newHabilidade) { // vai estar recebendo uma nova
        //habilidade pelo post no método para então salvar utilizando a Dao

        habs.save(newHabilidade);

    }


}
