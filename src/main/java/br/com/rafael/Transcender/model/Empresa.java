package br.com.rafael.Transcender.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Empresa extends Usuario{

    @OneToMany
    @JoinColumn(name = "empresa")
    private List<Vaga> vagasEmpresa;

    @OneToMany
    @JoinColumn(name = "empresa")
    private List<Avaliacao> avaEmpresa;

}
