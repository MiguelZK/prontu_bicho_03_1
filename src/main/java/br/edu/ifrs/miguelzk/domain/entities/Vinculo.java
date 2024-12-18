package br.edu.ifrs.miguelzk.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Vinculo extends PanacheEntity {

    private String nomeAnimal;
//    private Date inicioVinculo;
//    private Date fimVinculo;
//    private Set<Animal> animais;

    @ToString.Exclude
    @JsonBackReference
    @JoinColumn(name = "idAnimal")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Animal animal;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

}
