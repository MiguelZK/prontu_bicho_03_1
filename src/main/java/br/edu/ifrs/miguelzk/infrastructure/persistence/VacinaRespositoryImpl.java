package br.edu.ifrs.miguelzk.infrastructure.persistence;

import br.edu.ifrs.miguelzk.domain.entities.Vacina;
import br.edu.ifrs.miguelzk.domain.repository.VacinaRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class VacinaRespositoryImpl implements VacinaRepository, PanacheRepositoryBase<Vacina, Long> {

  @Override
  public Vacina save(Vacina vacina) {
    persist(vacina);
    return vacina;
  }

  @Override
  public Vacina findVacinaById(Long id) {
    return findById(id);
  }

  @Override
  public List<Vacina> findVacinaAll() {
    return listAll();
  }

  @Override
  public void deleteVacinaById(Long id) {
    deleteById(id);
  }

  @Override
  public Vacina update(Vacina vacina) {
    getEntityManager().merge(vacina);
    return vacina;
  }

}
