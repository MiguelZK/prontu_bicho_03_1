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

  private final ImunizanteUseCase vacinaUseCase;

  @Inject
  private UsuarioService usuarioService;

  @Inject
  private AnimalService animalService;

  @Inject
  public ImunizanteService(ImunizanteUseCase vacinaUseCase) {
    this.vacinaUseCase = vacinaUseCase;
  }

  @Transactional
  public ImunizanteComAnimalResponseDTO create(ImunizanteRequestDTO request) {
    return vacinaUseCase.createImunizante(request);
  }

  public List<ImunizanteTodasResponseDTO> findImunizanteAll() {
    return vacinaUseCase.listAllImunizantes();
  }

  public ImunizanteTodasResponseDTO findImunizanteById(Long id) {
    try {
      return vacinaUseCase.findImunizanteById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

/*  public Set<ImunizanteResponseDTO> carteiraDeImunizantecao(Long idAnimal) {
    return vacinaUseCase.carteiraDeImunizantecao(idAnimal);
  }*/

  @Transactional
  public ImunizanteComAnimalResponseDTO updateImunizante(Long id, ImunizanteRequestDTO request) {
    return vacinaUseCase.updateImunizante(id, request);
  }

  @Transactional
  public void deleteImunizanteById(Long id) {
    vacinaUseCase.deleteImunizante(id);
  }

}
