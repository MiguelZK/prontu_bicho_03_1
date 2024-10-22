package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.usecase.DeleteAnimalUseCase;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;

public class DeleteAnimalUseCaseImpl implements DeleteAnimalUseCase {

  private AnimalRepository animalRepository;

  public DeleteAnimalUseCaseImpl(AnimalRepository animalRepository) {
    this.animalRepository = animalRepository;
  }

  @Override
  public void execute(Long id) {
    animalRepository.deleteAnimalById(id);
  }

}
