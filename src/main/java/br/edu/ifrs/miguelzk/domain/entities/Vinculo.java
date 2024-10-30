package br.edu.ifrs.miguelzk.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Vinculo extends PanacheEntity {

    private String nomeAnimal;
//    private Date inicioVinculo;
//    private Date fimVinculo;
//    private Set<Animal> animais;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Animal animal;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;

}
