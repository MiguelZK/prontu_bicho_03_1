package br.edu.ifrs.miguelzk.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import br.edu.ifrs.miguelzk.domain.enums.PorteCachorro;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Animal extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long idAnimal;

    private String nomeAnimal;
/*    private String idMicrochip;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @CreationTimestamp
    private Date dataCadastro;

    @Temporal(TemporalType.DATE)
    private Date dataFalecimento;

    private Boolean idadeAproximada;
    private Boolean comunitario;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "idEspecie")
    private Especie especie;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "idRaca")
    private Raca raca;

    @Column(nullable = false)
    private String temperamento;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private String sinaisParticulares;

    @Column(nullable = false)
    private String tipoPelagem;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private String descricao;*/


    @Enumerated(EnumType.STRING)
    private PorteCachorro porteCachorro;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Usuario.class)
    @JoinTable( name="animal_usuario",
            joinColumns={ @JoinColumn(name="idAnimal")},
            inverseJoinColumns={@JoinColumn(name="idUsuario")})
    private Set<Usuario> usuarios = new HashSet<>();

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "animal")
    private Set<Atendimento> atendimentos = new HashSet<>();

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "animal")
    private Set<Vinculo> vinculos = new HashSet<>();

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "animal", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Vacina> vacinas = new HashSet<>();

    public Animal(String nomeAnimal, String porteCachorro) {
        this.nomeAnimal = nomeAnimal;
        this.porteCachorro = PorteCachorro.valueOf(porteCachorro);
        this.usuarios = new HashSet<>();
        this.atendimentos = new HashSet<>();
        this.vinculos = new HashSet<>();
    }

    public Animal() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(idAnimal, animal.idAnimal);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idAnimal);
    }
}
