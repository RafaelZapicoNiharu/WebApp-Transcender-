package br.com.rafael.Transcender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/company"})
public class EmpresaController {

    @GetMapping("/vagas")
    public String pageHome(Model model) {

        return "cadastroVaga";
    }
}
