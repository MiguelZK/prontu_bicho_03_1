package br.edu.ifrs.miguelzk.application.service;

import java.util.List;
import br.edu.ifrs.miguelzk.application.dto.AnimalRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateAnimalUseCase;
import br.edu.ifrs.miguelzk.application.usecase.DeleteAnimalUseCase;
import br.edu.ifrs.miguelzk.application.usecase.FindAnimalUseCase;
import br.edu.ifrs.miguelzk.application.usecase.UpdateAnimalUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AnimalService {

  private final CreateAnimalUseCase createAnimalUseCase;

  private final FindAnimalUseCase findAnimalUseCase;

  private final DeleteAnimalUseCase deleteAnimalUseCase;

  private final UpdateAnimalUseCase updateAnimalUseCase;

  @Inject
  public AnimalService(CreateAnimalUseCase createAnimalUseCase, FindAnimalUseCase findAnimalUseCase,
                       DeleteAnimalUseCase deleteAnimalUseCase, UpdateAnimalUseCase updateAnimalUseCase) {
    this.createAnimalUseCase = createAnimalUseCase;
    this.findAnimalUseCase = findAnimalUseCase;
    this.deleteAnimalUseCase = deleteAnimalUseCase;
    this.updateAnimalUseCase = updateAnimalUseCase;
  }

  @Transactional
  public AnimalResponseDTO createAnimal(AnimalRequestDTO request) {
    return createAnimalUseCase.execute(request);
  }

  @Transactional
  public AnimalResponseDTO updateAnimal(AnimalRequestDTO request) {
    return updateAnimalUseCase.execute(request);
  }

  public List<AnimalResponseDTO> findAnimalAll(){
    return findAnimalUseCase.execute();
  }

  public AnimalResponseDTO findAnimalById(Long id) {
    try {
      return findAnimalUseCase.execute(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Animal não encontrado");
    }
  }

  public List<AnimalResponseDTO> findAnimalByName(String nomeAnimal) {
    return findAnimalUseCase.execute(nomeAnimal);
  }

  @Transactional
  public void deleteAnimalById(Long id) {
    deleteAnimalUseCase.execute(id);
  }

}
