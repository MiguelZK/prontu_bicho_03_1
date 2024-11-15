package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.MedVetRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.UpdateMedVetUseCase;
import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

public class UpdateMedVetUseCaseImpl implements UpdateMedVetUseCase {

  private final ModelMapper modelMapper;
  private MedVetRepository medVetRepository;

  public UpdateMedVetUseCaseImpl(MedVetRepository medVetRepository, ModelMapper modelMapper) {
    this.medVetRepository = medVetRepository;
    this.modelMapper = modelMapper;
  }


  @Override
  public MedVetResponseDTO execute(Long crmv, MedVetRequestDTO dto) {
    MedVet medVetExistente = medVetRepository.findMedVetByCrmv(crmv);
    if (medVetExistente == null) {
      throw new EntityNotFoundException("Usuário não encontrado ao buscar CRMV");
    }
    modelMapper.map(dto, medVetExistente);
    if (dto.getPassword() != null) {
      medVetExistente.setPassword(BcryptUtil.bcryptHash(dto.getPassword()));
    }
    MedVet medVetSaved = medVetRepository.update(medVetExistente);
    return modelMapper.map(medVetSaved, MedVetResponseDTO.class);
  }

}
