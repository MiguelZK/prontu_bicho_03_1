package br.edu.ifrs.miguelzk.application.dto;

import java.util.Date;
import java.util.Set;

//import org.hibernate.type.descriptor.java.DateJavaType;

import lombok.Data;

@Data
public class AtendimentoRequestDTO {
    private Date dataAtendimento;
    private String relatoTutor;
    private String exameClinico;
    private String avaliacaoExames;
    private String diagnPresuntivo;
    private String diagnConclusivo;
    private String procedimentoRealizado;
    private String tratamentoInstituido;
    private String observarProxConsulta;
//    private List<DateJavaType> validacaoPelosTutores;

    private Long idAnimal;
    private Set<Long> idUsuarios;
    private Set<Long> crmvMedVets;

}
