package br.edu.ifrs.miguelzk.application.dto;

import java.util.Set;

import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import lombok.Data;

@Data
public class MedVetResponseDTO {
  private int crmv;
  private String especialidade;
  private Set<Atendimento> atendimentos;
}
