package br.edu.ifrs.miguelzk.application.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoTodosResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import org.modelmapper.ModelMapper;
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
  public AtendimentoTodosResponseDTO execute(Long id) {
    Optional<Atendimento> atendimentoOpt = Optional.ofNullable(atendimentoRepository.findAtendimentoById(id));
    if(atendimentoOpt.isEmpty()) {
      throw new ObjetoNaoEncontradoException("Atendimento Não Encontrado.");
    }
    Atendimento atendimento = atendimentoOpt.get();
    AtendimentoTodosResponseDTO atendimentoTodosResponseDTO = modelMapper
            .map(atendimento, AtendimentoTodosResponseDTO.class);
    atendimentoTodosResponseDTO.setUsuariosDTO(atendimento.getUsuarios().stream()
            .map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
            .collect(Collectors.toSet()));
    atendimentoTodosResponseDTO.setMedVetsDTO(atendimento.getMedVets().stream()
            .map(u -> modelMapper.map(u, MedVetResponseDTO.class))
            .collect(Collectors.toSet()));
    return atendimentoTodosResponseDTO;
  }

  @Override
  public List<AtendimentoTodosResponseDTO> execute() {
    List<AtendimentoTodosResponseDTO> listAtendimentoTodosResponse = new ArrayList<>();
    List<Atendimento> listAtendimento = atendimentoRepository.findAtendimentoAll();

    for (Atendimento atendimento : listAtendimento) {
      AtendimentoTodosResponseDTO atendimentoTodosResponse = modelMapper.map(atendimento, AtendimentoTodosResponseDTO.class);
      System.out.println(atendimento.getUsuarios());
      atendimentoTodosResponse.setUsuariosDTO(atendimento.getUsuarios().stream()
              .map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
              .collect(Collectors.toSet()));
      atendimentoTodosResponse.setMedVetsDTO(atendimento.getMedVets().stream()
              .map(u -> modelMapper.map(u, MedVetResponseDTO.class))
              .collect(Collectors.toSet()));
       listAtendimentoTodosResponse.add(atendimentoTodosResponse);
    }
    return listAtendimentoTodosResponse;
  }

}
