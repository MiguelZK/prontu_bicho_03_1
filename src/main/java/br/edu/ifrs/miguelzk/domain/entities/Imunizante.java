package br.edu.ifrs.miguelzk.domain.entities;

import br.edu.ifrs.miguelzk.domain.enums.TipoImunizante;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
/*@AllArgsConstructor
@NoArgsConstructor*/
public class Imunizante extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long idImunizante;

    private String nome;
    private TipoImunizante tipoImunizante;
    private String marca;
    private String lote;
    private Boolean importada;
    private Boolean registroAtivo;

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

/*    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idAnimal")
    private Animal animal;*/

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Imunizante imunizante)) return false;
        return Objects.equals(idImunizante, imunizante.idImunizante)
                && Objects.equals(nome, imunizante.nome)
                && Objects.equals(tipoImunizante, imunizante.tipoImunizante)
                && Objects.equals(marca, imunizante.marca)
                && Objects.equals(lote, imunizante.lote)
                && Objects.equals(importada, imunizante.importada)
                && Objects.equals(dataFabricacao, imunizante.dataFabricacao)
                && Objects.equals(dataValidade, imunizante.dataValidade)
                && Objects.equals(dataAplicacao, imunizante.dataAplicacao)
                && Objects.equals(dataCadastro, imunizante.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idImunizante, nome, tipoImunizante, marca, lote, importada, dataFabricacao
                , dataValidade, dataAplicacao, dataCadastro);
    }
}
