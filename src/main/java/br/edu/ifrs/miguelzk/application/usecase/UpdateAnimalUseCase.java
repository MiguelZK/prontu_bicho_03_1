package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.AnimalRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;

public interface UpdateAnimalUseCase {
    AnimalResponseDTO execute(Long id, AnimalRequestDTO dto);
}
