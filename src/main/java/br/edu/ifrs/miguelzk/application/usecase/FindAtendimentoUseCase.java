package br.edu.ifrs.miguelzk.application.usecase;

import java.util.List;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoTodosResponseDTO;

public interface FindAtendimentoUseCase {

  AtendimentoTodosResponseDTO execute(Long id);

  List<AtendimentoTodosResponseDTO> execute();

}
