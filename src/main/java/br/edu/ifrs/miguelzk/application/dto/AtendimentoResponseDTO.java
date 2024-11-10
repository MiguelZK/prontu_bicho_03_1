package br.edu.ifrs.miguelzk.application.dto;

import java.util.List;
import java.util.Set;

//import org.hibernate.type.descriptor.java.DateJavaType;

import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import lombok.Data;

@Data
public class AtendimentoResponseDTO {

  private Long id;
  //  private DateJavaType dataAtendimento;
  private String relatoTutor;
  private String exameClinico;
  private String avaliacaoExames;
  private String diagnPresuntivo;
  private String diagnConclusivo;
  private String procedimentoRealizado;
  private String tratamentoInstituido;
  private String observarProxConsulta;
//  private List<DateJavaType> validacaoPelosTutores;

  private Animal animal;
//  private Set<Usuario> usuarios;
//  private Set<MedVet> medVets;

}
