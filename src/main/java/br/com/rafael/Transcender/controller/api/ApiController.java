package br.com.rafael.Transcender.controller.api;


import br.com.rafael.Transcender.model.Habilidade;
import br.com.rafael.Transcender.service.HabilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = {"/api"})
public class ApiController {

    @Autowired
    HabilidadeService habilis;

    @GetMapping("/habilidades/criaVaga")
    public List<String> retornaHabilidades() {
        List<Habilidade> habilidades = habilis.getMyHabilidades();
        List<String> habilidadesList = new ArrayList<>();

        for (Habilidade habilidade : habilidades) {
            habilidadesList.add(habilidade.getNome());
        }

        return habilidadesList;
    }

}
