package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VinculoResponseDTO;

public interface UpdateVinculoUseCase {
    VinculoResponseDTO execute(VinculoRequestDTO dto);
}
