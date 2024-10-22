package br.edu.ifrs.miguelzk.application.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.FindUsuarioUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;

public class FindUsuarioUseCaseImpl implements FindUsuarioUseCase {

  private final UsuarioRepository usuarioRepository;
  private final ModelMapper modelMapper;

  public FindUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
    this.usuarioRepository = usuarioRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public UsuarioResponseDTO execute(Long id) {
    return modelMapper.map(usuarioRepository.findUsuarioById(id), UsuarioResponseDTO.class);
  }

  @Override
  public List<UsuarioResponseDTO> execute(String userName) {
    List<UsuarioResponseDTO> listResponseDTO = new ArrayList<>();
    List<Usuario> listUsuario = usuarioRepository.findUsuarioByName(userName);

    for (Usuario usuario : listUsuario) {
      listResponseDTO.add(modelMapper.map(usuario, UsuarioResponseDTO.class));
    }

    return listResponseDTO;
  }

  @Override
  public List<UsuarioResponseDTO> execute() {
    List<UsuarioResponseDTO> listResponseDTOs = new ArrayList<>();
    List<Usuario> listUsuarioEtity = usuarioRepository.findUsuarioAll();

    for (Usuario usuario : listUsuarioEtity) {
      listResponseDTOs.add(modelMapper.map(usuario, UsuarioResponseDTO.class));
    }

    return listResponseDTOs;
  }

}
