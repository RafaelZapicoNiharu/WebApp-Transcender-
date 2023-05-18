package br.com.rafael.Transcender.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Data //cria os get e sets
@NoArgsConstructor //construtor vazio
@AllArgsConstructor //construtor com tudo
@SuperBuilder
@MappedSuperclass
@DiscriminatorColumn(name = "tipo")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30,nullable = false)
    private String nome;
    @Column(length = 30,nullable = false) 
    private String login;
    @Column(length = 30,nullable = false)
    private String email;
    @Column(length = 30,nullable = false)
    private String documento;
    @Column(length = 30,nullable = false)
    private String telefone;
    @Column(length = 120,nullable = false)
    private String endereco;
    @Column(length = 120,nullable = false)
    private String descricao;
    @Column(length = 120,nullable = false)
    private String senha;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimoAcesso;
}
