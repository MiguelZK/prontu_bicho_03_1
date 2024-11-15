package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;

import java.util.List;

public interface FindMedVetUseCase {

  MedVetResponseDTO execute(Long id);

  MedVetResponseDTO executeByCrmv(Long crmv);

  List<MedVetResponseDTO> execute(String userName);

  List<MedVetResponseDTO> executeByNome(String nomeCompleto);

  List<MedVetResponseDTO> execute();

}
