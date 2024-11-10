package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;

import java.util.List;

public interface FindMedVetUseCase {

  UsuarioResponseDTO execute(Long id);

  List<UsuarioResponseDTO> execute(String nomeUsuario);

  List<UsuarioResponseDTO> execute();

}
