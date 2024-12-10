package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.*;

import java.util.List;
import java.util.Set;

public interface ImunizanteUseCase {

    ImunizanteComAnimalResponseDTO createImunizante(ImunizanteRequestDTO dto);

    ImunizanteTodasResponseDTO findImunizanteById(Long id);

    Set<ImunizanteResponseDTO> carteiraDeImunizantecao(Long idAnimal);

    List<ImunizanteTodasResponseDTO> listAllImunizantes();

    ImunizanteComAnimalResponseDTO updateImunizante(Long id, ImunizanteRequestDTO dto);

    void deleteImunizante(Long id);
}
