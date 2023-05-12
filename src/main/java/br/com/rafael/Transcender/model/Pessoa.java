package br.com.rafael.Transcender.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("P")
public class Pessoa extends Usuario {

    @ManyToMany
    private List<Habilidade> minhasHabilidades;
    private String AreaInteresse;

    @ManyToMany
    @JoinColumn(name = "candidatos")
        private List<Vaga> minhasVagas;

}
