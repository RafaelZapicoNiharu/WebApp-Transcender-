package br.com.rafael.Transcender.controller;

import br.com.rafael.Transcender.configuration.user.UserLogado;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/company"})
public class EmpresaController {

    @GetMapping("/criarvagas")
    public String pageCriaVagas(Model model) {

        return "cadastroVaga";
    }
    @GetMapping("/minhasvagas")
    public String pageMinhasVagas(Model model) {

        return "cadastroVaga";
    }
    @GetMapping("/perfil")
    public String pagePerfil(Model model, Authentication auth){


        model.addAttribute("nome",
                ((UserLogado) auth.getPrincipal()).getUser().getNome());

        model.addAttribute("email",
                ((UserLogado) auth.getPrincipal()).getUser().getEmail());

        model.addAttribute("telefone",
                ((UserLogado) auth.getPrincipal()).getUser().getTelefone());
        model.addAttribute("descricao",
                ((UserLogado) auth.getPrincipal()).getUser().getDescricao());



        return "perfilEmpresa";
    }
}
