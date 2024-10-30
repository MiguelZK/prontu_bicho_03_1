package br.edu.ifrs.miguelzk.infrastructure.persistence;

import br.edu.ifrs.miguelzk.domain.entities.Vinculo;
import br.edu.ifrs.miguelzk.domain.repository.VinculoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class VinculoRepositoryImpl implements VinculoRepository, PanacheRepositoryBase<Vinculo, Long> {

  public Vinculo save(Vinculo Vinculo) {
    persist(Vinculo);
    return Vinculo;
  }

  @Override
  public Vinculo findVinculoById(Long id) {
    return findById(id);
  }

  @Override
  public List<Vinculo> findVinculoByName(String nomeVinculo) {
    return find("nome like ?1", "%" + nomeVinculo + "%").list();
  }

  @Override
  public List<Vinculo> findVinculoAll() {
    return listAll();
  }

  @Override
  public Vinculo update(Vinculo Vinculo) {
    persist(Vinculo);
    return Vinculo;
  }

  @Override
  public void deleteVinculoById(Long id) {
    deleteById(id);
  }

  @Override
  public long contaVinculos() {
    return count();
  }
}
