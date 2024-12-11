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
public class AtendimentoParaImunizanteResponseDTO {

  private Long id;
  private Date dataAtendimento;
  private Set<UsuarioResponseDTO> usuariosDTO; // nomes de usuários
  private Set<MedVetResponseDTO> medVetsDTO; // nomes de medVets

}
