package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.VinculoResponseDTO;

import java.util.List;

public interface FindVinculoUseCase {

  VinculoResponseDTO execute(Long id);

  List<VinculoResponseDTO> execute(String nomeVinculo);

  List<VinculoResponseDTO> execute();

}
