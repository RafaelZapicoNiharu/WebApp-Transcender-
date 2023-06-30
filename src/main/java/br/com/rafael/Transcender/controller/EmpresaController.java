package br.com.rafael.Transcender.controller;

import br.com.rafael.Transcender.configuration.user.UserLogado;
import br.com.rafael.Transcender.model.*;
import br.com.rafael.Transcender.service.UserService;
import br.com.rafael.Transcender.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('EMPRESA')")
@RequestMapping(path = {"/company"})
public class EmpresaController {

    @Autowired
    private VagaService vServ;
    @Autowired
    private UserService uServ;

    @GetMapping("/criarvagas")
    public String pageCriaVagas(Model model, Authentication auth) {

        model.addAttribute("vaga",new Vaga());

        return "cadastroVaga";
    }
    @PostMapping("/criarvagas/save")
    public String pageSaveVaga(@ModelAttribute Vaga vaga,
                                     Model model, Authentication auth){

        int loginUserLogada = ((UserLogado) auth.getPrincipal()).getUser().getId();
        vaga.setEmpresa((Empresa) uServ.buscarUsuarioId(loginUserLogada));

        vServ.saveHabilidade(vaga); //aqui utiliza o service para poder salvar
        // a habilidade passada pelo formulario para o banco, atraves do save

        return "redirect:/mod/habilidades";
    }
    @GetMapping("/minhasvagas") // isso aqui ta funcionando direito
    public String pageMyVagas(Model model, Authentication auth){

        //aqui pega o login da empresa logada
        int loginEmpresaLogada = ((UserLogado) auth.getPrincipal()).getUser().getId();

        //aqui pega as vagas de acordo com esse login
        List<Vaga> vagas = vServ.getMyVagasCompany(loginEmpresaLogada);

        model.addAttribute("vagas",vagas); // bota elas no model
        return "vaga"; // aqui entra na pagina, onde vamos utilizar
    }
    @GetMapping("/minhasvagas/deletar/{id}")
    public String pageApagaHabilidade( @PathVariable("id") int id, Authentication auth){

        vServ.apagaHabilidade(id);
        return "redirect:/company/minhasvagas";
    }
    @GetMapping("/perfil/editar/{id}")
    public String pageEditarUsuario(Model model, @PathVariable("id") int id, Authentication auth){

        Usuario u = uServ.buscarUsuarioId(id);
        model.addAttribute("usuario",u);

        return "cadastro";
    }


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

}
