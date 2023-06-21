package br.com.rafael.Transcender.controller;

import br.com.rafael.Transcender.model.Habilidade;
import br.com.rafael.Transcender.model.Usuario;
import br.com.rafael.Transcender.service.HabilidadeService;
import br.com.rafael.Transcender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/home","/"})
public class HomeController {

    @Autowired
    UserService usuarioservice;

    @GetMapping("")
    public String pageHome(Model model) {

        return "inicio";
    }
    @GetMapping("homelogado")
    public String pageHomeLogado(Model model) {

        return "inicio";
    }
    @GetMapping("/login")
    public String pageLogin(Model model) {

        return "entrar";
    }

    @GetMapping("/cadastro")
    public String pageCadastro(Model model) {

        model.addAttribute("usuario",new Usuario()); //adiciona usuario no model

        return "cadastro";
    }
    @PostMapping("cadastro/save")
    public String pageSaveUsuario(@ModelAttribute Usuario usuario,
                                     Model model, Authentication auth){

        usuarioservice.saveUsuario(usuario); //aqui utiliza o service para poder salvar
        // o usuario passada pelo formulario para o banco, atraves do save

        return "redirect:/login";
    }

}
