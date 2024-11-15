package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.usecase.DeleteMedVetUseCase;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;

public class DeleteMedVetUseCaseImpl implements DeleteMedVetUseCase {

  private MedVetRepository medVetRepository;

  public DeleteMedVetUseCaseImpl(MedVetRepository medVetRepository) {
    this.medVetRepository = medVetRepository;
  }

  @Override
  public void execute(Long crmv) {
    medVetRepository.deleteMedVetByCrmv(crmv);
  }

}
