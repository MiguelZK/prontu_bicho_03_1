package br.edu.ifrs.miguelzk.application.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.FindAtendimentoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import br.edu.ifrs.miguelzk.domain.repository.AtendimentoRepository;

public class FindAtendimentoUseCaseImpl implements FindAtendimentoUseCase {

  private final AtendimentoRepository atendimentoRepository;
  private final ModelMapper modelMapper;

  public FindAtendimentoUseCaseImpl(AtendimentoRepository atendimentoRepository, ModelMapper modelMapper) {
    this.atendimentoRepository = atendimentoRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public AtendimentoResponseDTO execute(Long id) {
    return modelMapper.map(atendimentoRepository.findAtendimentoById(id), AtendimentoResponseDTO.class);
  }

  @Override
  public List<AtendimentoResponseDTO> execute() {
    List<AtendimentoResponseDTO> listAtendimentoResponse = new ArrayList<>();
    List<Atendimento> listAtendimento = atendimentoRepository.findAtendimentoAll();

    for (Atendimento atendimento : listAtendimento) {
      AtendimentoResponseDTO atendimentoResponse = modelMapper.map(atendimento, AtendimentoResponseDTO.class);
       listAtendimentoResponse.add(atendimentoResponse);
    }
    return listAtendimentoResponse;
  }

}
