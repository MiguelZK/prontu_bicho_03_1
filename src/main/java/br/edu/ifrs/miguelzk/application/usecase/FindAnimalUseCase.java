package br.edu.ifrs.miguelzk.application.usecase;

import java.util.List;

import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;

public interface FindAnimalUseCase {

  AnimalResponseDTO execute(Long id);

  List<AnimalResponseDTO> execute(String nomeAnimal);

  List<AnimalResponseDTO> execute();

}
