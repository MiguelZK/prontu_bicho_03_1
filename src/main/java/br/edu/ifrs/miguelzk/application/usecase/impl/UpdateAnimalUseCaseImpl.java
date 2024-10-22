package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.AnimalRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateAnimalUseCase;
import br.edu.ifrs.miguelzk.application.usecase.UpdateAnimalUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import org.modelmapper.ModelMapper;

public class UpdateAnimalUseCaseImpl implements UpdateAnimalUseCase {

  private final ModelMapper modelMapper;
  private AnimalRepository animalRepository;

  public UpdateAnimalUseCaseImpl(AnimalRepository animalRepository, ModelMapper modelMapper) {
    this.animalRepository = animalRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public AnimalResponseDTO execute(AnimalRequestDTO dto) {
    Animal animal = modelMapper.map(dto, Animal.class);
    Animal animalSaved = animalRepository.update(animal);
    return modelMapper.map(animalSaved, AnimalResponseDTO.class);
  }

}
