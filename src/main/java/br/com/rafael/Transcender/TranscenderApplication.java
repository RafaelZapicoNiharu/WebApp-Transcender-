package br.com.rafael.Transcender;

import br.com.rafael.Transcender.model.*;
import br.com.rafael.Transcender.model.dao.AdministradorDao;
import br.com.rafael.Transcender.model.dao.EmpresaDao;
import br.com.rafael.Transcender.model.dao.PessoaDao;
import br.com.rafael.Transcender.model.dao.VagaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TranscenderApplication implements ApplicationRunner {

	public static void main(String[] args) { SpringApplication.run(TranscenderApplication.class, args); }

	@Autowired
	AdministradorDao admins;
	// no me sistema, o usuario administrador vai ser pré-criado pela equipe do aplicativo
	// com finalidade unica de gerenciar as habilidades disponíveis no sistema
	@Autowired
	EmpresaDao empresas;

	@Autowired
	PessoaDao pessoas;
	@Autowired
	VagaDao vagas;
	@Autowired
	PasswordEncoder pass;
	//como nos estamos utilizando senha criptografada, é necessario ter o passencoder aqui
	//pra podermos criar usuarios no run, pois o banco nao aceitaria senhas normais
	@Override
	public void run(ApplicationArguments args) throws Exception {


		//criando o administrador do sistema
		Administrador mod = Administrador.builder()
				.nome("Niharu")
				.email("rafaelzapico.ti@gmail.com")
				.login("niharu")
				.documento("12345678910")
				.telefone("1234")
				.descricao("moderador da transcender")
				.certificacao("55141")
				.senha(pass.encode("123"))
				.build();
		//salvando ele no banco através da dao
		admins.save(mod); //funcionou certinho

		System.out.println("Admin salvo :: "+mod.getId());

		//criando empresa para testes
		Empresa empresa = Empresa.builder()
				.nome("Prisma")
				.email("prisma@gmail.com")
				.login("prisma")
				.documento("123456789102")
				.telefone("1234")
				.descricao("prisma lunar")
				.senha(pass.encode("123"))
				.build();

		empresas.save(empresa);
		System.out.println("Empresa salva :: "+empresa.getId());

		//criando empresa para testes

		Pessoa pessoa = Pessoa.builder()
				.nome("Chang'e")
				.email("chang@gmail.com")
				.login("chang'e")
				.documento("12345678911")
				.telefone("1234")
				.descricao("deusa da lua")
				.senha(pass.encode("123"))
				.build();

		List<Pessoa> pessoinhas = new ArrayList<>();
		pessoinhas.add(pessoa);

		Vaga vaga =  Vaga.builder()
				.titulo("Arconte")
				.linkVaga("https://i.pinimg.com/564x/76/c0/4b/76c04bb9fecdd53566b5098c8e2094a5.jpg")
				.descricao("twink ventania")
				.tipo(ETipoVaga.SENIOR)
				.candidatos(pessoinhas)
				.empresa(empresa)
				.build();

		pessoas.save(pessoa);
		System.out.println("Pessoa salva :: "+pessoa.getId());

		vagas.save(vaga);

		System.out.println("Pessoas na vaga  salva :: "+vaga.getCandidatos().get(0).getNome());




	}
}
