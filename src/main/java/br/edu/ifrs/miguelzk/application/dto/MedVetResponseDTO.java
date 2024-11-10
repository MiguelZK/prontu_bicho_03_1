package br.edu.ifrs.miguelzk.application.dto;

import java.util.Set;

import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import lombok.Data;

@Data
public class MedVetResponseDTO {

/*
  // CAMPOS DE USUARIO - PARA NÃO USAR HERANÇA EM DTOS
  private Long idUsuario;
  private String userName;
  private String role;
*/

  private String nomeCompleto;
  private int crmv;
  private String especialidade;
}
