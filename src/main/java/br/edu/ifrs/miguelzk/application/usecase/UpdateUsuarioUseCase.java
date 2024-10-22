package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;

public interface UpdateUsuarioUseCase {
    UsuarioResponseDTO execute(UsuarioRequestDTO dto);

    UsuarioResponseDTO execute(Long idUsuario);
}
