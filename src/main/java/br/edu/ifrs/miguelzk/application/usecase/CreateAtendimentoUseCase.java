package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;

public interface CreateAtendimentoUseCase {

  public AtendimentoResponseDTO execute(AtendimentoRequestDTO request);

}
