package br.edu.ifrs.miguelzk.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
/*@AllArgsConstructor
@NoArgsConstructor*/
public class Vacina extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long idVacina;

    private String nome;
    private String marca;
    private String lote;
    private Boolean importada;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFabricacao;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataValidade;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAplicacao;

    @CreationTimestamp
    private Date dataCadastro;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idAnimal")
    private Animal animal;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vacina vacina)) return false;
        return Objects.equals(idVacina, vacina.idVacina) && Objects.equals(nome, vacina.nome) && Objects.equals(marca, vacina.marca) && Objects.equals(lote, vacina.lote) && Objects.equals(importada, vacina.importada) && Objects.equals(dataFabricacao, vacina.dataFabricacao) && Objects.equals(dataValidade, vacina.dataValidade) && Objects.equals(dataAplicacao, vacina.dataAplicacao) && Objects.equals(dataCadastro, vacina.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVacina, nome, marca, lote, importada, dataFabricacao, dataValidade, dataAplicacao, dataCadastro);
    }
}
