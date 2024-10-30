package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.usecase.DeleteVinculoUseCase;
import br.edu.ifrs.miguelzk.domain.repository.VinculoRepository;

public class DeleteVinculoUseCaseImpl implements DeleteVinculoUseCase {

  private VinculoRepository vinculoRepository;

  public DeleteVinculoUseCaseImpl(VinculoRepository vinculoRepository) {
    this.vinculoRepository = vinculoRepository;
  }

  @Override
  public void execute(Long id) {
    vinculoRepository.deleteVinculoById(id);
  }

}
