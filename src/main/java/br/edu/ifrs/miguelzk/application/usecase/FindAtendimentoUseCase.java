package br.edu.ifrs.miguelzk.application.usecase;

import java.util.List;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;

public interface FindAtendimentoUseCase {

  AtendimentoResponseDTO execute(Long id);

  List<AtendimentoResponseDTO> execute();

}
