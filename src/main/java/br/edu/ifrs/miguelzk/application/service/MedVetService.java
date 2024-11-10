package br.edu.ifrs.miguelzk.application.service;

import br.edu.ifrs.miguelzk.application.dto.MedVetRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateMedVetUseCase;
import br.edu.ifrs.miguelzk.application.usecase.DeleteMedVetUseCase;
import br.edu.ifrs.miguelzk.application.usecase.FindMedVetUseCase;
import br.edu.ifrs.miguelzk.application.usecase.UpdateMedVetUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class MedVetService {

  private final CreateMedVetUseCase createMedVetUseCase;
  private final FindMedVetUseCase findMedVetUseCase;
  private final DeleteMedVetUseCase deleteMedVetUseCase;
  private final UpdateMedVetUseCase updateMedVetUseCase;

  @Inject
  public MedVetService(CreateMedVetUseCase createMedVetUseCase, FindMedVetUseCase findMedVetUseCase
          , DeleteMedVetUseCase deleteMedVetUseCase, UpdateMedVetUseCase updateMedVetUseCase) {
    this.createMedVetUseCase = createMedVetUseCase;
    this.findMedVetUseCase = findMedVetUseCase;
    this.deleteMedVetUseCase = deleteMedVetUseCase;
    this.updateMedVetUseCase = updateMedVetUseCase;
  }

  @Transactional
  public MedVetResponseDTO create(MedVetRequestDTO request) {
    return createMedVetUseCase.execute(request);
  }

  @Transactional
  public MedVetResponseDTO updateMedVet(Long id, MedVetRequestDTO request) {
    return updateMedVetUseCase.execute(id, request);
  }

  public List<MedVetResponseDTO> findMedVetAll() {
    return findMedVetUseCase.execute();
  }

  public MedVetResponseDTO findMedVetById(Long id) {
    try {
      return findMedVetUseCase.execute(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Usuário não encontrado");
    }
  }

  public List<MedVetResponseDTO> findMedVetByName(String nomeMedVet) {
    return findMedVetUseCase.execute(nomeMedVet);
  }

  @Transactional
  public void deleteMedVetById(Long id) {
    try {
      deleteMedVetUseCase.execute(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Usuário não encontrado");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
