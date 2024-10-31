package br.edu.ifrs.miguelzk.application.service;

import java.util.List;

import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateUsuarioUseCase;
import br.edu.ifrs.miguelzk.application.usecase.DeleteUsuarioUseCase;
import br.edu.ifrs.miguelzk.application.usecase.FindUsuarioUseCase;
import br.edu.ifrs.miguelzk.application.usecase.UpdateUsuarioUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioService {

  private final CreateUsuarioUseCase createUsuarioUseCase;
  private final FindUsuarioUseCase findUsuarioUseCase;
  private final DeleteUsuarioUseCase deleteUsuarioUseCase;
  private final UpdateUsuarioUseCase updateUsuarioUseCase;

  @Inject
  public UsuarioService(CreateUsuarioUseCase createUsuarioUseCase, FindUsuarioUseCase findUsuarioUseCase
          , DeleteUsuarioUseCase deleteUsuarioUseCase, UpdateUsuarioUseCase updateUsuarioUseCase) {
    this.createUsuarioUseCase = createUsuarioUseCase;
    this.findUsuarioUseCase = findUsuarioUseCase;
    this.deleteUsuarioUseCase = deleteUsuarioUseCase;
    this.updateUsuarioUseCase = updateUsuarioUseCase;
  }

  @Transactional
  public UsuarioResponseDTO create(UsuarioRequestDTO request) {
    return createUsuarioUseCase.execute(request);
  }

  @Transactional
  public UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO request) {
    return updateUsuarioUseCase.execute(id, request);
  }

  public List<UsuarioResponseDTO> findUsuarioAll() {
    return findUsuarioUseCase.execute();
  }

  public UsuarioResponseDTO findUsuarioById(Long id) {
    try {
      return findUsuarioUseCase.execute(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Usuário não encontrado");
    }
  }

  public List<UsuarioResponseDTO> findUsuarioByName(String nomeUsuario) {
    return findUsuarioUseCase.execute(nomeUsuario);
  }

  @Transactional
  public void deleteUsuarioById(Long id) {
    try {
      deleteUsuarioUseCase.execute(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Usuário não encontrado");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
