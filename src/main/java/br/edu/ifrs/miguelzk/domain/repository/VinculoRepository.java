package br.edu.ifrs.miguelzk.domain.repository;

import br.edu.ifrs.miguelzk.domain.entities.Vinculo;

import java.util.List;

public interface VinculoRepository {
    
    Vinculo findVinculoById(Long id);
    List<Vinculo> findVinculoAll();
    List<Vinculo> findVinculoByName(String nomeVinculo);
    Vinculo save(Vinculo Vinculo);
    Vinculo update(Vinculo Vinculo);
    void deleteVinculoById(Long id);

    long contaVinculos();
}
