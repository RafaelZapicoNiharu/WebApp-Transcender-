package br.com.rafael.Transcender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/user"})
public class UserController {

    @GetMapping("/perfil")
    public String pagePerfil(Model model) {

        return "perfilUsuario";
    }
    @GetMapping("/vaga")
    public String pageVaga(Model model) {

        return "vaga";
    }
}
