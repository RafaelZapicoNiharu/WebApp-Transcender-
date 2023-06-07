package br.com.rafael.Transcender.controller;

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
    public String pagePerfil(Model model) {

        return "perfilEmpresa";
    }
}
