package br.com.rafael.Transcender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/mod"})
public class AdminController {

    @GetMapping("/home")
    public String pageHome(Model model) {

        return "inicioAdmin";
    }
}
