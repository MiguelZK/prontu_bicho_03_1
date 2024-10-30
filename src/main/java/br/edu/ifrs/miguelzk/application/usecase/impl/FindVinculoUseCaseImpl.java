package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.VinculoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.FindVinculoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Vinculo;
import br.edu.ifrs.miguelzk.domain.repository.VinculoRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class FindVinculoUseCaseImpl implements FindVinculoUseCase {

  private final VinculoRepository vinculoRepository;
  private final ModelMapper modelMapper;

  public FindVinculoUseCaseImpl(VinculoRepository vinculoRepository, ModelMapper modelMapper) {
    this.vinculoRepository = vinculoRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public VinculoResponseDTO execute(Long id) {
    return modelMapper.map(vinculoRepository.findVinculoById(id), VinculoResponseDTO.class);
  }

  @Override
  public List<VinculoResponseDTO> execute(String userName) {
    List<VinculoResponseDTO> listResponseDTO = new ArrayList<>();
    List<Vinculo> listVinculo = vinculoRepository.findVinculoByName(userName);

    for (Vinculo vinculo : listVinculo) {
      listResponseDTO.add(modelMapper.map(vinculo, VinculoResponseDTO.class));
    }

    return listResponseDTO;
  }

  @Override
  public List<VinculoResponseDTO> execute() {
    List<VinculoResponseDTO> listResponseDTOs = new ArrayList<>();
    List<Vinculo> listVinculoEtity = vinculoRepository.findVinculoAll();

    for (Vinculo vinculo : listVinculoEtity) {
      listResponseDTOs.add(modelMapper.map(vinculo, VinculoResponseDTO.class));
    }

    return listResponseDTOs;
  }

}
