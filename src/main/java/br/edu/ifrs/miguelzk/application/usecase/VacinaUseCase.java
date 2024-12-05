package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.*;

import java.util.List;
import java.util.Set;

public interface VacinaUseCase {

    VacinaResponseDTO createVacina(VacinaRequestDTO dto);

    VacinaResponseDTO findVacinaById(Long id);

    Set<VacinaResponseSemAnimalDTO> carteiraDeVacinacao(Long idAnimal);

    List<VacinaResponseDTO> listAllVacinas();

    VacinaResponseDTO updateVacina(Long id, VacinaRequestDTO dto);

    void deleteVacina(Long id);
}
