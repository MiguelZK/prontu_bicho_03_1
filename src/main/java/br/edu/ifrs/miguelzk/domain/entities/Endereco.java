package br.edu.ifrs.miguelzk.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Endereco extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Integer idEndereco;

    private String logradouro;
    private int numero;
    private String complemento;
    private String cidade;
    private String estado;
    private int cep;
    private String tipo;

/*    @ToString.Exclude
@ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "idUsuario")
    private Usuario usuario;*/
}
