package br.edu.ifrs.miguelzk.infrastructure.persistence;

import java.util.List;

import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import br.edu.ifrs.miguelzk.domain.repository.AtendimentoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtendimentoRespositoryImpl implements AtendimentoRepository, PanacheRepositoryBase<Atendimento, Long> {

  @Override
  public Atendimento save(Atendimento atendimento) {
    persist(atendimento);
    return atendimento;
  }

  @Override
  public Atendimento update(Atendimento atendimento) {
    getEntityManager().merge(atendimento);
    return atendimento;
  }

  @Override
  public Atendimento findAtendimentoById(Long id) {
    return findById(id);
  }

  @Override
  public List<Atendimento> findAtendimentoAll() {
    return listAll();
  }

  @Override
  public void deleteAtendimentoById(Long id) {
    deleteById(id);
  }

}
