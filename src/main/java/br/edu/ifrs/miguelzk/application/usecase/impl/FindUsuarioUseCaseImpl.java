package br.edu.ifrs.miguelzk.application.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.FindUsuarioUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import org.jboss.logging.Logger;

public class FindUsuarioUseCaseImpl implements FindUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOG = Logger.getLogger(FindUsuarioUseCaseImpl.class);

    public FindUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsuarioResponseDTO execute(Long id) {
        try {
            Usuario usuario = usuarioRepository.findUsuarioById(id);

            if (usuario == null) {
                LOG.info("FindUsuarioUseCaseImpl - Linha 35 - if");
                throw new NotFoundException("Usuario não encontrado");
            }
            return modelMapper.map(usuario, UsuarioResponseDTO.class);
        } catch (IllegalArgumentException e) {
            LOG.info("FindUsuarioUseCaseImpl - Linha 40 - catch - IllegalArgumentException");
            throw new BadRequestException("Usuario não encontrado");
        }
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
