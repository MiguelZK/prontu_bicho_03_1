package br.edu.ifrs.miguelzk.application.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
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
    Optional<Atendimento> atendimentoOpt = Optional.ofNullable(atendimentoRepository.findAtendimentoById(id));
    if(atendimentoOpt.isEmpty()) {
      throw new ObjetoNaoEncontradoException("Atendimento Não Encontrado.");
    }
    Atendimento atendimento = atendimentoOpt.get();
    AtendimentoResponseDTO atendimentoResponseDTO = modelMapper
            .map(atendimento, AtendimentoResponseDTO.class);
    atendimentoResponseDTO.setUsuariosDTO(atendimento.getUsuarios().stream()
            .map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
            .collect(Collectors.toSet()));
    atendimentoResponseDTO.setMedVetsDTO(atendimento.getMedVets().stream()
            .map(u -> modelMapper.map(u, MedVetResponseDTO.class))
            .collect(Collectors.toSet()));
    return atendimentoResponseDTO;
  }

  @Override
  public List<AtendimentoResponseDTO> execute() {
    List<AtendimentoResponseDTO> listAtendimentoResponse = new ArrayList<>();
    List<Atendimento> listAtendimento = atendimentoRepository.findAtendimentoAll();

    for (Atendimento atendimento : listAtendimento) {
      AtendimentoResponseDTO atendimentoResponse = modelMapper.map(atendimento, AtendimentoResponseDTO.class);
      System.out.println(atendimento.getUsuarios());
      atendimentoResponse.setUsuariosDTO(atendimento.getUsuarios().stream()
              .map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
              .collect(Collectors.toSet()));
      atendimentoResponse.setMedVetsDTO(atendimento.getMedVets().stream()
              .map(u -> modelMapper.map(u, MedVetResponseDTO.class))
              .collect(Collectors.toSet()));
       listAtendimentoResponse.add(atendimentoResponse);
    }
    return listAtendimentoResponse;
  }

}
