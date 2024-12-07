package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;

public interface UpdateAtendimentoUseCase {
    AtendimentoResponseDTO execute(Long id, AtendimentoRequestDTO dto);
}
