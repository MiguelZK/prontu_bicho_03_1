package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.MedVetRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.UpdateMedVetUseCase;
import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;
import org.modelmapper.ModelMapper;

public class UpdateMedVetUseCaseImpl implements UpdateMedVetUseCase {

  private final ModelMapper modelMapper;
  private MedVetRepository medVetRepository;

  public UpdateMedVetUseCaseImpl(MedVetRepository medVetRepository, ModelMapper modelMapper) {
    this.medVetRepository = medVetRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public MedVetResponseDTO execute(MedVetRequestDTO dto) {
    MedVet medVet = modelMapper.map(dto, MedVet.class);
    MedVet medVetSaved = medVetRepository.update(medVet);
    return modelMapper.map(medVetSaved, MedVetResponseDTO.class);
  }

}
