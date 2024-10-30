package br.edu.ifrs.miguelzk.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class MedVet extends Usuario {

    @Column(nullable = false)
    private int cmrv;

    private String especialidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MedVet medVet = (MedVet) o;
        return cmrv == medVet.cmrv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cmrv);
    }
}
