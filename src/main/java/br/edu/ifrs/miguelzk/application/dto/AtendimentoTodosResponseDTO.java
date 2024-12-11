package br.edu.ifrs.miguelzk.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoTodosResponseDTO {

  private Long id;
  private Date dataAtendimento;
  private String relatoTutor;
  private String exameClinico;
  private String avaliacaoExames;
  private String diagnPresuntivo;
  private String diagnConclusivo;
  private String procedimentoRealizado;
  private String tratamentoInstituido;
  private String observarProxConsulta;
  private Boolean registroAtivo;
//  private List<DateJavaType> validacaoPelosTutores;

  private AnimalResponseDTO animal;
  private Set<UsuarioSemRolesResponseDTO> usuariosDTO; // nomes de usuários
  private Set<MedVetResponseDTO> medVetsDTO; // nomes de medVets

}
