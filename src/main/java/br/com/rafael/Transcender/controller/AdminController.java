package br.com.rafael.Transcender.controller;

import br.com.rafael.Transcender.model.Habilidade;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/mod"})
public class AdminController {

    @GetMapping("/home")
    public String pageHome(Model model) {

        return "inicioAdmin";
    }
    @GetMapping("/habilidades")
    public String pageHabilidades(Model model) {

        return "habilidades";
    }

    @PostMapping("/habilidade/save")
    public String pageSaveHabilidade(@ModelAttribute Habilidade habilidade,
                                  Model model, Authentication auth){
//
//        cliServ.saveCarro(carro, (Cliente) ((UserLogado) auth.getPrincipal()).getUser());

        return "redirect:/cliente/veiculo";
    }
}
