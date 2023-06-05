package br.com.rafael.Transcender.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vaga {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String linkVaga;

    @Column(length =200,nullable = false)
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ETipoVaga tipo;
    @ManyToMany
    private List<Habilidade> habilidadesRecomendadas;
    @ManyToOne (optional = true)
    private Empresa empresa;

    @ManyToMany
    @JoinColumn(name = "minhasVagas")
    private List<Pessoa> candidatos;


}
