package br.edu.ifrs.miguelzk.infrastructure.persistence;

import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MedVetRepositoryImpl implements MedVetRepository, PanacheRepositoryBase<MedVet, Long> {

  public MedVet save(MedVet medVet) {
    persist(medVet);
    return medVet;
  }

  @Override
  public MedVet findMedVetById(Long id) {
    return findById(id);
  }

  @Override
  public MedVet findMedVetByCrmv(Long crmv) {
    return find("crmv", crmv).firstResult();
  }

  @Override
  public List<MedVet> findMedVetByLogin(String userName) {
    return find("userName like ?1", "%" + userName + "%").list();
  }

  @Override
  public List<MedVet> findMedVetByName(String parteDoNome) {
    return find("nomeCompleto like ?1", "%" + parteDoNome + "%").list();
  }

  @Override
  public MedVet update(MedVet medVet) {
    persist(medVet);
    return medVet;
  }

  @Override
  public List<MedVet> findMedVetAll() {
    return listAll();
  }

  @Override
  public void deleteMedVetByCrmv(Long crmv) {
    Long id = findMedVetByCrmv(crmv).getIdUsuario();
    deleteById(id);
  }

}
