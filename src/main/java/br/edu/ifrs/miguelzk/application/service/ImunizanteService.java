package br.edu.ifrs.miguelzk.application.service;

import br.edu.ifrs.miguelzk.application.dto.ImunizanteRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.ImunizanteComAnimalResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.ImunizanteTodasResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.ImunizanteUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class ImunizanteService {

  private final ImunizanteUseCase imunizanteUseCase;

  @Inject
  private UsuarioService usuarioService;

  @Inject
  private AnimalService animalService;

  @Inject
  public ImunizanteService(ImunizanteUseCase imunizanteUseCase) {
    this.imunizanteUseCase = imunizanteUseCase;
  }

  @Transactional
  public ImunizanteComAnimalResponseDTO create(ImunizanteRequestDTO request) {
    return imunizanteUseCase.createImunizante(request);
  }

  public List<ImunizanteTodasResponseDTO> findImunizanteAll() {
    return imunizanteUseCase.listAllImunizantes();
  }

  public ImunizanteTodasResponseDTO findImunizanteById(Long id) {
    try {
      return imunizanteUseCase.findImunizanteById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

/*  public Set<ImunizanteResponseDTO> carteiraDeVacinacao(Long idAnimal) {
    return vacinaUseCase.carteiraDeVacinacao(idAnimal);
  }*/

  @Transactional
  public ImunizanteComAnimalResponseDTO updateImunizante(Long id, ImunizanteRequestDTO request) {
    return imunizanteUseCase.updateImunizante(id, request);
  }

  @Transactional
  public void deleteImunizanteById(Long id) {
    imunizanteUseCase.deleteImunizante(id);
  }

}
