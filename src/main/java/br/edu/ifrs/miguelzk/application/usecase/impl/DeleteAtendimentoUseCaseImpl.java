package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.usecase.DeleteAtendimentoUseCase;
import br.edu.ifrs.miguelzk.domain.repository.AtendimentoRepository;

public class DeleteAtendimentoUseCaseImpl implements DeleteAtendimentoUseCase {

  private AtendimentoRepository atendimentoRepository;

  public DeleteAtendimentoUseCaseImpl(AtendimentoRepository atendimentoRepository) {
    this.atendimentoRepository = atendimentoRepository;
  }

  @Override
  public void execute(Long id) {
    atendimentoRepository.deleteAtendimentoById(id);
  }

}
