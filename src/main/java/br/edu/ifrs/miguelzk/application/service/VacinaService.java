package br.edu.ifrs.miguelzk.application.service;

import br.edu.ifrs.miguelzk.application.dto.VacinaRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VacinaResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.VacinaResponseSemAnimalDTO;
import br.edu.ifrs.miguelzk.application.usecase.VacinaUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class VacinaService {

  private final VacinaUseCase vacinaUseCase;

  @Inject
  private UsuarioService usuarioService;

  @Inject
  private AnimalService animalService;

  @Inject
  public VacinaService(VacinaUseCase vacinaUseCase) {
    this.vacinaUseCase = vacinaUseCase;
  }

  @Transactional
  public VacinaResponseDTO create(VacinaRequestDTO request) {
    return vacinaUseCase.createVacina(request);
  }

  public List<VacinaResponseDTO> findVacinaAll() {
    return vacinaUseCase.listAllVacinas();
  }

  public VacinaResponseDTO findVacinaById(Long id) {
    try {
      return vacinaUseCase.findVacinaById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  public Set<VacinaResponseSemAnimalDTO> carteiraDeVacinacao(Long idAnimal) {
    return vacinaUseCase.carteiraDeVacinacao(idAnimal);
  }

  @Transactional
  public VacinaResponseDTO updateVacina(Long id, VacinaRequestDTO request) {
    return vacinaUseCase.updateVacina(id, request);
  }

  @Transactional
  public void deleteVacinaById(Long id) {
    vacinaUseCase.deleteVacina(id);
  }

}
