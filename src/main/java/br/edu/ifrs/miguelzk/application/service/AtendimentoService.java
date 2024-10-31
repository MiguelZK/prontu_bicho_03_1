package br.edu.ifrs.miguelzk.application.service;

import java.util.List;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateAtendimentoUseCase;
import br.edu.ifrs.miguelzk.application.usecase.DeleteAtendimentoUseCase;
import br.edu.ifrs.miguelzk.application.usecase.FindAtendimentoUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtendimentoService {

  private final CreateAtendimentoUseCase createAtendimentoUseCase;
  private final FindAtendimentoUseCase findAtendimentoUseCase;
  private final DeleteAtendimentoUseCase deleteAtendimentoUseCase;

  @Inject
  public AtendimentoService(CreateAtendimentoUseCase createAtendimentoUseCase, FindAtendimentoUseCase findAtendimentoUseCase,
      DeleteAtendimentoUseCase deleteAtendimentoUseCase) {
    this.createAtendimentoUseCase = createAtendimentoUseCase;
    this.findAtendimentoUseCase = findAtendimentoUseCase;
    this.deleteAtendimentoUseCase = deleteAtendimentoUseCase;
  }

  @Transactional
  public AtendimentoResponseDTO createAtendimento(AtendimentoRequestDTO request) {
    return createAtendimentoUseCase.execute(request);
  }

  public AtendimentoResponseDTO findAtendimentoById(Long id) {
    try {
      return findAtendimentoUseCase.execute(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Atendimento não encontrado");
    }
  }

  public List<AtendimentoResponseDTO> findAtendimentoAll(){
    return findAtendimentoUseCase.execute();
  }

  @Transactional
  public void deleteAtendimentoById(Long id) {
    deleteAtendimentoUseCase.execute(id);
  }

}
