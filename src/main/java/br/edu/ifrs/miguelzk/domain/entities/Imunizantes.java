package br.edu.ifrs.miguelzk.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class Imunizantes {

    private String nome;
    private String marca;
    private String lote;
    private Date dataFabricacao;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAplicacao;

    @CreationTimestamp
    private Date dataCadastro;
}
