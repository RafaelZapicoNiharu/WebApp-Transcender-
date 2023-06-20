package br.com.rafael.Transcender.controller;

import br.com.rafael.Transcender.configuration.user.UserLogado;
import br.com.rafael.Transcender.model.Empresa;
import br.com.rafael.Transcender.model.Pessoa;
import br.com.rafael.Transcender.model.Vaga;
import br.com.rafael.Transcender.service.UserService;
import br.com.rafael.Transcender.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = {"/company"})
public class EmpresaController {

    @Autowired
    private VagaService vServ;
    @Autowired
    private UserService uServ;

    @GetMapping("/criarvagas")
    public String pageCriaVagas(Model model) {

        return "cadastroVaga";
    }
    @GetMapping("/minhasvagas") // isso aqui ta funcionando direito
    public String pageMyVagas(Model model, Authentication auth){

        //aqui pega o login da empresa logada
        String loginEmpresaLogada = ((UserLogado) auth.getPrincipal()).getUser().getLogin();

        //aqui pega as vagas de acordo com esse login
        List<Vaga> vagas = vServ.getMyVagasCompany((Empresa) uServ.loadUserByUsername(loginEmpresaLogada));

        model.addAttribute("vagas",vagas); // bota elas no model
        return "vaga"; // aqui entra na pagina, onde vamos utilizar
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
