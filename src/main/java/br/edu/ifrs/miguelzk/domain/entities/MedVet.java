package br.edu.ifrs.miguelzk.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class MedVet extends Usuario {

    @Column(nullable = false)
    private Long crmv;

    private String especialidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MedVet medVet = (MedVet) o;
        return crmv == medVet.crmv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), crmv);
    }
}
