package br.edu.ifrs.miguelzk.application.dto;

import java.util.List;
import java.util.Set;

//import org.hibernate.type.descriptor.java.DateJavaType;

import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import lombok.Data;

@Data
public class MedVetRequestDTO {
//    private DateJavaType dataNascimento;
//    private DateJavaType dataCadastro;
    private int crmv;
    private String especialidade;
    private Set<Atendimento> atendimentos;
}
