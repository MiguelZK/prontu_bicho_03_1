package br.edu.ifrs.miguelzk.domain.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
public class Atendimento extends PanacheEntityBase {
//    private DateJavaType dataAtendimento;

    @Id
    @GeneratedValue
    private Long idAtendimento;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAtendimento;

    @CreationTimestamp
    private Date dataCadastro;

    private String relatoTutor;
    private String exameClinico;
    private String avaliacaoExames;
    private String diagnPresuntivo;
    private String diagnConclusivo;
    private String procedimentoRealizado;
    private String tratamentoInstituido;
    private String observarProxConsulta;
//    private List<DateJavaType> validacaoPelosTutores;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idAnimal")
    private Animal animal;

    @ToString.Exclude
    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "atendim_usuarios",
            joinColumns = {@JoinColumn(name = "idAtendimento")},
            inverseJoinColumns = {@JoinColumn(name = "idUsuario")})
    private Set<Usuario> usuarios = new HashSet<>();

    @ToString.Exclude
    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "atendim_medvets",
            joinColumns = {@JoinColumn(name = "idAtendimento")},
            inverseJoinColumns = {@JoinColumn(name = "crmv")})
    private Set<MedVet> medVets = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendimento that = (Atendimento) o;
        return Objects.equals(idAtendimento, that.idAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idAtendimento);
    }
}
