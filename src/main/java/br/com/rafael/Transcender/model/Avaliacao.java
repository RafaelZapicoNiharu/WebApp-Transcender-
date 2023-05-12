package br.com.rafael.Transcender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Avaliacao {
    private ENotaEmpresa ava;

    @ManyToOne(optional = true)
    private Empresa empresa;
    private String comentario;
}
