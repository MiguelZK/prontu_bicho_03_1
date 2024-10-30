package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.UpdateUsuarioUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

public class UpdateUsuarioUseCaseImpl implements UpdateUsuarioUseCase {

    private final ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;

    public UpdateUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsuarioResponseDTO execute(Long id, UsuarioRequestDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findUsuarioById(id);
        if (usuarioExistente == null) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        modelMapper.map(dto, usuarioExistente);
        if (dto.getPassword() != null) {
            usuarioExistente.setPassword(BcryptUtil.bcryptHash(dto.getPassword()));
        }
        Usuario usuarioSaved = usuarioRepository.update(usuarioExistente);
        return modelMapper.map(usuarioSaved, UsuarioResponseDTO.class);
    }

}
