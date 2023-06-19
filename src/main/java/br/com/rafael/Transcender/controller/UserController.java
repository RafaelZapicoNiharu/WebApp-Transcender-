package br.com.rafael.Transcender.controller;

import br.com.rafael.Transcender.configuration.user.UserLogado;
import br.com.rafael.Transcender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('PESSOA')")  //somente os users com essa role poderam utilizar esse controller
@RequestMapping(path = {"/user"})
public class UserController {

    @Autowired
    private UserService uServ; //aqui eu estou pegando o user service

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



        return "perfilUsuario";
    }
    @GetMapping("/procura")
    public String pageProcura(Model model) {

        return "procura";
    }
    @GetMapping("/minhasvagas")
    public String pageVaga(Model model) {

        return "vaga";
    }
}
