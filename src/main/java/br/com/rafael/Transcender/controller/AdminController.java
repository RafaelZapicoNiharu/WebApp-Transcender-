package br.com.rafael.Transcender.controller;

import br.com.rafael.Transcender.model.Habilidade;
import br.com.rafael.Transcender.service.HabilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMINISTRADOR')")  //somente os users com essa role poderam utilizar esse controller
@RequestMapping(path = {"/mod"})
public class AdminController {

    @Autowired
    HabilidadeService habilis;

    @GetMapping("/home")
    public String pageHome(Model model) {

        return "inicioAdmin";
    }
    @GetMapping("/habilidades")
    public String pageHabilidades(Model model,Authentication auth) {

        List<Habilidade> habs = habilis.getMyHabilidades() ; //chama a service pra trazer as habilidades

        model.addAttribute("habilidades",habs); // bota elas no model

        return "listhabilidades"; //direciona tudo para a pagina
    }


    @GetMapping("/habilidades/new") // isso aqui ta funcionando direito
    public String pageNewHabilidade(Model model, Authentication auth){

          Habilidade a = new Habilidade(0,"Insira aqui"); // aqui cria um padrão pra deixar quando abrir
           model.addAttribute("habilida",a); //adiciona ela no model
           return "habilidades"; // aqui entra na pagina, onde vamos utilizar

    }

    @PostMapping("/habilidades/save")
    public String pageSaveHabilidade(@ModelAttribute Habilidade habilidade,
                                  Model model, Authentication auth){

          habilis.saveHabilidade(habilidade);
//        cliServ.saveCarro(carro, (Cliente) ((UserLogado) auth.getPrincipal()).getUser());

        return "redirect:/mod/habilidades";
    }
}
