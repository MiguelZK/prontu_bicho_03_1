package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.usecase.DeleteUsuarioUseCase;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;

public class DeleteUsuarioUseCaseImpl implements DeleteUsuarioUseCase {

  private UsuarioRepository usuarioRepository;

  public DeleteUsuarioUseCaseImpl(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public void execute(Long id) {
    usuarioRepository.deleteUsuarioById(id);
  }

}
