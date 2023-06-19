package br.com.rafael.Transcender;

import br.com.rafael.Transcender.model.Administrador;
import br.com.rafael.Transcender.model.Empresa;
import br.com.rafael.Transcender.model.Pessoa;
import br.com.rafael.Transcender.model.dao.AdministradorDao;
import br.com.rafael.Transcender.model.dao.EmpresaDao;
import br.com.rafael.Transcender.model.dao.PessoaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

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

		pessoas.save(pessoa);
		System.out.println("Pessoa salva :: "+pessoa.getId());

	}
}
