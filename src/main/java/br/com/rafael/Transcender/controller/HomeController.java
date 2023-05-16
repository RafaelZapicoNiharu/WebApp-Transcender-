package br.com.rafael.Transcender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/home","/"})
public class HomeController {

    @GetMapping("")
    public String pageHome(Model model) {

        model.addAttribute("nome",
                "Zezin da Silva");
        return "home";
    }
}
