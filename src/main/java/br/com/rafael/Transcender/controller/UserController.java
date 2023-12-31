package br.com.rafael.Transcender.controller;

import br.com.rafael.Transcender.configuration.user.UserLogado;
import br.com.rafael.Transcender.model.*;
import br.com.rafael.Transcender.service.UserService;
import br.com.rafael.Transcender.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@PreAuthorize("hasRole('PESSOA')")  //somente os users com essa role poderam utilizar esse controller
@RequestMapping(path = {"/user"})
public class UserController {

    @Autowired
    private UserService uServ; //aqui eu estou pegando o user service

    @Autowired
    private VagaService vServ;



    @GetMapping("/perfil")
    public String pagePerfil(Model model, Authentication auth){


        model.addAttribute("nome",
                    ((UserLogado) auth.getPrincipal()).getUser().getNome());
        model.addAttribute("id",
                ((UserLogado) auth.getPrincipal()).getUser().getId());
        model.addAttribute("email",
                ((UserLogado) auth.getPrincipal()).getUser().getEmail());

        model.addAttribute("telefone",
                ((UserLogado) auth.getPrincipal()).getUser().getTelefone());
        model.addAttribute("descricao",
                ((UserLogado) auth.getPrincipal()).getUser().getDescricao());



        return "perfil";
    }
    @GetMapping("/perfil/editar/{id}")
    public String pageEditarUsuario(Model model,@PathVariable("id") int id, Authentication auth){

        Usuario u = uServ.buscarUsuarioId(id);
        model.addAttribute("usuario",u);

        return "cadastro";
    }


    @GetMapping("/minhasvagas") // isso aqui ta funcionando direito
    public String pageMyVagas(Model model, Authentication auth){

        //aqui pega o login da empresa logada
        int loginUserLogada = ((UserLogado) auth.getPrincipal()).getUser().getId();

        //aqui pega as vagas de acordo com esse login
        List<Vaga> vagas = vServ.getMyVagasUser(loginUserLogada);

        model.addAttribute("vagas",vagas); // bota elas no model
        return "vaga"; // aqui entra na pagina, onde vamos utilizar
    }

    @GetMapping("/procura")
    public String pageProcura(Model model) {

        return "procura";
    }



}
