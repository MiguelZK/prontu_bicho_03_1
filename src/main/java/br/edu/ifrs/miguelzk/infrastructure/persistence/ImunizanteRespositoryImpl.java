package br.edu.ifrs.miguelzk.infrastructure.persistence;

import br.edu.ifrs.miguelzk.domain.entities.Imunizante;
import br.edu.ifrs.miguelzk.domain.repository.ImunizanteRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ImunizanteRespositoryImpl implements ImunizanteRepository, PanacheRepositoryBase<Imunizante, Long> {

  @Override
  public Imunizante save(Imunizante imunizante) {
    persist(imunizante);
    return imunizante;
  }

  @Override
  public Imunizante findImunizanteById(Long id) {
    return findById(id);
  }

  @Override
  public List<Imunizante> findImunizanteAll() {
    return listAll();
  }

  @Override
  public void deleteImunizanteById(Long id) {
    deleteById(id);
  }

  @Override
  public Imunizante update(Imunizante imunizante) {
    getEntityManager().merge(imunizante);
    return imunizante;
  }

}
