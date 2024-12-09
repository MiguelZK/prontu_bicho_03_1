package br.edu.ifrs.miguelzk.application.service;

import br.edu.ifrs.miguelzk.application.dto.VacinaRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VacinaComAnimalResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.VacinaTodasResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.VacinaUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

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
  public VacinaComAnimalResponseDTO create(VacinaRequestDTO request) {
    return vacinaUseCase.createVacina(request);
  }

  public List<VacinaTodasResponseDTO> findVacinaAll() {
    return vacinaUseCase.listAllVacinas();
  }

  public VacinaTodasResponseDTO findVacinaById(Long id) {
    try {
      return vacinaUseCase.findVacinaById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

/*  public Set<VacinaResponseDTO> carteiraDeVacinacao(Long idAnimal) {
    return vacinaUseCase.carteiraDeVacinacao(idAnimal);
  }*/

  @Transactional
  public VacinaComAnimalResponseDTO updateVacina(Long id, VacinaRequestDTO request) {
    return vacinaUseCase.updateVacina(id, request);
  }

  @Transactional
  public void deleteVacinaById(Long id) {
    vacinaUseCase.deleteVacina(id);
  }

}
