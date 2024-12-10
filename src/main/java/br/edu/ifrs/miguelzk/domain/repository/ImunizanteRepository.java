package br.edu.ifrs.miguelzk.domain.repository;

import br.edu.ifrs.miguelzk.domain.entities.Imunizante;

import java.util.List;

public interface ImunizanteRepository {

    Imunizante findImunizanteById(Long id);
    List<Imunizante> findImunizanteAll();
    Imunizante save(Imunizante vacina);
    Imunizante update(Imunizante vacina);
    void deleteImunizanteById(Long id);
}
