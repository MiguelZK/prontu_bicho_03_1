package br.edu.ifrs.miguelzk.domain.repository;

import br.edu.ifrs.miguelzk.domain.entities.Vacina;

import java.util.List;

public interface VacinaRepository {

    Vacina findVacinaById(Long id);
    List<Vacina> findVacinaAll();
    Vacina save(Vacina vacina);
    Vacina update(Vacina vacina);
    void deleteVacinaById(Long id);
}
