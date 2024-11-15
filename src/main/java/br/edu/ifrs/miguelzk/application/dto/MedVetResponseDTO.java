package br.edu.ifrs.miguelzk.application.dto;

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
  private Long crmv;
  private String especialidade;
}
