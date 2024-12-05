package br.edu.ifrs.miguelzk.application.service;

import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VinculoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateVinculoUseCase;
import br.edu.ifrs.miguelzk.application.usecase.DeleteVinculoUseCase;
import br.edu.ifrs.miguelzk.application.usecase.FindVinculoUseCase;
import br.edu.ifrs.miguelzk.application.usecase.UpdateVinculoUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.openapi.annotations.links.Link;

import java.util.List;

@ApplicationScoped
public class VinculoService {

  private final CreateVinculoUseCase createVinculoUseCase;
  private final FindVinculoUseCase findVinculoUseCase;
  private final DeleteVinculoUseCase deleteVinculoUseCase;
  private final UpdateVinculoUseCase updateVinculoUseCase;

  @Inject
  private UsuarioService usuarioService;

  @Inject
  private AnimalService animalService;

  @Inject
  public VinculoService(CreateVinculoUseCase createVinculoUseCase, FindVinculoUseCase findVinculoUseCase
          , DeleteVinculoUseCase deleteVinculoUseCase, UpdateVinculoUseCase updateVinculoUseCase) {
    this.createVinculoUseCase = createVinculoUseCase;
    this.findVinculoUseCase = findVinculoUseCase;
    this.deleteVinculoUseCase = deleteVinculoUseCase;
    this.updateVinculoUseCase = updateVinculoUseCase;
  }

  @Transactional
  public VinculoResponseDTO create(VinculoRequestDTO request) {
    return createVinculoUseCase.execute(request);
  }

  @Transactional
  public VinculoResponseDTO updateVinculo(Long id, VinculoRequestDTO request) {
    return updateVinculoUseCase.execute(id, request);
  }

  public List<VinculoResponseDTO> findVinculoAll() {
    return findVinculoUseCase.execute();
  }

  public VinculoResponseDTO findVinculoById(Long id) {
    try {
      return findVinculoUseCase.execute(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  public List<VinculoResponseDTO> findVinculoByName(String nomeVinculo) {
    return findVinculoUseCase.execute(nomeVinculo);
  }

  @Transactional
  public void deleteVinculoById(Long id) {
    deleteVinculoUseCase.execute(id);
  }

}
