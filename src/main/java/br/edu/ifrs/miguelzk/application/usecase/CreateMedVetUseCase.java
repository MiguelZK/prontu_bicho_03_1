package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.MedVetRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;

public interface CreateMedVetUseCase {

  MedVetResponseDTO execute(MedVetRequestDTO request);

}
