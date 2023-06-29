package br.com.rafael.Transcender.controller.api;


import br.com.rafael.Transcender.model.Habilidade;
import br.com.rafael.Transcender.service.HabilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/api"})
public class ApiController {

    @Autowired
    HabilidadeService habilis;

    @GetMapping("/habilidades/criaVaga") // vai retornar todas as habilidades que ha no sistema
    public String[] retornaHabilidades(){

        List<Habilidade> habilidades = habilis.getMyHabilidades();
        String[] habilidadesArray = new String[habilidades.size()];

        for (int i = 0; i < habilidades.size(); i++) {
            Habilidade habilidade = habilidades.get(i);
            habilidadesArray[i] = habilidade.getNome();
        }

        return habilidadesArray;
    }
}
