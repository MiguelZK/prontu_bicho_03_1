package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VinculoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateVinculoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Vinculo;
import br.edu.ifrs.miguelzk.domain.repository.VinculoRepository;
import org.modelmapper.ModelMapper;

public class CreateVinculoUseCaseImpl implements CreateVinculoUseCase {

  private final VinculoRepository vinculoRepository;
  private final ModelMapper modelMapper;

  public CreateVinculoUseCaseImpl(VinculoRepository vinculoRepository, ModelMapper modelMapper) {
    this.vinculoRepository = vinculoRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public VinculoResponseDTO execute(VinculoRequestDTO request) {
    Vinculo vinculo = modelMapper.map(request, Vinculo.class);
    Vinculo saveVinculo = vinculoRepository.save(vinculo);
    return modelMapper.map(saveVinculo, VinculoResponseDTO.class);
  }

}
