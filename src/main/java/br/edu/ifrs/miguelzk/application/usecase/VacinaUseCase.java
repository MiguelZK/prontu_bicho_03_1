package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.*;

import java.util.List;
import java.util.Set;

public interface VacinaUseCase {

    VacinaComAnimalResponseDTO createVacina(VacinaRequestDTO dto);

    VacinaTodasResponseDTO findVacinaById(Long id);

    Set<VacinaResponseDTO> carteiraDeVacinacao(Long idAnimal);

    List<VacinaTodasResponseDTO> listAllVacinas();

    VacinaComAnimalResponseDTO updateVacina(Long id, VacinaRequestDTO dto);

    void deleteVacina(Long id);
}
