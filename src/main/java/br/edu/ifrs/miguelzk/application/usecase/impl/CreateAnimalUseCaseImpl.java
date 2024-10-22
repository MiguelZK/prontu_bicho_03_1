package br.edu.ifrs.miguelzk.application.usecase.impl;

import org.modelmapper.ModelMapper;
import br.edu.ifrs.miguelzk.application.dto.AnimalRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateAnimalUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;

public class CreateAnimalUseCaseImpl implements CreateAnimalUseCase {

  private final ModelMapper modelMapper;
  private AnimalRepository animalRepository;

  public CreateAnimalUseCaseImpl(AnimalRepository animalRepository, ModelMapper modelMapper) {
    this.animalRepository = animalRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public AnimalResponseDTO execute(AnimalRequestDTO dto) {
    Animal animal = modelMapper.map(dto, Animal.class);
    Animal animalSaved = animalRepository.save(animal);
    return modelMapper.map(animalSaved, AnimalResponseDTO.class);
  }

}
