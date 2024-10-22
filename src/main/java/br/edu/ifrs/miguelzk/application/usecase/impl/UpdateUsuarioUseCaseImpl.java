package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.UpdateUsuarioUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.modelmapper.ModelMapper;

public class UpdateUsuarioUseCaseImpl implements UpdateUsuarioUseCase {

  private final ModelMapper modelMapper;
  private UsuarioRepository usuarioRepository;
  private FindUsuarioUseCaseImpl findUsuarioUseCase;

  public UpdateUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
    this.usuarioRepository = usuarioRepository;
    this.modelMapper = modelMapper;
    this.findUsuarioUseCase = new FindUsuarioUseCaseImpl(usuarioRepository, modelMapper);
  }

  @Override
  public UsuarioResponseDTO execute(UsuarioRequestDTO dto) {
    Usuario usuario = modelMapper.map(dto, Usuario.class);
    usuario.setPassword(BcryptUtil.bcryptHash(usuario.getPassword()));
    Usuario usuarioSaved = usuarioRepository.update(usuario);
    return modelMapper.map(usuarioSaved, UsuarioResponseDTO.class);
  }

  @Override
  public UsuarioResponseDTO execute(Long idUsuario) {
    UsuarioResponseDTO dto = findUsuarioUseCase.execute(idUsuario);
    Usuario usuario = modelMapper.map(dto, Usuario.class);
    usuario.setPassword(BcryptUtil.bcryptHash(usuario.getPassword()));
    Usuario usuarioSaved = usuarioRepository.update(usuario);
    return modelMapper.map(usuarioSaved, UsuarioResponseDTO.class);
  }

}
