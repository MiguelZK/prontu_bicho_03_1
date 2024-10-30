package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VinculoResponseDTO;

public interface CreateVinculoUseCase {

  VinculoResponseDTO execute(VinculoRequestDTO request);

}
