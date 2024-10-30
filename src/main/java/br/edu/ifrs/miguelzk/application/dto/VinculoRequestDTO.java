package br.edu.ifrs.miguelzk.application.dto;

import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class VinculoRequestDTO extends PanacheEntity {

    private String nomeAnimal;
//    private Date inicioVinculo;
//    private Date fimVinculo;
//    private Set<Animal> animais;

    private Animal animal;
    private Usuario usuario;

}
