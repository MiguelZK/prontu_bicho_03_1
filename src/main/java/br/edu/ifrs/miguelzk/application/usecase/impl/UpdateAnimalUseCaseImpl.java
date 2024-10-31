package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.AnimalRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateAnimalUseCase;
import br.edu.ifrs.miguelzk.application.usecase.UpdateAnimalUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

public class UpdateAnimalUseCaseImpl implements UpdateAnimalUseCase {

  private final ModelMapper modelMapper;
  private AnimalRepository animalRepository;

  public UpdateAnimalUseCaseImpl(AnimalRepository animalRepository, ModelMapper modelMapper) {
    this.animalRepository = animalRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public AnimalResponseDTO execute(Long id, AnimalRequestDTO dto) {
    Animal animalExistente = animalRepository.findAnimalById(id);
    if (animalExistente == null) {
      throw new EntityNotFoundException("Usuário não encontrado");
    }
    modelMapper.map(dto, animalExistente);
    Animal animalSaved = animalRepository.update(animalExistente);
    return modelMapper.map(animalSaved, AnimalResponseDTO.class);
  }

}
