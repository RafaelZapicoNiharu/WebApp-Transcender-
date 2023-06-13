package br.com.rafael.Transcender;

import br.com.rafael.Transcender.model.Administrador;
import br.com.rafael.Transcender.model.dao.AdministradorDao;
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
				.documento("1234")
				.telefone("1234")
				.descricao("moderador da transcender")
				.certificacao("55141")
				.senha(pass.encode("123"))
				.build();
		//salvando ele no banco através da dao
		admins.save(mod); //funcionou certinho

		System.out.println("Admin salvo :: "+mod.getId());

	}
}
