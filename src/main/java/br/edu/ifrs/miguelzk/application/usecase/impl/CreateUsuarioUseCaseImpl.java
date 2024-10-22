package br.edu.ifrs.miguelzk.application.usecase.impl;

import io.quarkus.elytron.security.common.BcryptUtil;
import org.modelmapper.ModelMapper;
import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateUsuarioUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;

public class CreateUsuarioUseCaseImpl implements CreateUsuarioUseCase {

  private final UsuarioRepository usuarioRepository;
  private final ModelMapper modelMapper;

  public CreateUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
    this.usuarioRepository = usuarioRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public UsuarioResponseDTO execute(UsuarioRequestDTO request) {
    Usuario usuario = modelMapper.map(request, Usuario.class);
    usuario.setPassword(BcryptUtil.bcryptHash(usuario.getPassword()));
    Usuario saveUsuario = usuarioRepository.save(usuario);
    return modelMapper.map(saveUsuario, UsuarioResponseDTO.class);
  }

}
